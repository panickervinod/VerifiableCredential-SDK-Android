/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Microsoft Corporation. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package com.microsoft.portableIdentity.sdk.auth.validators

import com.microsoft.portableIdentity.sdk.crypto.protocols.jose.JoseToken
import com.microsoft.portableIdentity.sdk.crypto.protocols.jose.jws.JwsToken

/**
 * Class that can be used to validate, decrypt, and/or verify JoseToken.
 */
class Validator: IValidator {

    override fun decrypt(token: JoseToken) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun verify(token: JwsToken): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}