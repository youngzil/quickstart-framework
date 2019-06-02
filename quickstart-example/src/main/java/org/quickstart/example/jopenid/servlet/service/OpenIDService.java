package org.quickstart.example.jopenid.servlet.service;

import javax.servlet.http.HttpServletRequest;
import org.expressme.openid.Association;
import org.expressme.openid.Authentication;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xsalefter (xsalefter@gmail.com)
 */
public class OpenIDService {

    private Logger logger = LoggerFactory.getLogger(OpenIDService.class);

    public static final String HOME_PAGE = "http://localhost:8080/openid-servlet";
    public static final String LOGGED_IN_URL = "http://localhost:8080/openid-servlet/redirect-after-login";
    public static final String MAC_KEY = "openid_mac";
    public static final String ALIAS = "openid_alias";
    public static final String AUTH_OBJECT = "authentication";
    public static final String PROVIDER_NAME = "provider_name";

    
    public String loggedIn(HttpServletRequest request) {
        final String providerName = request.getParameter("providerId");

        if (providerName == null) {
            RuntimeException re = new RuntimeException("Provider ID parameter is null: " + providerName);
            logger.error(re.toString());
            throw re;
        } else {
            OpenIdManager manager = null;
            Endpoint endpoint = null;

            if (providerName.equals("google")) {
                manager = new OpenIdManager();
                endpoint = manager.lookupEndpoint("Google");
            } else if (providerName.equals("yahoo!")) {
                manager = new OpenIdManager();
                endpoint = manager.lookupEndpoint("Yahoo");
            } else {
                RuntimeException re = new RuntimeException("Unsupported provider: " + providerName);
                logger.error(re.toString());
                throw re;
            }

            Association association = manager.lookupAssociation(endpoint);
            manager.setReturnTo(LOGGED_IN_URL);

            request.getSession().setAttribute(MAC_KEY, association.getRawMacKey());
            request.getSession().setAttribute(ALIAS, endpoint.getAlias());
            request.getSession().setAttribute(PROVIDER_NAME, providerName);

            return manager.getAuthenticationUrl(endpoint, association);
        }
    }


    public void initializeUser(HttpServletRequest request) {
        OpenIdManager manager = new OpenIdManager();

        final byte[] keys = (byte[]) request.getSession().getAttribute(MAC_KEY);
        final String alias = (String) request.getSession().getAttribute(ALIAS);

        manager.setReturnTo(LOGGED_IN_URL);
        Authentication authentication = manager.getAuthentication(request, keys, alias);

        request.getSession().setAttribute("authentication", authentication);
    }


    public String loggedOut(HttpServletRequest request) {
        final String providerName = request.getParameter("providerId");

        if (providerName == null) {
            logger.info("Cannot logged out from the provider. Provider parameter undefined: " + providerName);
            logger.info("Logout action will be in consumer server only.");
            return "http://localhost:8080/openid-servlet/home";
        } else {
            if (providerName.equals("google")) {
                return "https://www.google.com/accounts/Logout";
            } else if (providerName.equals("yahoo!")) {
                return "https://login.yahoo.com/config/login?logout=1";
            } else {
                RuntimeException re = new RuntimeException("Undefined provider name: " + providerName);
                logger.error(re.toString());
                throw re;
            }
        }
    }
}
