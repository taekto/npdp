package com.project.npdp.utils;

import java.security.MessageDigest;

public class SHA256Util {

    public static String getSHA256(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 바이트 배열로 변환한 후 sha-256해시 계산
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            // 각 바이트를 2자리의 16진수로 변환
            for(byte b : hash){
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
