package Morpion.Controller.Reseau.Client;

import java.io.IOException;
import java.net.UnknownHostException;

public class MainClient {

	private Client leClient;
	private String pseudo;
		
	public MainClient(String uneAdresse, String unPort, String unPseudo)
	{
		this.pseudo = unPseudo;
		try
		{
			if (uneAdresse == "") 
			{
				printUsage();
			} 
			else 
			{
				String address = uneAdresse;
				Integer port = new Integer(unPort);
				String pseudo = unPseudo;
				this.leClient = new Client(address, port, pseudo);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}	
		System.out.println("JA !");
	}
	
	private void printUsage() {
		System.out.println("java client.Client <address> <port>");
		System.out.println("\t<address>: server's ip address");
		System.out.println("\t<port>: server's port");
	}
	
	public Client GetClient() {
		return this.leClient;
	}
	
	public String GetPseudo(){
		return this.pseudo;
	}

}
