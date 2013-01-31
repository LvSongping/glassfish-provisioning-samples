package org.glassfish.provisioning.sample.web.client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DeploySubsystemServlet for deploying subsystem into glassfish
 * 
 * @author TangYong(tangyong@cn.fujitsu.com)
 */
@WebServlet(urlPatterns = "/list.jsp")
public class ListSubsystemServlet extends HttpServlet {
    
    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, java.io.IOException {
       
    }
}
