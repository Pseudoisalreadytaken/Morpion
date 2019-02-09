package Morpion.Controller.Reseau.Server;

import java.io.IOException;

public class MainServer {



	public MainServer(String unPort) {		
		try 
		{
			Integer port = new Integer(unPort);
			Server server = new Server(port);
			System.out.println("Nouveau serveur crée");
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}			
	}
}
