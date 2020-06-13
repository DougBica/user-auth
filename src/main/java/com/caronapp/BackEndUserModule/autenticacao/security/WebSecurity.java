package com.caronapp.BackEndUserModule.autenticacao.security;

import static com.caronapp.BackEndUserModule.autenticacao.security.ConstantesSeguranca.SIGN_UP_URL;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.caronapp.BackEndUserModule.autenticacao.service.ServiceDetalhesUsuario;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	private ServiceDetalhesUsuario serviceDetalhesUsuario;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(ServiceDetalhesUsuario serviceDetalhesUsuario, BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.serviceDetalhesUsuario = serviceDetalhesUsuario;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST, "/caronapp/public/*").permitAll()
        .anyRequest().authenticated()
        .and()
        .addFilter(new JWTAuthFilter(authenticationManager()))
        .addFilter(new JWTAuthorizationFilter(authenticationManager()))
        // this disables session creation on Spring Security
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(serviceDetalhesUsuario).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Bean 
	CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	}
	
	

}
