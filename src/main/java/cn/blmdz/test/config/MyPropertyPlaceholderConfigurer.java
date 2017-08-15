package cn.blmdz.test.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		System.out.println(propertyName + ": " + propertyValue);
		return convertPropertyValue(propertyValue);
	}

}
