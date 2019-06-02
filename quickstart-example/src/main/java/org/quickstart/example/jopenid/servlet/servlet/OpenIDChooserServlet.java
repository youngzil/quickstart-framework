package org.quickstart.example.jopenid.servlet.servlet;

import java.io.IOException;
import java.rmi.ServerException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.expressme.openid.Association;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdManager;

/**
 * @author xsalefter
 */
public class OpenIDChooserServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String provider = request.getParameter("providerId");

        if (provider == null) {
            throw new ServletException("Unknwon provider: " + provider);
        } else {
            OpenIdManager manager = new OpenIdManager();
            manager.setReturnTo("http://localhost:8080/openid-servlet/display");
            Endpoint endpoint = null;
            
            if (provider.equals("google")) 
                endpoint = manager.lookupEndpoint("Google");
            else if (provider.equals("yahoo!"))
                endpoint = manager.lookupEndpoint("Yahoo");
            else
                throw new ServerException("Unknown provider: " + provider);

            Association association = manager.lookupAssociation(endpoint);
            request.getSession().setAttribute("openid_mac", association.getRawMacKey());
            request.getSession().setAttribute("openid_alias", endpoint.getAlias());
            String url = manager.getAuthenticationUrl(endpoint, association);
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
