/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdac.egov.wamis.security.jwt;

import static com.cdac.egov.wamis.security.jwt.SecurityConstants.EXPIRATION_TIME;
import static com.cdac.egov.wamis.security.jwt.SecurityConstants.HEADER_STRING;
import static com.cdac.egov.wamis.security.jwt.SecurityConstants.SECRET;
import static com.cdac.egov.wamis.security.jwt.SecurityConstants.TOKEN_PREFIX;
import com.cdac.egov.wamis.security.wamisuser.WamisUser;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

/**
 *
 * @author ambarishn
 */
public class JwtGenerator {
    public static void generateSignedJwt(HttpServletRequest req, HttpServletResponse res, Authentication auth) 
    {
        JwtBuilder jwtBuilder = Jwts.builder();
        assignClaims(jwtBuilder, auth);
        signJwt(jwtBuilder);
        returnJws(jwtBuilder, res);
        
//        String token = Jwts.builder()
//                .setSubject(((User) auth.getPrincipal()).getUsername())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(SECRET,SignatureAlgorithm.HS512)
//                .compact();
        
//        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        
    }
    
    private static void assignClaims(JwtBuilder jwtBuilder, Authentication auth) {
        WamisUser wamisUser = (WamisUser)auth.getPrincipal();
        
        jwtBuilder.setId(wamisUser.getId().toString())
                  .setSubject(wamisUser.getUsername())
                  .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        jwtBuilder.claim("officeId", "197");
        
    }
    
    private static void signJwt(JwtBuilder jwtBuilder) {
        jwtBuilder.signWith(SECRET,SignatureAlgorithm.HS512);
    }
    
    private static void returnJws(JwtBuilder jwtBuilder, HttpServletResponse res) {
        String jwtToken = jwtBuilder.compact();
        res.addHeader(HEADER_STRING, TOKEN_PREFIX + jwtToken);
    }
}
