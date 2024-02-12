package com.sipra.blogapplication.security;

import com.sipra.blogapplication.config.AppConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper {


    //retrieve username from token
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }
    //retrieve Expiration from token
    public Date getExpirationFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }
    //retrieve claim from token
    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims =getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    //for retrieving any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(AppConstants.JWT_SECRET_KEY)).build().parseSignedClaims(token).getPayload();

    }
    //check if the token is expired
    private Boolean isTokenExpire(String token){
        final Date expiration = getExpirationFromToken(token);
        return expiration.before(new Date());
    }
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername());
    }
    //while creating the token -
    //1.define claims of the token, like issuer, expiration,subject,and the id
    //2.sign the jwt using the HSS12 algorithm and secret key
    //3.according to jws compact serialization (https://tools.ietf.org/html/draft-ieft-jose-j
    //compaction of the jwt to a safe string
    private String doGenerateToken(Map<String,Object> claims,String subject){
        return Jwts.builder().claims(claims).subject(subject).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ AppConstants.JWT_TOKEN_VALIDITY*100))
                .signWith(Keys.hmacShaKeyFor(AppConstants.JWT_SECRET_KEY)).compact();

    }
    //Validate token
    public Boolean validateToken(String token,UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&&!isTokenExpire(token));
    }

}
