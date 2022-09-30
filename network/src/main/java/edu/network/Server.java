package edu.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	public static void main(String[] args) throws IOException {
		
		ServerSocket sc = new ServerSocket(12345);
		
		System.out.println("Server is started");
		
		while(true) {
			Socket client = sc.accept();
			connectionSocket(client);
		}
		
	}

	private static void connectionSocket(Socket client) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		
		StringBuilder sb = new StringBuilder("Hello, ");
		sb.append(br.readLine());
		
		bw.write(sb.toString());
		bw.newLine();
		bw.flush();		
		
		br.close();
		bw.close();
		client.close();
	}

}
