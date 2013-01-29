package org.glassfish.provisioning.sample.client;

import org.glassfish.obrbuilder.ObrHandlerService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		ServiceReference serviceReference = context.getServiceReference(ObrHandlerService.class.getName());
		ObrHandlerService b = (ObrHandlerService) context.getService(serviceReference);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
	}
}
