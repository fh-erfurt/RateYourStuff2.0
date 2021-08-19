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

/**
 * Configuration settings and methods of Spring-Security for a Java-Web-Token - technology
 * and Role-Based-PreAuthorization in every controller
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserDetailService appUserDetailService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    private AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * Bean which returns authentication manager bean
     *
     * @return bean of authentication manager
     * @throws Exception if super class isn´t valid
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Reconfigure and declare the UserDetailService and the PasswordEncoder
     *
     * @param auth given object from spring security´s AuthenticationManagerBuilder
     * @throws Exception if appUserService isn´t valid
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * Reconfigure the Authorizations for the HttpSecurity of Spring Security
     * In this case this method only provides an authentication Point with its own Exception
     * The Permission for the authenticate path
     * The setting to make Spring Sessions stateless
     *
     * @param http the hyper-text-transfer-protocol of security instance
     * @throws Exception if http object is invalid
     */
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
     * Reconfiguration of which URLs been ignored by the Spring Security filter
     *
     * @param web the web security instance
     * @throws Exception if it is not possible to ignore urls
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        try {
            web.ignoring()
                    .antMatchers("/rest/**", "/images/media/**", "/user/add", "/user/check/**", "/swagger-ui/**", "/v3/api-docs/**", "/login/check/**");
        } catch (Exception e) {
            throw new Exception("Cant ignore URL", e);
        }
    }

    /**
     * This Method provides the Bean to the PasswordEncoder but not the encoder itself
     *
     * @return a new instance of the BCrypt Password Encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


