
/*---------------------------------------------------------------------------------------------
 *  Copyright (c) Microsoft Corporation. All rights reserved.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/
/**
  * Class for JOSE constants
  */
 export default class JoseConstants {
   
  /**
   * Define JOSE protocol name
   */
   public static Jose = 'JOSE';
   
  /**
   * Define JWE protocol name
   */
   public static Jwe = 'JWE';
   
  /**
   * Define JWS protocol name
   */
   public static Jws = 'JWS';

  /**
   * Define JOSE algorithm constants
   */
   public static RsaOaep256 = 'RSA-OAEP-256';

  /**
   * Define JOSE algorithm constants
   */
   public static RsaOaep = 'RSA-OAEP';

   /**
   * Define JOSE algorithm constants
   */
   public static Rs256 = 'RS256';

  /**
   * Define JOSE algorithm constants
   */
   public static Rs384 = 'RS384';

  /**
   * Define JOSE algorithm constants
   */
  public static Rs512 = 'RS512';

  /**
   * Define JOSE algorithm constants
   */
  public static Es256K = 'ES256K';

   /**
   * Define JOSE algorithm constants
   */
  public static AesGcm128 ='A128GCM';
  
  /**
   * Define JOSE algorithm constants
   */
  public static AesGcm192 ='A192GCM';
  
  /**
   * Define JOSE algorithm constants
   */
   public static AesGcm256 ='A256GCM';

  /**
   * Define JOSE algorithm constants
   */
   public static Hs256 = 'HS256';

  /**
   * Define JOSE algorithm constants
   */
   public static Hs512 = 'HS512';

   /**
    * Define the default signing algorithm
    */
   public static DefaultSigningAlgorithm = JoseConstants.Es256K;
   
   /**
    * Define the JOSE protocol elements
    */
   public static Alg = 'alg';
   
   /**
    * Define the JOSE protocol elements
    */
   public static Kid = 'kid';
   
   /**
    * Define the JOSE protocol elements
    */
   public static Enc = 'enc';
   
}
