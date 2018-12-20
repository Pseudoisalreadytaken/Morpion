package Morpion.Controller.Reseau.Server;

import java.io.IOException;

public class MainServer {



	public MainServer(String unPort) {		
		try 
		{
			if (unPort == "")
			{
				printUsage();
			} 
			else 
			{
				Integer port = new Integer(unPort);
				Server server = new Server(port);
			}
			System.out.println("JA !");
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}			
	}
	
		
	private void printUsage() 
	{
		System.out.println("java server.Server <port>");
		System.out.println("\t<port>: server's port");
	}

}
