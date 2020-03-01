package Milestone1;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

public class Client_Thread extends Thread{
	Socket connectionSocket;
	int ID;
	String clientSentence;
	String capitalizedSentence;
	DataOutputStream outToClient;
	BufferedReader inFromClient;
	private ArrayList<Float> degrees;
	float avgdeg;

	public int getID() {
		return ID;
	}

	public float getAvgdeg() {
		return avgdeg;
	}

		//lazm n3ml constructor 3shan ya5od socket bta3 client da 
		public Client_Thread(Socket ClientSocket,int ID)
		{
			avgdeg=0;
			degrees = new ArrayList();
			connectionSocket = ClientSocket;
			ID = this.ID;
		}
	
	public void run(){
		try {
			inFromClient = new BufferedReader(new
					InputStreamReader(connectionSocket.getInputStream()));
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} 

	
	try {
		outToClient = new DataOutputStream(connectionSocket.getOutputStream());
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	} 

	while(true){
		try {
			clientSentence = inFromClient.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		try {
		
		}catch(NullPointerException e) {
			clientSentence="";
		}
		
		try {
			float d=Float.parseFloat(clientSentence.substring(4));
			degrees.add(d);
			float sum=0;
			for (int i = 0; i < degrees.size(); i++) {
				sum+=((degrees.get(i)));
			}
			avgdeg=sum/degrees.size();
			String clientID=clientSentence.substring(0, 3);
			System.out.println("From CLient "+clientID+" temperature is "+avgdeg);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(clientSentence.equalsIgnoreCase("Close Socket"))
		{
			try {
				connectionSocket.close();
				System.out.println("Connection Socket is closed @Server");
				break;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		}
		
	}
}
