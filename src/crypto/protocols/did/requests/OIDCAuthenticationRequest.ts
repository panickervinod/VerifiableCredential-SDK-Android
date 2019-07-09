/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Microsoft Corporation. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

/**
 * OpenID Connect Self-Issued Authentication Request.
 */
export default interface OIDCAuthenticationRequest {
  /** 
   * DID of the issuer of the request. This should match the signature
   */
  iss: string;
  /** 
   * MUST be set as 'id_token' in order to match OIDC self-issued protocol 
   */
  response_type: 'id_token';
  /**
   * MUST be set as 'form_post' in order to match OIDC self-issued protocol
   */
  response_mode: 'form_post'
  /** 
   * The redirect url as specified in the OIDC self-issued protocol
   */
  client_id: string;
  /** 
   * MUST start with 'openid' 
   */
  scope: string;
  /** 
   * Opaque value used by issuer for state 
   */
  state?: string;
  /** 
   * Request Nonce 
   */
  nonce: string;
  /** 
   * Claims that are requested
   */
  claims?: {id_token: {[key: string]: any}};
  /**
   * URL encoded object containing information about the requester
   */
  registration?: string;
}
