/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.controllers;

import com.cristianroot.springrestsecurityexample.constants.properties.JwtProperties;
import com.cristianroot.springrestsecurityexample.models.AuthenticationRequest;
import com.cristianroot.springrestsecurityexample.models.AuthenticationResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final JwtProperties jwtProperties;

	public AuthenticationController(AuthenticationManager authenticationManager, JwtProperties jwtProperties) {
		this.authenticationManager = authenticationManager;
		this.jwtProperties = jwtProperties;
	}

	@PostMapping("/auth/login")
	public AuthenticationResponse getToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
		// Let spring check the credentials and get the authorities
		//El authentication manager tiene que buscar en base de datos las clases del usuario, si se cumple está bien
		//hay que sobrescribir userDetails manager
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
														authenticationRequest.getPassword()));

		// Extract authorities from spring authentication
		List<String> authorityList = authentication.getAuthorities()
												   .stream()
												   .map(GrantedAuthority::getAuthority)
												   .collect(Collectors.toList());

		// Create JWS token
		//se puede hacer el token a mano porque es el proceso para generar un token pero hay librerías de terceros que te lo hace
		//esta clase tiene un builder que te da un objeto jwt builder que te da las cosas necesarias para parsear un token
		//y le dices que es lo que necesitas para firmarlo
		String token = Jwts.builder()
						   .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes()))
						   .setIssuer(jwtProperties.getIssuer())
						   .setSubject(authenticationRequest.getUsername())
						   .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpirationInMillis()))
						   .claim(jwtProperties.getAuthoritiesClaim(), authorityList)
						   .compact();
		//authorities son la lista de roles
		return new AuthenticationResponse(token);
	}

}
