package org.quickstart.example.oauth2.authorization.code;

import org.quickstart.example.oauth2.authorization.code.OAuthConstants;
import org.quickstart.example.oauth2.authorization.code.OAuthUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class OAuth2Client
 */
public class OAuth2Client extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OAuth2Client() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String caller = request.getParameter(OAuthConstants.CALLER);
		String code = request.getParameter(OAuthConstants.CODE);
		
		//Load the properties file
		Properties config = OAuthUtils.getClientConfigProps(OAuthConstants.CONFIG_FILE_PATH);
				
		//Generate the OAuthDetails bean from the config properties file
		OAuth2Details oauthDetails = OAuthUtils.createOAuthDetails(config);
				
		//Validate Input
		List<String> invalidProps = OAuthUtils.validateInput(oauthDetails);
		if(invalidProps!=null && invalidProps.size() == 0){
			//Validation successful
			
			if(OAuthUtils.isValid(caller)){
				//Request was sent from web application. 
				//Check type of request
				if(caller.equalsIgnoreCase(OAuthConstants.TOKEN)){
					//Request for Access token
					oauthDetails.setAccessTokenRequest(true);
					String location = OAuthUtils.getAuthorizationCode(oauthDetails);
					
					//Send redirect to location returned by endpoint
					response.sendRedirect(location);
					return;
				}
				else{
					//Request for accessing protected resource
					if(!OAuthUtils.isValid(oauthDetails.getResourceServerUrl())){
						invalidProps.add(OAuthConstants.RESOURCE_SERVER_URL);
						
					}
					
					if(!OAuthUtils.isValid(oauthDetails.getAccessToken())){
						if(!OAuthUtils.isValid(oauthDetails.getRefreshToken())){
							invalidProps.add(OAuthConstants.REFRESH_TOKEN);
						}
						
						
					}
					
					
					if(invalidProps.size() > 0){
						sendError(response, invalidProps);
						return;
					}
					
					Map<String,String> map = OAuthUtils.getProtectedResource(oauthDetails);
					response.getWriter().println(new Gson().toJson(map));
					return;
				}
			}
			else if(OAuthUtils.isValid(code)){
				//Callback from endpoint with code.
				Map<String,String> map = OAuthUtils.getAccessToken(oauthDetails, code);
				response.getWriter().println(new Gson().toJson(map));
				return;
			}
			else{
				//Invalid request/error response
				String queryString = request.getQueryString();
				String error = "Invalid request";
				if(OAuthUtils.isValid(queryString)){
					//Endpoint returned an error
					error = queryString;
				}
				response.getWriter().println(error);
				return;
				
			}
		}
		else{
			//Input is not valid. Send error
			sendError(response, invalidProps);
			return;
			
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void sendError(HttpServletResponse response, List<String> invalidProps) throws IOException{
		PrintWriter pw = response.getWriter();
		
		pw.println("<HTML>");
		pw.println("<HEAD>");
		pw.println("<H1>");
		pw.println("Invalid Input in config file Oauth2Client.config");
		pw.println("</H1>");
		pw.println("</HEAD>");
		pw.println("<BODY>");
		pw.println("<BODY>");
		pw.println("<P>");
		pw.println("Please provide valid values for the following properties");
		pw.println(new Gson().toJson(invalidProps));
		pw.println("</P>");
		pw.println("</HTML>");
		
		pw.flush();
		pw.close();
		
		return;
	}

}
