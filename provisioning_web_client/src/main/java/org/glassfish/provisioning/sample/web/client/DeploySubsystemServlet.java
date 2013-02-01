package org.glassfish.provisioning.sample.web.client;

import java.io.File;
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
import org.glassfish.obrbuilder.subsystem.Module;
import org.glassfish.obrbuilder.subsystem.Subsystem;
import org.glassfish.obrbuilder.subsystem.Subsystems;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

/**
 * DeploySubsystemServlet for deploying subsystem into glassfish
 * 
 * @author TangYong(tangyong@cn.fujitsu.com)
 */
@WebServlet(urlPatterns = "/DeploySubsystemServlet")
public class DeploySubsystemServlet extends HttpServlet {

	/**
	 * 
	 */
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
			BundleContext bc = Util.getBundleContext(this.getClass());

			ServiceTracker st = null;

			st = new ServiceTracker(bc, bc.createFilter("(objectClass="
					+ ObrHandlerService.class.getName() + ")"), null);

			st.open();

			ObrHandlerService service = (ObrHandlerService) st
					.waitForService(Util.OBRHADNDLESERVICE_TIMEOUT);

			// parses the request's content to extract file data
			List<?> formItems = upload.parseRequest(req);
			Iterator<?> iter = formItems.iterator();

			// iterates over form's fields
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				// processes only fields that are not form fields
				if (!item.isFormField()) {
					InputStream is = item.getInputStream();
					//Deploying Subsystem
					service.deploySubsystems(is);
					
					is.close();
				}
			}
			
			Subsystems subsystems = service.getCurrentSubsystems();
			
			getServletContext().getRequestDispatcher("/list?subsystemsName=" + subsystems.getName()).forward(req, resp);

		} catch (Exception ex) {
			// ignore
		} finally {
			out.close();
		}
	}
}
