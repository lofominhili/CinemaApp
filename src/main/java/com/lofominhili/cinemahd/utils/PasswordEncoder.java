package com.lofominhili.cinemahd.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PasswordEncoder {
    public String generate() {
        StringBuilder key = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i <= 34; i++)
            key.append((char) ('a' + random.nextInt(0, 25)));
        return key.toString();
    }
}
