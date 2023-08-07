package com.project.npdp.snslogin.service;

import com.project.npdp.member.entity.Member;

import java.io.UnsupportedEncodingException;

public interface OAuthProviderService <T>{
    String getAuthorizeUrl() throws UnsupportedEncodingException;
    T getToken(String code);
    Member getMemberInfo(T token);
}
