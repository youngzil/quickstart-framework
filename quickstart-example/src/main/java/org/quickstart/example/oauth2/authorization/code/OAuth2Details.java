package org.quickstart.example.oauth2.authorization.code;
public class OAuth2Details {

	private String scope;
	private String state;
	private String grantType;
	private String clientId;
	private String clientSecret;
	private String accessToken;
	private String refreshToken;
	private String approvalPromptKey;
	private String approvalPromptValue;
	private String accessTypeKey;
	private String accessTypeValue;
	private String redirectURI;
	private String username;
	private String password;
	private String authenticationServerUrl;
	private String tokenEndpointUrl;
	private String resourceServerUrl;
	private boolean isAccessTokenRequest;
	
	
	
	public String getTokenEndpointUrl() {
		return tokenEndpointUrl;
	}
	public void setTokenEndpointUrl(String tokenEndpointUrl) {
		this.tokenEndpointUrl = tokenEndpointUrl;
	}
	public String getRedirectURI() {
		return redirectURI;
	}
	public void setRedirectURI(String redirectURI) {
		this.redirectURI = redirectURI;
	}
	public String getAccessTypeKey() {
		return accessTypeKey;
	}
	public void setAccessTypeKey(String accessTypeKey) {
		this.accessTypeKey = accessTypeKey;
	}
	public String getAccessTypeValue() {
		return accessTypeValue;
	}
	public void setAccessTypeValue(String accessTypeValue) {
		this.accessTypeValue = accessTypeValue;
	}
	public String getApprovalPromptKey() {
		return approvalPromptKey;
	}
	public void setApprovalPromptKey(String approvalPromptKey) {
		this.approvalPromptKey = approvalPromptKey;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getApprovalPromptValue() {
		return approvalPromptValue;
	}
	public void setApprovalPromptValue(String approvalPromptValue) {
		this.approvalPromptValue = approvalPromptValue;
	}
	
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getAuthenticationServerUrl() {
		return authenticationServerUrl;
	}
	public void setAuthenticationServerUrl(String authenticationServerUrl) {
		this.authenticationServerUrl = authenticationServerUrl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAccessTokenRequest() {
		return isAccessTokenRequest;
	}
	public void setAccessTokenRequest(boolean isAccessTokenRequest) {
		this.isAccessTokenRequest = isAccessTokenRequest;
	}
	public String getResourceServerUrl() {
		return resourceServerUrl;
	}
	public void setResourceServerUrl(String resourceServerUrl) {
		this.resourceServerUrl = resourceServerUrl;
	}
}
