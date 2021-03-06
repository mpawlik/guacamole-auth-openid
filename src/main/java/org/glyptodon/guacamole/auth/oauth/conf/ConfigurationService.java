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

package org.glyptodon.guacamole.auth.oauth.conf;

import com.google.inject.Inject;
import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.environment.Environment;

/**
 * Service for retrieving configuration information regarding the OAuth service.
 *
 * @author Michael Jumper
 */
public class ConfigurationService {

    /**
     * The Guacamole server environment.
     */
    @Inject
    private Environment environment;

    /**
     * Returns the authorization endpoint (URI) of the OAuth service as
     * configured with guacamole.properties.
     *
     * @return
     *     The authorization endpoint of the OAuth service, as configured with
     *     guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed, or if the authorization
     *     endpoint property is missing.
     */
    public String getAuthorizationEndpoint() throws GuacamoleException {
        return environment.getRequiredProperty(OAuthGuacamoleProperties.OAUTH_AUTHORIZATION_ENDPOINT);
    }

    /**
     * Returns the OAuth client ID which should be submitted to the OAuth
     * service when necessary, as configured with guacamole.properties. This
     * value is typically provided by the OAuth service when OAuth credentials
     * are generated for your application.
     *
     * @return
     *     The client ID to use when communicating with the OAuth service,
     *     as configured with guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed, or if the client ID
     *     property is missing.
     */
    public String getClientID() throws GuacamoleException {
        return environment.getRequiredProperty(OAuthGuacamoleProperties.OAUTH_CLIENT_ID);
    }

    /**
     * Returns the URI that the OAuth service should redirect to after
     * the authentication process is complete, as configured with
     * guacamole.properties. This must be the full URL that a user would enter
     * into their browser to access Guacamole.
     *
     * @return
     *     The client secret to use when communicating with the OAuth service,
     *     as configured with guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed, or if the redirect URI
     *     property is missing.
     */
    public String getRedirectURI() throws GuacamoleException {
        return environment.getRequiredProperty(OAuthGuacamoleProperties.OAUTH_REDIRECT_URI);
    }

    /**
     * Returns the issuer to expect for all received ID tokens, as configured
     * with guacamole.properties.
     *
     * @return
     *     The issuer to expect for all received ID tokens, as configured with
     *     guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed, or if the issuer property
     *     is missing.
     */
    public String getIssuer() throws GuacamoleException {
        return environment.getRequiredProperty(OAuthGuacamoleProperties.OAUTH_ISSUER);
    }

    /**
     * Returns the endpoint (URI) of the JWKS service which defines how
     * received ID tokens (JWTs) shall be validated, as configured with
     * guacamole.properties.
     *
     * @return
     *     The endpoint (URI) of the JWKS service which defines how received ID
     *     tokens (JWTs) shall be validated, as configured with
     *     guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed, or if the JWKS endpoint
     *     property is missing.
     */
    public String getJWKSEndpoint() throws GuacamoleException {
        return environment.getRequiredProperty(OAuthGuacamoleProperties.OAUTH_JWKS_ENDPOINT);
    }

    /**
     * Returns the claim type which contains the authenticated user's username
     * within any valid JWT, as configured with guacamole.properties.
     *
     * @return
     *     The claim type which contains the authenticated user's username
     *     within any valid JWT, as configured with guacamole.properties.
     *
     * @throws GuacamoleException
     *     If guacamole.properties cannot be parsed, or if the username claim
     *     type property is missing.
     */
    public String getUsernameClaimType() throws GuacamoleException {
        return environment.getRequiredProperty(OAuthGuacamoleProperties.OAUTH_USERNAME_CLAIM_TYPE);
    }

}
