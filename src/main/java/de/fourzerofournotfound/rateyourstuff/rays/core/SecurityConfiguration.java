package de.fourzerofournotfound.rateyourstuff.rays.core;

import de.fourzerofournotfound.rateyourstuff.rays.security.JwtRequestFilter;
import de.fourzerofournotfound.rateyourstuff.rays.services.users.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserDetailService appUserDetailService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
        .csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * Method configure which URLs been ignored by the Spring Security filter
     * @param web       the web security instance
     * @throws Exception if it is not possible to ignore urls
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        try{
            web.ignoring()
                    .antMatchers("/rest/**", "/images/media/**", "/user/add", "/user/check/**", "/swagger-ui/**", "/v3/api-docs/**", "/login/check/**");
        } catch(Exception e) {
            throw new Exception("Cant ignore URL", e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


