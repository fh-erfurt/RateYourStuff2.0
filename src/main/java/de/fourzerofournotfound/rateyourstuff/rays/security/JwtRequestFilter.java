package de.fourzerofournotfound.rateyourstuff.rays.security;

import de.fourzerofournotfound.rateyourstuff.rays.services.users.AppUserDetailService;
import de.fourzerofournotfound.rateyourstuff.rays.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JwtRequestFilter
 * This Class provides Methods to filter required information out of the JWT-Token if a user want to authenticate
 * for requests to get data which only is available for several roles or users
 */

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private AppUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * This Method decodes the given JWT-Token and compares the given username and the decoded username
     *
     * @param httpServletRequest  creates an HttpServletRequest object and passes it as an argument to the servlet's service methods
     * @param httpServletResponse creates an HttpServletResponse object and passes it as an argument to the servlet's service methods
     * @param filterChain         is used by Filters to invoke the next filter in the Chain
     * @throws ServletException if the servlets encounters difficulty
     * @throws IOException      if there is anything different with In/ or Output
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        String username = null;
        String jwt = null;

        int prefixLength = 7;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(prefixLength);
            username = jwtUtil.extractUsername(jwt);
        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}