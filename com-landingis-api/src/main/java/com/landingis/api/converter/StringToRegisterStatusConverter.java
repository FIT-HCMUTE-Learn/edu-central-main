package com.landingis.api.converter;

import com.landingis.api.enumeration.RegisterStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRegisterStatusConverter implements Converter<String, RegisterStatus> {
    
    @Override
    public RegisterStatus convert(String source) {
        try {
            return RegisterStatus.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid RegisterStatus value: " + source);
        }
    }
}
