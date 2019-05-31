package org.quickstart.example.oauth2;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuth2Details {

	private String scope;
	private String grantType;
	private String clientId;
	private String clientSecret;
	private String accessToken;
	private String refreshToken;
	private String username;
	private String password;
	private String authenticationServerUrl;
}
