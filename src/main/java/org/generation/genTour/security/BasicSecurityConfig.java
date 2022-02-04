package org.generation.genTour.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth.userDetailsService(userDetailsService);

		auth.inMemoryAuthentication().withUser("gentour").password(passwordEncoder().encode("gentour"))
		 .authorities("ROLE_ADMIN");
 	}
	
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
			
		}
		
		@Override
		protected void configure (HttpSecurity http) throws Exception {
			http.authorizeHttpRequests()
			.antMatchers("/usuario/logar").permitAll() // Acessar sem precisar de token
			.antMatchers("/usuario/cadastrar").permitAll()// Acessar sem precisar de token
			.antMatchers("/turismo").permitAll()// Acessar sem precisar de token
			.anyRequest().authenticated() // precisa de autenticação 
			.and().httpBasic() //  utiliza o metodo basic para gerar o token
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Stateless não guarda seção. Indica a seção  
			.and().cors() //
			.and().csrf().disable();// 
		}
}
