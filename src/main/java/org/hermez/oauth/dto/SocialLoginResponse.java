package org.hermez.oauth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SocialLoginResponse {
    private String accessToken;
    private String refreshToken;
}
