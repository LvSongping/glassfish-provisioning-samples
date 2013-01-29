package org.glassfish.provisioning.sample.b.impl;

import org.glassfish.provisioning.sample.b.api.BIntf;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class BActivator implements BundleActivator {

	private ServiceRegistration srg = null;

	@Override
	public void start(BundleContext context) throws Exception {
		BImpl b = new BImpl(context);
		srg = context.registerService(BIntf.class.getName(), b, null);
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		if (srg != null) {
			srg.unregister();
		}
	}
}
