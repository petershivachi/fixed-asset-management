package com.fixed.assets.app.fixedassets.Configs.providers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

public class CustomAuthProvider implements AuthenticationProvider {
  @Resource
  UserDetailsService userDetailsService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    final String username = (authentication.getPrincipal() == null) ? "NON_PROVIDED" : authentication.getName();
    if(StringUtils.isEmpty(username)){
      throw new BadCredentialsException("Invalid credentials");
    }

    // get user details using Spring security user details service
    UserDetails user = null;

    try{
      user = userDetailsService.loadUserByUsername(username);
    }catch (UsernameNotFoundException exception){
      throw new BadCredentialsException("Invalid credentials");
    }

    return createSuccessfulAuthentication(authentication, user);
  }

  private Authentication createSuccessfulAuthentication(final Authentication authentication, final UserDetails user) {
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), authentication.getCredentials(), user.getAuthorities());
    token.setDetails(authentication.getDetails());
    return token;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return false;
  }
}
