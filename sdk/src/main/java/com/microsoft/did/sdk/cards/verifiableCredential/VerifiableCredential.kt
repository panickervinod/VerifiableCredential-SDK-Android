/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Microsoft Corporation. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package com.microsoft.did.sdk.cards.verifiableCredential

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class VerifiableCredential(
    // jti of the verifiable credential
    @PrimaryKey
    val jti: String,

    // raw token.
    val raw: String,

    // contents of the Verifiable Credential token.
    val contents: VerifiableCredentialContent,

    // id of the Prime Verifiable Credential. (if prime VC, jti == cardId)
    val picId: String

)