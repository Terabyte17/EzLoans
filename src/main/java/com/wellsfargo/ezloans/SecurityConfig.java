package com.wellsfargo.ezloans;

import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

 
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
		requestCache.setMatchingRequestParameterName("continue");
		http
		    .cors(Customizer.withDefaults())
			.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            		.requestMatchers("/api/login/admin", "/api/login/user").permitAll()
            		.requestMatchers("/api/users/update", "/api/users/delete", 
            						 "/api/users", "/api/items", "/api/items/**", //remove purchase item from here
            						 "/api/loans", "/api/loans/**", "/api/purchaseItem").hasAnyRole("ADMIN")
                    .requestMatchers("/**").hasAnyRole("USER")
                    .anyRequest().authenticated())
//            		.httpBasic(Customizer.withDefaults())
//            		.securityContext(securityContext -> securityContext.
//            			      securityContextRepository(new HttpSessionSecurityContextRepository())
//            				)
            .httpBasic((basic) -> basic
                    .addObjectPostProcessor(new ObjectPostProcessor<BasicAuthenticationFilter>() {
                        @Override
                        public <O extends BasicAuthenticationFilter> O postProcess(O filter) {
                            filter.setSecurityContextRepository(new HttpSessionSecurityContextRepository());
                            return filter;
                        }
                    }))
            .requestCache((cache)-> cache
            		.requestCache(requestCache));
	    return http.build();
	}
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
