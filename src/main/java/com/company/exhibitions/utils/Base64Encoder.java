package com.company.exhibitions.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Encoder {

    public String encode(String text){
        byte[] message = text.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(message);
    }

    public String decode(String code){
        byte[] decoded = Base64.getDecoder().decode(code);
        return new String(decoded, StandardCharsets.UTF_8);
    }
}
