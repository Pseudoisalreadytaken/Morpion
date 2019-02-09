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
			
			//Lorsqu’une connexion est acceptée (on rappelle que la méthode accept() est bloquante)
			//on crée un nouvel objet ConnectedClient
			ConnectedClient newClient = new ConnectedClient(server, sockNewClient);
			
			//Ensuite, on ajoute au serveur ce nouveau client
			server.addClient(newClient);
			
			//enfin, on lance un thread à partir de l’objet ConnectedClient tout juste créé :
			Thread threadNewClient = new Thread(newClient);
			threadNewClient.start();
		}
	}
	
	public ServerSocket GetServerSocket()
	{
		return this.serverSocket;
	}
}
