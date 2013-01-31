package org.glassfish.provisioning.sample.web.client;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleReference;

public class Util {
	
	 public static final long OBRHADNDLESERVICE_TIMEOUT = 10000; // in ms
	
	public static BundleContext getBundleContext(Class<?> clazz) {
        BundleContext bc = null;
        try {
            bc = BundleReference.class
                            .cast(clazz.getClassLoader())
                            .getBundle().getBundleContext();
        } catch (ClassCastException cce) {
            throw cce;
        }
        
        return bc;
    }
}
