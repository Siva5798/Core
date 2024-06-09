package com.superkicks.core.services.impl;



import com.superkicks.core.services.MySimpleService;

import org.apache.commons.lang3.StringUtils;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceRanking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = MySimpleService.class,immediate = true, name = "serviceF")
@ServiceRanking(1000)
public class MySimpleServiceFirstImpl implements MySimpleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MySimpleServiceFirstImpl.class);

	public String demoString(String str) {
		return StringUtils.upperCase(str);
	}

	@Activate
	protected void activate(BundleContext bc) {
		LOGGER.info("My simple service First activated");
	}
}
