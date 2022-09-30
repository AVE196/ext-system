package edu.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("Ready to connect");

		Socket client = new Socket("127.0.0.1", 12345);
		System.out.println("Connect to server established");
		createConnect(client);
		
	}

	private static void createConnect(Socket client) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		
		String str = "Alexey";
		bw.write(str);
		bw.newLine();
		bw.flush();
		
		System.out.println(br.readLine());
		
		br.close();
		bw.close();
		client.close();
	}

}
