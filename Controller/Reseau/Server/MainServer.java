package Morpion.Controller.Reseau.Server;

import java.io.IOException;

public class MainServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		
		try 
		{
			if (args.length != 1)
			{
				printUsage();
			} 
			else 
			{
				Integer port = new Integer(args[0]);
				Server server = new Server(port);
			}
			System.out.println("JA !");
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}					
	
	}
		
	private static void printUsage() 
	{
		System.out.println("java server.Server <port>");
		System.out.println("\t<port>: server's port");
	}

}
