package edu.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		ServerSocket sc = new ServerSocket(12345);
		
		System.out.println("Server is started");
		
		while(true) {
			Socket client = sc.accept();
			
			SimpleServer ss = new SimpleServer(client);
			ss.start();
		}
		
	}
}

class SimpleServer extends Thread {
	
	private Socket client;
	
	public SimpleServer(Socket client) {
		this.client = client;
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
		switch (command) {
		case "Hello" : return "Hello" + " " + userName;
		case "Morning" : return "Good morning" + " " + userName;
		case "Evening" : return "Good evening" + " " + userName;
		default : return "Hi" + " " + userName;
		}
	}
	
}

	


