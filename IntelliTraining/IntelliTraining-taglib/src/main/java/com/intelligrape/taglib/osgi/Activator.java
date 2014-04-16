package com.intelligrape.taglib.osgi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.squeakysand.osgi.framework.BasicBundleActivator;

/**
 * Bundle activator for IntelliTraining - IntelliTraining-taglib.
 */
public class Activator extends BasicBundleActivator {

    private static final Logger LOG = LoggerFactory.getLogger(Activator.class);

    public Activator() {
		super(LOG);
	}

}
