package org.glassfish.provisioning.sample.c.impl;

import org.glassfish.provisioning.sample.c.api.CIntf;

public class CImpl implements CIntf{

	@Override
	public String sayC() {
		return "Hello C!";
	}
}
