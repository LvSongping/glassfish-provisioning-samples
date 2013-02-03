package org.glassfish.provisioning.sample.web.client;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * UndeploySubsystemServlet for undeploying subsystems in glassfish
 * 
 * @author TangYong(tangyong@cn.fujitsu.com)
 */
@WebServlet(urlPatterns = "/UndeploySubsystemServlet")
public class UndeploySubsystemServlet extends HttpServlet {
    
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, java.io.IOException {
    	PrintWriter out = resp.getWriter();
    	
    	resp.setContentType("text/html");
        out.println("<HTML> <HEAD> <TITLE> Glassfish Subsystems Administration </TITLE> </HEAD> ");
        out.println("<BODY BGCOLOR=#FDF5E6>");
        out.println("<H2 ALIGN=\"CENTER\">Glassfish Subsystems Undeployment Page</H2>");
        out.println("<div align=\"center\">");
        out.println("<H4 ALIGN=\"CENTER\">Doing...</H4>");
        out.println("</div>");
        out.println("<div align=\"center\">");
        out.println("<a href=\"index.html\">Backing to Subsystems Administration Page</a>");
        out.println("</div>");
        out.println("</BODY> </HTML> ");
    }
}
