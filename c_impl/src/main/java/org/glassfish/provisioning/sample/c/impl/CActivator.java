package org.glassfish.provisioning.sample.c.impl;

import org.glassfish.provisioning.sample.b.api.BIntf;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class CActivator implements BundleActivator {

	@Override
	public void start(BundleContext context) throws Exception {
		ServiceReference serviceReference = context.getServiceReference(BIntf.class.getName());
	    BIntf b = (BIntf) context.getService(serviceReference);
	    
	    CImpl c =  new CImpl();
	    
		System.out.println(b.sayB() + " And " + c.sayC());
	}

	@Override
	public void stop(BundleContext context) throws Exception {
	}
}
