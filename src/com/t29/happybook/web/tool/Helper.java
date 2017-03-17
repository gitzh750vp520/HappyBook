package com.t29.happybook.web.tool;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

	
	public static String getFileNameOfCurrTime(String ext) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS.");
		return sdf.format(new Date()) + ext;
	}
	
}
