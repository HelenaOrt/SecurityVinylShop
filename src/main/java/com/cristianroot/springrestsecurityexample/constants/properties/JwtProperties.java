/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.constants.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

	@NotNull
	private String secret;

	@NotNull
	private String header;

	@NotNull
	private String issuer;

	@NotNull
	private String prefix;

	@NotNull
	private Long expiration;

	@NotNull
	private String authoritiesClaim;

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public Long getExpiration() {
		return expiration;
	}

	public Long getExpirationInMillis() {
		return expiration * 60 * 1000;
	}

	public void setExpiration(Long expiration) {
		this.expiration = expiration;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getAuthoritiesClaim() {
		return authoritiesClaim;
	}

	public void setAuthoritiesClaim(String authoritiesClaim) {
		this.authoritiesClaim = authoritiesClaim;
	}
}
