package org.glassfish.provisioning.sample.web.client;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.glassfish.obrbuilder.ObrHandlerService;
import org.glassfish.obrbuilder.subsystem.Subsystems;

/**
 * DeploySubsystemsServlet for deploying subsystems into glassfish
 * 
 * @author TangYong(tangyong@cn.fujitsu.com)
 */
@WebServlet(urlPatterns = "/DeploySubsystemsServlet")
public class DeploySubsystemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {

		// checks if the request actually contains upload file
		if (!ServletFileUpload.isMultipartContent(req)) {
			// if not, we stop here
			return;
		}

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		// configures some settings
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(THRESHOLD_SIZE);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(REQUEST_SIZE);

		try {
			ObrHandlerService service = Util.getObrHandlerService();

			// parses the request's content to extract file data
			List<?> formItems = upload.parseRequest(req);
			Iterator<?> iter = formItems.iterator();

			Subsystems subsystems = null;
			
			// iterates over form's fields
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				// processes only fields that are not form fields
				if (!item.isFormField()) {
					InputStream is = item.getInputStream();
					//Deploying Subsystem
					subsystems = service.deploySubsystems(is);
					
					is.close();
				}
			}
			
			getServletContext().getRequestDispatcher("/ListSubsystemsServlet?subsystemsName=" + subsystems.getName()).forward(req, resp);
			
		} catch (Exception ex) {
			outputDeployfailedInfo(out);
		} finally {
			out.close();
		}
	}

	private void outputDeployfailedInfo(PrintWriter out) {
		out.println("<HTML> <HEAD> <TITLE> Glassfish Subsystems Administration </TITLE> </HEAD> ");
		 out.println("<BODY BGCOLOR=#FDF5E6>");
		 out.println("<H2 ALIGN=\"CENTER\">Glassfish Subsystems Deployment Page</H2>");
		 out.println("<div align=\"center\">");
		 out.println("Subsystems Deployment failed!");
		 out.println("</div>");
		 out.println("<div align=\"center\">");
		 out.println("<a href=\"deploy.jsp\">Backing to Subsystems Deployment Page</a>");
		 out.println("</div>");
		 out.println("<div align=\"center\">");
		 out.println("<a href=\"index.html\">Backing to Subsystems Administration Page</a>");
		 out.println("</div>");
		 out.println("<br>");
	}
}
