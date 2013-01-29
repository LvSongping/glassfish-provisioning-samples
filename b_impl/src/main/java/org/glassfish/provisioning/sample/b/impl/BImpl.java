package org.glassfish.provisioning.sample.b.impl;

import org.glassfish.provisioning.sample.a.api.AIntf;
import org.glassfish.provisioning.sample.b.api.BIntf;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class BImpl implements BIntf{
	
	private BundleContext bctx = null;
			
	public BImpl(BundleContext bctx){
		this.bctx = bctx;
	}

	@Override
	public String sayB() {
		ServiceReference serviceReference = this.bctx.getServiceReference(AIntf.class.getName());
	    AIntf a = (AIntf) this.bctx.getService(serviceReference);
	    
		return a.sayA() + " And " + "Hello B";
	}
}
