package edu.network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

public class Client {

	public static void main(String[] args) {

		for(int i = 0; i < 5; i++) {
		SimpleClient sc = new SimpleClient(i);
				sc.start();
		}		
	}
}

class SimpleClient extends Thread {
	
	private final String[] COMMAND = {
			"Hello", "Morning", "Evening"
	};
	
	int command;
	
	public SimpleClient(int command) {
		this.command = command;
	}
	
	@Override
	public void run() {
		
		try {
//		System.out.println("Started" + LocalDateTime.now());

		Socket client = new Socket("127.0.0.1", 12345);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		
		String str = COMMAND[command % COMMAND.length] + " " + "Alexey";
		bw.write(str);
		bw.newLine();
		bw.flush();
		
		System.out.println(br.readLine());
		
//		System.out.println("Finished" + LocalDateTime.now());

		
		br.close();
		bw.close();
		client.close();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}
	}
}

