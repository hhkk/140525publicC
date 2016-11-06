package com.ustodo.utilg

import com.ustodo.utilj.Ozz;

class UtilStreams {



	// 		Reader in = new InputStreamReader(is, "UTF-8");
	public String convertStreamToString_1(InputStream is) {
//		final char[] buffer = new char[0x10000];
//		StringBuilder out = new StringBuilder();
//		int read;
//		do {
//			read = in.read(buffer, 0, buffer.length);
//			if (read>0) {
//				out.append(buffer, 0, read);
//			}
//		} while (read>=0);
	}



	public String convertStreamToString_2(InputStream is) {
		StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer, encoding);
		String theString = writer.toString();
	}




}
