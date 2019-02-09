package Morpion.Controller.Reseau.Server;

import java.util.ArrayList;
import java.util.List;

import Morpion.Controller.Reseau.Common.Message;

public class Server {
	
	private int port;
	private List<ConnectedClient> clients;
	
	public Server(int unPort)
	{
		this.port = unPort;
		this.clients = new ArrayList<ConnectedClient>();
		//lancer un thread � partir de la classe Connection.
		Thread threadConnection = new Thread(new Connection(this));
		threadConnection.start();
	}
	
	
	public void addClient(ConnectedClient newClient)
	{	
		//ajoute le client connect�, pass� en param�tre, � notre liste clients :
		this.clients.add(newClient);			
	}
	
	//envoie le message mess � tous les clients
	public void broadcastMessage(Message mess)
	{
		for (ConnectedClient client : clients) 
		{
			client.sendMessage(mess);
		}
	}
	
	public void disconnectedClient(ConnectedClient discClient)
	{
		discClient.closeClient();
		//supprime le client de la liste des client
		this.clients.remove(discClient);
	}
	
	public void disconnectedAllClient()
	{
		//Pour chaque clients
		for (ConnectedClient client : clients) 
		{
			client.closeClient();
			//supprime le client de la liste des client
		}
	}
	
	
	public int GetPort()
	{
		return this.port;
	}
}
