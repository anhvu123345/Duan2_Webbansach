package duan2.nhom11.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import duan2.nhom11.demo.controller.SimpleAuthenticationSuccessHandler;
import duan2.nhom11.demo.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SimpleAuthenticationSuccessHandler simpleAuthenticationSuccessHandler;
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
		http.authorizeRequests()
				.antMatchers("/welcome", "/manager/catagory/list", "/manager/order/list", "/manager/product/list",
						"/manager/catagory/add", "/manager/product/add", "/manager/{?}/upload",
						"/manager/product/{?}/edit")
				.access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/admin/userlist").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/login");
		http.authorizeRequests().and().formLogin()//
				// Submit URL của trang login
				.loginProcessingUrl("/checklogin") // Submit URL
				.loginPage("/login").successHandler(simpleAuthenticationSuccessHandler).failureUrl("/login?error=true")//
				.usernameParameter("email")//
				.passwordParameter("password").and().logout().logoutUrl("/logout")
				.logoutSuccessUrl("/logoutSuccessful");
		//
		http.rememberMe().key("uniqueAndSecret").tokenValiditySeconds(1296000);

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		return db;
	}
	
	
}

      