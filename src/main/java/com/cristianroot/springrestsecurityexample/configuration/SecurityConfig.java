/******************************************************************************
 * Copyright (c) 2019. Cristian Gonzalez Morante                              *
 ******************************************************************************/

package com.cristianroot.springrestsecurityexample.configuration;

import com.cristianroot.springrestsecurityexample.components.AuthenticationTokenFilter;
import com.cristianroot.springrestsecurityexample.constants.properties.JwtProperties;
import com.cristianroot.springrestsecurityexample.services.impl.CustomUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userDetailsService;
	private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	private final JwtProperties jwtProperties;

	public SecurityConfig(CustomUserDetailsServiceImpl userDetailsService, RestAuthenticationEntryPoint restAuthenticationEntryPoint,
						  JwtProperties jwtProperties) {
		this.userDetailsService = userDetailsService;
		this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
		this.jwtProperties = jwtProperties;
	}

	@Bean
	@Override
	//no hemos definido uno personalizado y permite inyectar este servicio en el resto de clases pero
	//al no ser sobreescrito no está disponible para inyectarse
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	//encriptar la contraseña
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	//con esto seteamos el autentication donde le decimos el ahtentication manager que usamos y el password encode
	public void configureAuthenticationManager(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	//son una serie de restrictiones de los navegadores que hay que configurar y con esto se configura solo
	//menos si queremos restringir algún dominio
	//authenticationEntryPoint la añadimos un filtro que es el authentication token filter
	//si metemos authoized request poner un patron como que todo lo que todo lo que tenga auth te lo autentica
	//de esta forma aseguras todo el endpoint
	//también se puede poner a nivel de cada endpoint la seguridad

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.addFilter(new AuthenticationTokenFilter(jwtProperties, authenticationManagerBean()))
			.authorizeRequests()
			.antMatchers(HttpMethod.GET).permitAll()
			.antMatchers("/auth/login").permitAll()
			.anyRequest().permitAll();
	}

}
