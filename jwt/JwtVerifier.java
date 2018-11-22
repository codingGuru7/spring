/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdac.egov.wamis.security.jwt;

import static com.cdac.egov.wamis.security.jwt.SecurityConstants.HEADER_STRING;
import static com.cdac.egov.wamis.security.jwt.SecurityConstants.SECRET;
import static com.cdac.egov.wamis.security.jwt.SecurityConstants.TOKEN_PREFIX;
import io.jsonwebtoken.Jwts;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author ambarishn
 */
public class JwtVerifier {
    public static UsernamePasswordAuthenticationToken verifySignedJwt(HttpServletRequest request) 
    {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
            return null;
        }
        return null;
    }
}
