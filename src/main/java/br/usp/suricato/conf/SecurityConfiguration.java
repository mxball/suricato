package br.usp.suricato.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

   @Autowired
   private UserDetailsService users;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(users);
	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/usuario/cadastro").permitAll()
			.antMatchers("/usuario/novo").permitAll()
			.antMatchers("/retrospectiva/asndjkahsdjhds/**").permitAll()
			.antMatchers("/retrospectiva/mostra").permitAll()
			.anyRequest().authenticated()
			.and()
				.formLogin().loginPage("/login").permitAll()
			.and()
				.exceptionHandling().accessDeniedPage("/403");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/assets/**");
	}
	
}
