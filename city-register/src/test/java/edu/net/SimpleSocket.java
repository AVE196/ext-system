package edu.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class SimpleSocket {

	@Test
	public void simpleSocket() throws UnknownHostException, IOException {
		
		Socket sc = new Socket("java-course.ru", 80);
		InputStream is = sc.getInputStream();
		OutputStream os = sc.getOutputStream();
		
		String command = "GET /sitemap.xml HTTP/1.1\r\nHost:java-course.ru\r\n\r\n";
		os.write(command.getBytes());
		
		int i = 0;
		while ( (i = is.read()) != -1) {
			System.out.print((char)i);
		}
		
		sc.close();
	
	}
	
}
