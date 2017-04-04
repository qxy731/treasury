package com.soule.base;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Properties;

public class PropertyPlaceholderConfigurerExt extends PropertyPlaceholderConfigurer {
	private String separator = "|";

	protected String resolvePlaceholder(String placeholder, Properties props)
	{
		int separatorIndex = placeholder.indexOf(separator);
		if (separatorIndex == -1)
			return super.resolvePlaceholder(placeholder, props);
		String propKey = placeholder.substring(0, separatorIndex);
		String defPropVal = placeholder.substring(separatorIndex + 1);
		return props.getProperty(propKey, defPropVal);
	}

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

}
