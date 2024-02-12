package com.sipra.blogapplication.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1. get token
        String requestToken = request.getHeader("Authorization");
        String username=null;
        String token = null;
        if(requestToken!=null && requestToken.startsWith("Bearer")){
            token = requestToken.substring(7);
            try {
                username = jwtTokenHelper.getUsernameFromToken(token);
            }catch (IllegalArgumentException ex){
                System.out.println("unable to get token");
            }catch (ExpiredJwtException ex){
                System.out.println("Jwt token expired");
            }catch (MalformedJwtException ex){
                System.out.println("Invalid token");
            }
        }else{
            System.out.println("It is not validate token");
        }
        //once we get the token we validate that
        if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtTokenHelper.validateToken(token, userDetails)){
                //then authenticate
                UsernamePasswordAuthenticationToken authenticationToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }else{
                System.out.println("Invalid jwt token");
            }
        }else{
            System.out.println("User name is null or centext is not null");
        }
        filterChain.doFilter(request,response);
    }
}
