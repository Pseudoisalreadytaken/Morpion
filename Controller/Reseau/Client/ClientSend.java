package Morpion.Controller.Reseau.Client;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Morpion.Controller.Reseau.Common.Message;
import Morpion.FrontEnd.ClientPanel;

public class ClientSend implements Runnable {
	
	private Socket socket;
	private ObjectOutputStream out;
	
	public ClientSend(Socket unSocket, ObjectOutputStream unOut)
	{
		this.socket = unSocket;
		this.out = unOut;
		/*try
		{
			this.out = new ObjectOutputStream(this.socket.getOutputStream());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}*/
	}
	
	//invite l’utilisateur à saisir un message, et l’envoie :
	public synchronized void run()
	{
		Scanner sc = new Scanner(System.in);
		while (true) 
		{
			//System.out.print("Votre message >> " + receivedText.getChildren().add(messageAEnvoye));
			String m = sc.nextLine();
			/*Message mess = new Message("client", m);
			try
			{
				out.writeObject(mess);
				out.flush();
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}*/
			
		}
	}

}
