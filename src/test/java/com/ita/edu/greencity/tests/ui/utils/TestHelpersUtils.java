package com.ita.edu.greencity.tests.ui.utils;

import org.apache.tika.langdetect.OptimaizeLangDetector;
import org.apache.tika.language.detect.LanguageDetector;

import java.util.Random;

public class TestHelpersUtils {

    public static String getLanguage(String text) {
        LanguageDetector detector = new OptimaizeLangDetector().loadModels();
        return detector
                .detectAll(text)
                .get(0)
                .getLanguage();
    }

    public static String generateRandomComment(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                 +"lmnopqrstuvwxyz!@#$%& ";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
}
