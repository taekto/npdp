package com.project.npdp.snslogin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleTokenInfo {
    private String accessToken;
    private String idToken;
}
