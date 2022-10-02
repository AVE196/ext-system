package edu.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Server {
	
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		
		Map<String, Greetable> handlers = buildHendlers();
		
		ServerSocket sc = new ServerSocket(12345);
		
		System.out.println("Server is started");
		
		while(true) {
			Socket client = sc.accept();
			
			SimpleServer ss = new SimpleServer(client, handlers);
			ss.start();
		}
		
	}

	private static Map<String, Greetable> buildHendlers()  {
		Map<String, Greetable> result = new HashMap<>();
		try (InputStream is = Server.class.getClassLoader().getResourceAsStream("greetable.properties")) {
		Properties prop = new Properties();
		prop.load(is);
		for(Object command: prop.keySet()) {
			Class<Greetable> cl = (Class<Greetable>)Class.forName(prop.getProperty(command.toString()));
			Greetable hadler  = cl.getConstructor().newInstance();
			result.put(command.toString(), hadler);
		}
		} catch (Exception ex) {
			ex.printStackTrace();		}
		
		return result;
	}
}

class SimpleServer extends Thread {
	
	private Socket client;
	private Map<String, Greetable> handlers;
	
	public SimpleServer(Socket client, Map<String, Greetable> handlers) {
		this.client = client;
		this.handlers = handlers;
	}
	
	@Override
	public void run() {
		connectionSocket();
	}
	
	private void connectionSocket() {		
		try {		
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		
		String[] str = br.readLine().split("\\s+");
		String command = str[0];
		String userName = str[1];
		
		String response = buildResponse(command, userName);
		bw.write(response);
		Thread.sleep(2000);
		bw.newLine();
		bw.flush();		
		
		br.close();
		bw.close();
		client.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}
	
	private String buildResponse(String command, String userName) {
		Greetable handler = handlers.get(command);
		if (handler != null) return handler.buildResponse(userName);
		else return "Hi, " + userName;
	}
	
}

	


