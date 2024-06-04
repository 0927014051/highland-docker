package com.javaweb.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenValidator extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		String jwt = request.getHeader(JwtConstant.JWT_HEADER);
		System.out.println("jwt --> " + jwt);
	
		if (jwt != null) {
			jwt = jwt.substring(7);
			System.out.println("jwt --> " + jwt);
			try {
				SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
				Jws<Claims> claimsJws = Jwts.parserBuilder()
						.setSigningKey(key)
						.build()
						.parseClaimsJws(jwt);
	
				Claims claims = claimsJws.getBody();
				System.err.println("claims: " + claims);
				String email = String.valueOf(claims.get("user_id"));
	
				String authorities = String.valueOf(claims.get("authorities"));
				System.out.println("Authorities from claims: " + authorities);
	
				List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);
	
				SecurityContextHolder.getContext().setAuthentication(authentication);
	
			} catch (Exception e) {
				throw new BadCredentialsException("Invalid or expired token");
			}
		}
		filterChain.doFilter(request, response);
	}
	
}
