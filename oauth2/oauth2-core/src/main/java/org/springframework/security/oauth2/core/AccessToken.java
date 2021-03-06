/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.security.oauth2.core;

import org.springframework.util.Assert;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of an {@link AbstractToken} representing an <i>OAuth 2.0 Access Token</i>.
 *
 * <p>
 * An access token is a credential that represents an authorization
 * granted by the resource owner to the client.
 * It is primarily used by the client to access protected resources on either a
 * resource server or the authorization server that originally issued the access token.
 *
 * @author Joe Grandja
 * @since 5.0
 * @see <a target="_blank" href="https://tools.ietf.org/html/rfc6749#section-1.4">Section 1.4 Access Token</a>
 */
public class AccessToken extends AbstractToken {
	private final TokenType tokenType;
	private final Set<String> scopes;
	private final Map<String,Object> additionalParameters;

	public enum TokenType {
		BEARER("Bearer");

		private final String value;

		TokenType(String value) {
			this.value = value;
		}

		public String value() {
			return this.value;
		}
	}

	public AccessToken(TokenType tokenType, String tokenValue, Instant issuedAt, Instant expiresAt) {
		this(tokenType, tokenValue, issuedAt, expiresAt, Collections.emptySet());
	}

	public AccessToken(TokenType tokenType, String tokenValue, Instant issuedAt, Instant expiresAt, Set<String> scopes) {
		this(tokenType, tokenValue, issuedAt, expiresAt, scopes, Collections.emptyMap());
	}

	public AccessToken(TokenType tokenType, String tokenValue, Instant issuedAt, Instant expiresAt,
						Set<String> scopes, Map<String,Object> additionalParameters) {

		super(tokenValue, issuedAt, expiresAt);
		Assert.notNull(tokenType, "tokenType cannot be null");
		this.tokenType = tokenType;
		this.scopes = Collections.unmodifiableSet(
			scopes != null ? scopes : Collections.emptySet());
		this.additionalParameters = Collections.unmodifiableMap(
			additionalParameters != null ? additionalParameters : Collections.emptyMap());
	}

	public TokenType getTokenType() {
		return this.tokenType;
	}

	public Set<String> getScopes() {
		return this.scopes;
	}

	public Map<String, Object> getAdditionalParameters() {
		return additionalParameters;
	}
}
