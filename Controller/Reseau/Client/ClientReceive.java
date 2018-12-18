package Morpion.Controller.Reseau.Client;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import Morpion.Controller.Reseau.Common.Message;

public class ClientReceive implements Runnable {


	public Client client;
	public Socket socket;
	private ObjectInputStream in;

	
	public ClientReceive(Client unClient, Socket unSocket)
	{
		this.client = unClient;
		this.socket = unSocket;
	}
	
	
	
	public synchronized void run()
	{
		//crée l'object in à partir du socket
		try
		{
			//créer l'objet in à partir du socket :
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//Attend un nouveau message à l'aide de in tant que la connexion est active. Si le
		//message reçu n’est pas null, on l’affiche sur la sortie standard. Si le message reçu est null, la
		//connexion devient inactive, et on appelle la méthode disconnectedServer() de l’attribut client
		//
		Message mess;
		boolean isActive = true ;
		while(isActive)
		{
			try
			{
				mess = (Message) in.readObject();
				if (mess != null) 
				{
					System.out.println("\nMessage reçu : " + mess.GetContent());
					//this.client.messageReceived(mess);
				} 
				else
				{
					isActive = false;
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		} 
		client.disconnectedServer();
	}
	
}