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
			String address = uneAdresse;
			Integer port = new Integer(unPort);
			String pseudo = unPseudo;
			this.leClient = new Client(address, port, pseudo);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}	
		//Création client réussi
		System.out.println("Client" + pseudo + " crée\tadresse : " + uneAdresse + "\tport : " + unPort);
	}
	
	public Client GetClient() {
		return this.leClient;
	}
	
	public String GetPseudo(){
		return this.pseudo;
	}

}
