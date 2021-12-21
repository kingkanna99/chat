package com.cosmos.chat.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          .authorizeRequests()
            .antMatchers("/", "/login", "/new-account", "/new-role", "/prova", "/get-lista", "/put-element", "/invalidate").permitAll()
            .antMatchers("/add-Gruppo", "/get-Gruppi", "/delete-Gruppo", "/add-UserToGruppo", "/delete-UserToGruppo").permitAll()
            .antMatchers("/send-Messaggio-Direct", "/send-Messaggio-Fanout", "/send-Messaggio-Topic", "/send-Messaggio-Header").permitAll()
            .antMatchers("/send-Messaggio-Gruppo", "/send-Messaggio-User").permitAll()
          .anyRequest()
          .authenticated()
          //.and()
          //.httpBasic()
          ;
    }


    /*@Autowired
    private UserDetailsService userDetailsService;*/
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
	        .csrf().disable()
	        .formLogin()
	        	.loginProcessingUrl("/login")
	        	.loginPage("/")
	        	.defaultSuccessUrl("/chat")
	        	.and()
	        .logout()
	        	.logoutSuccessUrl("/")
	        	.and()
	        .authorizeRequests()
	        	.antMatchers("/login", "/new-account", "/", "/prova", "/my-login").permitAll()
	        	.antMatchers(HttpMethod.POST, "/chatroom").hasRole("ADMIN")
	        	.anyRequest().authenticated();
	}*/
	
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }*/
    
    /*@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    /*@Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails stefano =  User.builder()
        .username("stefano")
        .password(bCryptPasswordEncoder().encode("stefano"))
        .roles("STUDENT") //ROLE_STUDENT
        .build();

        UserDetails andrea =  User.builder()
        .username("andrea")
        .password(bCryptPasswordEncoder().encode("andrea"))
        .roles("ADMIN") //ROLE_STUDENT
        .build();
        
        return new InMemoryUserDetailsManager(stefano);
    }*/
}






