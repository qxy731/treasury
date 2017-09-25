package com.soule.data.tools;

import java.util.List;
import java.util.Map;

import com.soule.comm.file.IValueHandler;
import com.soule.comm.file.config.Field;

public class TarDateValueHandler implements IValueHandler {
	
	@Override
	public Object special(@SuppressWarnings("rawtypes") Map context, List<Field> list, String[] values, int i)  {
		return context.get("date").toString();
	}
	
}
