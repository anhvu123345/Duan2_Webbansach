package duan2.nhom11.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import duan2.nhom11.demo.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
//			.antMatchers("/login").permitAll()
//			.antMatchers("/user/registrantion","/user/sign").permitAll()
//			.antMatchers("/admin/**","/manager/**").hasRole("ADMIN")
//			.antMatchers("/manager/**").hasRole("MANAGER")
//			.antMatchers("/user/**").hasRole("USER")
			
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/welcome")		
		.usernameParameter("email")
		.passwordParameter("password")
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/403");
		
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());
	}
}
