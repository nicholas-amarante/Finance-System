package com.example.demo.security;

import com.example.demo.models.User;
import com.example.demo.models.UserDetailsImpl;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Component
public class UserAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=recoveryToken(request);//Recupera o token do cabeçalho Authorization
        if (checkIfEndpointIsNotPublic(request)) {
            if (token != null) {
                try {
                    String subject = jwtTokenService.getSubject(token);//Obtem o assunto do token
                    Optional<User> userOptional = userRepository.findByEmail(subject);
                    if (userOptional.isPresent()) {
                        User user = userOptional.get();
                        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user);
                        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetailsImpl, null, userDetailsImpl.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                } catch (Exception e) {
                    System.err.println("Erro de validação de token" + e.getMessage());
                }
            }
        }
        filterChain.doFilter(request,response);//Continua o processamento da requisição
    }

    private String recoveryToken(HttpServletRequest request) {
        String authorizationHeader=request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }

    private boolean checkIfEndpointIsNotPublic(HttpServletRequest request) {
        String requestURI=request.getRequestURI();
        return !Arrays.asList(SecurityConfig.ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED).contains(requestURI);
    }
}
