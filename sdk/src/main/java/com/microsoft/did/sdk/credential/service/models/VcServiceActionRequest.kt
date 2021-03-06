// Copyright (c) Microsoft Corporation. All rights reserved

package com.microsoft.did.sdk.credential.service.models

import com.microsoft.did.sdk.credential.models.VerifiableCredential
import com.microsoft.did.sdk.identifier.models.Identifier

/**
 * Sealed Class for Requests to the Verifiable Credential Service to do a certain action on a Verifiable Credential.
 */
sealed class VcServiceActionRequest(val audience: String)

class PairwiseIssuanceRequest(val verifiableCredential: VerifiableCredential, val pairwiseIdentifier: String) :
    VcServiceActionRequest(verifiableCredential.contents.vc.exchangeService?.id ?: "")

class RevocationRequest(val verifiableCredential: VerifiableCredential, val owner: Identifier) :
    VcServiceActionRequest(verifiableCredential.contents.vc.revokeService?.id ?: "")

class StatusRequest(val verifiableCredential: VerifiableCredential, val owner: Identifier) :
    VcServiceActionRequest(verifiableCredential.contents.vc.credentialStatus?.id ?: "")

