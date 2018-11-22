/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdac.egov.wamis.security.utilities;

import com.cdac.egov.wamis.security.jwt.JwtFacade;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author ambarishn
 */
public class WamisIntegerDeserializer extends JsonDeserializer<Integer>
{

    @Override
    public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException 
    {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        
        String encryptedValue = jsonParser.getText();
        String decryptedValue = AES.decrypt(encryptedValue, new JwtFacade(request).getSubject().orElse("masterPassword"));
        return Integer.parseInt(decryptedValue);
    }
    
}
