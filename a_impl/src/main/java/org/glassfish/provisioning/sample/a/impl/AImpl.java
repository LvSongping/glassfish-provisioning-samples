package org.glassfish.provisioning.sample.a.impl;

import org.glassfish.provisioning.sample.a.api.AIntf;

public class AImpl implements AIntf{

	@Override
	public String sayA() {
		return "Hello A";
	}
}
