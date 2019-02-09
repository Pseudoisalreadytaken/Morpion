package Morpion.Controller.Reseau.Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Connection implements Runnable {
	
	private Server server;
	private ServerSocket serverSocket;
	
	public Connection (Server unServer)
	{
		this.server = unServer;
		try
		{
			this.serverSocket = new ServerSocket(server.GetPort());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Override
	public synchronized void run()
	{
		Socket sockNewClient = null;
		while(true)
		{
			try
			{
				sockNewClient = serverSocket.accept();
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			//Lorsqu�une connexion est accept�e (on rappelle que la m�thode accept() est bloquante)
			//on cr�e un nouvel objet ConnectedClient
			ConnectedClient newClient = new ConnectedClient(server, sockNewClient);
			
			//Ensuite, on ajoute au serveur ce nouveau client
			server.addClient(newClient);
			
			//enfin, on lance un thread � partir de l�objet ConnectedClient tout juste cr�� :
			Thread threadNewClient = new Thread(newClient);
			threadNewClient.start();
		}
	}
	
	public ServerSocket GetServerSocket()
	{
		return this.serverSocket;
	}
}
