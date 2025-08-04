package com.durgasoft.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

@Component
public class JWTService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry.duration}")
    private int expiry;

    private Algorithm algorithm;

//    @PostConstruct
//    public void postConstruct() throws UnsupportedEncodingException {
//        algorithm = Algorithm.HMAC256(algorithmKey);
//    }
//
//    public String generateToken(String username){
//        return JWT.create()
//                .withClaim("name",username)
//                .withIssuer(issuer)
//                .withExpiresAt(new Date(System.currentTimeMillis() + expiry))
//                .sign(algorithm);
//    }
//}

@PostConstruct
public void init() throws UnsupportedEncodingException {
if(algorithmKey == null || algorithmKey.isEmpty()){
    throw new IllegalStateException("JWT algorithm key must not be null or empty");
}
algorithm = Algorithm.HMAC256(algorithmKey);

}

public String generateToken(String username){
    if(username==null || username.isEmpty()){
        throw new IllegalArgumentException("User must not be null or empty");
    }
    return JWT.create()
            .withClaim("name",username)
            .withIssuer(issuer)
            .withExpiresAt(new Date(System.currentTimeMillis() + expiry *1000L))
            .sign(algorithm);
}

// once you write the jwtfilter code then use below method to extract the username decrypt and verify the token

    public String getUsername(String username){
        DecodedJWT decodedJWT = JWT.
                require(algorithm)
                .withIssuer(issuer)
                 .build()
                .verify(username);
        return decodedJWT.getClaim("name").asString();

    }




}
