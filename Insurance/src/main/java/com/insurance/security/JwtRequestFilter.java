package com.insurance.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;



import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private InsuranceUserDetailsService jwtUserDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenUtil;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {


		final String requestTokenHeader = request.getHeader("Authorization");

		String userName = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				userName = jwtTokenUtil.getUserNameFromToken(jwtToken);
				
				
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			} catch (MalformedJwtException e) {
								
				System.out.println("Invalid jwt");
				
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		// Once we get the token validate it.
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(userName);

			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}else {
				
				System.out.println("Invalid jwt token");
				
			}
			
		}else {
			
			System.out.println("User-name is null or context is not null");
			
		}
		filterChain.doFilter(request, response);
	}
		
	}


