/*
 * Copyright (C) 2015 Glyptodon LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.glyptodon.guacamole.auth.oauth.form;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import org.apache.guacamole.form.Field;

/**
 * Field definition which represents the token returned by an OAuth service.
 * Within the user interface, this will be rendered as an appropriate "Log in
 * with ..." button which links to the OAuth service.
 *
 * @author Michael Jumper
 */
public class OAuthTokenField extends Field {

    /**
     * The standard HTTP parameter which will be included within the URL by all
     * OAuth services upon successful authentication and redirect.
     */
    public static final String PARAMETER_NAME = "id_token";

    /**
     * The full URI which the field should link to.
     */
    private final String authorizationURI;

    /**
     * Cryptographically-secure random number generator for generating the
     * required nonce.
     */
    private static final SecureRandom random = new SecureRandom();

    /**
     * Generates a cryptographically-secure nonce value. The nonce is intended
     * to be used to prevent replay attacks.
     *
     * @return
     *     A cryptographically-secure nonce value.
     */
    private static String generateNonce() {
        return new BigInteger(130, random).toString(32);
    }

    /**
     * Creates a new OAuth "id_token" field which links to the given OAuth
     * service using the provided client ID. Successful authentication at the
     * OAuth service will result in the client being redirected to the specified
     * redirect URI. The OAuth token will be embedded in the fragment (the part
     * following the hash symbol) of that URI, which the JavaScript side of
     * this extension will move to the query parameters.
     *
     * @param authorizationEndpoint
     *     The full URL of the endpoint accepting OAuth authentication
     *     requests.
     *
     * @param clientID
     *     The ID of the OAuth client. This is normally determined ahead of
     *     time by the OAuth service through some manual credential request
     *     procedure.
     *
     * @param redirectURI
     *     The URI that the OAuth service should redirect to upon successful
     *     authentication.
     */
    public OAuthTokenField(String authorizationEndpoint, String clientID,
            String redirectURI) {

        // Init base field properties
        super(PARAMETER_NAME, "GUAC_OAUTH_TOKEN");

        // Build authorization URI from given values
        try {
            this.authorizationURI = authorizationEndpoint
                    + "?scope=openid"
                    + "&response_type=id_token"
                    + "&client_id=" + URLEncoder.encode(clientID, "UTF-8")
                    + "&redirect_uri=" + URLEncoder.encode(redirectURI, "UTF-8")
                    + "&nonce=" + generateNonce();
        }

        // Java is required to provide UTF-8 support
        catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException("Unexpected lack of UTF-8 support.", e);
        }

    }

    /**
     * Returns the full URI that this field should link to when a new token
     * needs to be obtained from the OAuth service.
     *
     * @return
     *     The full URI that this field should link to.
     */
    public String getAuthorizationURI() {
        return authorizationURI;
    }

}
