package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.models.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {
    private static final String SECRET_KEY="6f0cbe38cad6139cfa2ec26f19faeefd022b3cbef7f1b45b1c557281cdff44fde3b05090203778b01ba9fc3b800333062c083cd63d19a410e3cdc300e18d73aae5958e4cec551e3c83a009dce784b219";
    private static final String ISSUER="katame-api";
    public String generateToken(UserDetails user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            String role=user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .findFirst()
                    .orElse("ROLE_CUSTOM");
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())//Define o emissor do token
                    .withExpiresAt(expirationDate())//Define a data de expiração do token
                    .withSubject(user.getUsername())//Define assunto do token
                    .withClaim("role",role)
                    .sign(algorithm);//Assina o token
        }catch(JWTCreationException e){
            throw new JWTCreationException("Erro ao gerar token.", e);
        }
    }

    public String getSubject(String token){
        try{
            Algorithm algoritm=Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algoritm)
                    .withIssuer(ISSUER)//Define o emissor do token
                    .build()
                    .verify(token)//Verifica a validade do token
                    .getSubject();//Obtém o assunto(nome do usuário)
        }catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token inválido ou expirado.",exception);
        }
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(4).toInstant();
    }

    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
    }
}
