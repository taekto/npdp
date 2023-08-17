package com.project.npdp.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    MEMBER("ROLE_MEMBER", "일반 사용자"),
    ADMIN("ROLE_ADMIN", "관리자"),
    SUPER_ADMIN("ROLE_SUPER_ADMIN", "최고 관리자");

    private final String key;
    private final String title;
}
