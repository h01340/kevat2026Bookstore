package k26.bookstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig  {



 	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 


	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests( authorize -> authorize
				.requestMatchers("/", "/login").permitAll()    // / require any authentication.
				.anyRequest().authenticated()        // All other paths must be authenticated.
			)                                        
		.formLogin( formlogin -> formlogin        // your applicationis using SB's default login page.
			.defaultSuccessUrl("/booklist", true)      // <-- Tells where to go after successful login
			.permitAll()                                  
		)
		.logout( logout -> logout
			.permitAll()
		);
		return http.build();
	}
}

