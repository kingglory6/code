package com.newer.mall.admin.commodity.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFile implements Runnable {

	private String path;
	private String imageTemp = "G:/image/temp/";
	private String imagePath = "G:/image/upload/";

	public CopyFile(String path) {
		this.path = path;
	}

	@Override
	public void run() {
		String[] paths = path.split("/");
		File f = new File(imageTemp + paths[paths.length - 1]);
		try {
			InputStream is = new FileInputStream(f);
			OutputStream os = new FileOutputStream(imagePath + paths[paths.length - 1]);
			byte[] buf = new byte[1024];
			int bytes=0;
			while((bytes=is.read(buf))>0) {
				os.write(buf,0,bytes);
				os.flush();
			}
			is.close();
			os.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
