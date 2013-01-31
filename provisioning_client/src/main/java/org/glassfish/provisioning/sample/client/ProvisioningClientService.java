package org.glassfish.provisioning.sample.client;

import org.glassfish.obrbuilder.ObrHandlerService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public class ProvisioningClientService extends ServiceTracker{
	
	public ProvisioningClientService(BundleContext bctx){
		super(bctx, ObrHandlerService.class.getName(), null);
	}
	
	@Override
	public Object addingService(ServiceReference reference) {
		if (this.getTrackingCount() == 1) {
			return null; // we are not tracking this
		}
		ObrHandlerService obrHandler = (ObrHandlerService) context
				.getService(reference);
		
		//Temp Testing
		String subSystemPath = "d:/provisioning-sample/subsystems.xml";

		obrHandler.deploySubsystems(subSystemPath);
				
		return super.addingService(reference);
	}
	
	@Override
	public void remove(ServiceReference reference) {
		super.remove(reference);
	}
}
