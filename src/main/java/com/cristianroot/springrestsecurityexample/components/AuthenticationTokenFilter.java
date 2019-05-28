/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.components;

import com.cristianroot.springrestsecurityexample.constants.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthenticationTokenFilter extends BasicAuthenticationFilter {

	private final JwtProperties jwtProperties;

	public AuthenticationTokenFilter(JwtProperties jwtProperties, AuthenticationManager authenticationManager) {
		super(authenticationManager);
		this.jwtProperties = jwtProperties;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String token = request.getHeader(jwtProperties.getHeader());

		if (token == null || !token.startsWith(jwtProperties.getPrefix())) {
			filterChain.doFilter(request, response);
			return;
		}

		// Remove the prefix
		token = token.replace(jwtProperties.getPrefix(), "");

		// Read and validate the jwt
		Jws<Claims> jws = Jwts.parser()
							  .setSigningKey(jwtProperties.getSecret().getBytes())
							  .parseClaimsJws(token);

		// Get the authorities and username from token and autenticate with them
		//añadir try catch de la firma
		List<GrantedAuthority> authorities = ((List<String>) jws.getBody().get("authorities")).stream()
																							  .map(SimpleGrantedAuthority::new)
																							  .collect(Collectors.toList());
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jws.getBody().getSubject(), token, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		filterChain.doFilter(request, response);
	}

}
