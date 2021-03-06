/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Microsoft Corporation. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package com.microsoft.did.sdk

import com.microsoft.did.sdk.crypto.CryptoOperations
import com.microsoft.did.sdk.identifier.models.Identifier
import com.microsoft.did.sdk.identifier.IdentifierCreator
import com.microsoft.did.sdk.datasource.repository.IdentifierRepository
import com.microsoft.did.sdk.util.Base64Url
import com.microsoft.did.sdk.util.Constants.HASHING_ALGORITHM_FOR_ID
import com.microsoft.did.sdk.util.Constants.MASTER_IDENTIFIER_NAME
import com.microsoft.did.sdk.util.Constants.METHOD_NAME
import com.microsoft.did.sdk.util.log.SdkLog
import com.microsoft.did.sdk.util.controlflow.Result
import com.microsoft.did.sdk.util.controlflow.RepositoryException
import com.microsoft.did.sdk.util.controlflow.runResultTry
import com.microsoft.did.sdk.util.stringToByteArray
import kotlinx.coroutines.*
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class provides methods to create, update and manage decentralized identifiers.
 */
@Singleton
class IdentifierManager @Inject constructor(
    val identifierRepository: IdentifierRepository,
    private val cryptoOperations: CryptoOperations,
    private val identifierCreator: IdentifierCreator
) {

    suspend fun getMasterIdentifier(): Result<Identifier> {
        return withContext(Dispatchers.IO) { getOrCreateMasterIdentifier() }
    }

    private suspend fun getOrCreateMasterIdentifier(): Result<Identifier> {
        val identifier = identifierRepository.queryByName(MASTER_IDENTIFIER_NAME)
        return if (identifier != null) {
            Result.Success(identifier)
        } else {
            createMasterIdentifier()
        }
    }

    // Master Identifier will be created once per app.
    private suspend fun createMasterIdentifier(): Result<Identifier> {
        return runResultTry {
            //TODO(seed is needed for pairwise key generation)
            cryptoOperations.generateAndStoreSeed()
            // peer id for master Identifier will be method name for now.
            val identifier = identifierCreator.create(METHOD_NAME).abortOnError()
            SdkLog.i("Creating Identifier: $identifier")
            saveIdentifier(identifier)
        }
    }

    suspend fun createPairwiseIdentifier(identifier: Identifier, peerId: String): Result<Identifier> {
        return withContext(Dispatchers.IO) {
            runResultTry {
                when (val pairwiseIdentifier = identifierRepository.queryByName(pairwiseIdentifierName(peerId))) {
                    null -> {
                        val registeredIdentifier = identifierCreator.createPairwiseId(identifier.id, peerId).abortOnError()
                        saveIdentifier(registeredIdentifier)
                    }
                    else -> Result.Success(pairwiseIdentifier)
                }
            }
        }
    }

    private fun pairwiseIdentifierName(peerId: String): String {
        val digest = MessageDigest.getInstance(HASHING_ALGORITHM_FOR_ID)
        return Base64Url.encode(digest.digest(stringToByteArray(peerId)))
    }

    private fun saveIdentifier(identifier: Identifier): Result<Identifier> {
        return try {
            identifierRepository.insert(identifier)
            Result.Success(identifier)
        } catch (exception: Exception) {
            throw RepositoryException("Unable to save identifier in repository", exception)
        }
    }
}