/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cdac.egov.wamis.security.utilities;

import com.cdac.egov.wamis.security.jwt.JwtFacade;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author ambarishn
 */

public class WamisIntegerSerializer extends JsonSerializer<Integer>
{   
    @Override
    public void serialize(Integer value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException 
    {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String encryptedValue = AES.encrypt(value.toString(), new JwtFacade(request).getSubject().orElse("masterPassword"));
        jsonGenerator.writeString(encryptedValue);
    }
}
