package Milestone1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	// TODO Auto-generated constructor stub
	public static void main(String[] args) throws UnknownHostException, IOException {
		String sentence; 
		String modifiedSentence; 

		BufferedReader inFromUser = 
				new BufferedReader(new InputStreamReader(System.in)); 
		Socket clientSocket = new Socket("localhost", 17345);

		DataOutputStream outToServer = 
				new DataOutputStream(clientSocket.getOutputStream()); 
		BufferedReader inFromServer = 
				new BufferedReader(new
						InputStreamReader(clientSocket.getInputStream())); 

		while(true){
			System.out.println("Please, Enter your message.");
			sentence = inFromUser.readLine(); 

			outToServer.writeBytes(sentence + '\n'); 

			modifiedSentence = inFromServer.readLine(); 

			System.out.println("FROM SERVER: " + modifiedSentence); 
			System.out.println("================================");
			
			if(sentence.equalsIgnoreCase("Close Socket"))
			{
				clientSocket.close(); 
				System.out.println("ClientSocket is closed @Client");
				break;
			}
		}
	}
}
