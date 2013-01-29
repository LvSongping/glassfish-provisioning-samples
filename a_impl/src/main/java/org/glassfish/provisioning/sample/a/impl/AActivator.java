package org.glassfish.provisioning.sample.a.impl;

import org.glassfish.provisioning.sample.a.api.AIntf;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class AActivator implements BundleActivator {

	private ServiceRegistration srg = null;
	
	@Override
	public void start(BundleContext context) throws Exception {
		AImpl a = new AImpl();
		srg = context.registerService(AIntf.class.getName(), a, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (srg != null){
			srg.unregister();
		}
	}
}
