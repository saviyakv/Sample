package org.sample;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableWebSecurity
public class LdapSecurity extends WebSecurityConfigurerAdapter {
	
	@Value("${sunOneURL}")
	String sunOneURL;
	
	@Value("${sunOneDirectory}")
	String sunOneDirectory;
	
	@Value("${sunOneUsers}")
	String sunOneUsers;

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

   /* @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic().and().authorizeRequests().anyRequest().authenticated().and().csrf().disable();

    }
*/
    protected void configure(HttpSecurity http) throws Exception {
    	http
    	.authorizeRequests()
    	.antMatchers("/login**").permitAll()
    	.antMatchers("/profile/**").fullyAuthenticated()
    	.antMatchers("/").permitAll()
    	.and()
    	.formLogin()
    	.loginPage("/login")
    	.failureUrl("/login?error")
    	.permitAll()
    	.and()
    	.logout()
    	.invalidateHttpSession(true)
    	.deleteCookies("JSESSIONID")
    	.permitAll();
    	}
    	
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.ldapAuthentication()
                .contextSource().url(sunOneURL)
                .managerDn(sunOneDirectory).managerPassword("secret")
                .and()
                .userSearchBase(sunOneUsers)
                .userSearchFilter("(cn={0})");
    }
}
