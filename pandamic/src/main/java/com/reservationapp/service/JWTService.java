package com.reservationapp.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JWTService {

    @Value("${jwt.algorithm.key")
    private  String algorithmkey;
    @Value("${jwt.issuer")
    private String  issuer;

    @Value("jwt.expiryDuration")
    private int expiryTIme;

    private Algorithm algorithm;

    private final static String USER_NAME="username";

    @PostConstruct
    public void postConstruct(){
      algorithm= Algorithm.HMAC256(algorithmkey);

    }

    public String generateToken(PropertyUser user){
      return  JWT.create()
                .withClaim(USER_NAME,user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTIme))
                .withIssuer(issuer)
                .sign(algorithm);

    }

    // verify the token and return if valid
    public String getNametoken(String token){
       DecodedJWT decodedJWT = JWT.require((algorithm).withIssuer(issuer).build().verify(token);
       return decodedJWT.getClaim(USER_NAME).asString());

    }
}


