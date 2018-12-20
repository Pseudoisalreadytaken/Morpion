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
		//lancer un thread à partir de la classe Connection.
		Thread threadConnection = new Thread(new Connection(this));
		threadConnection.start();
	}
	
	
	public void addClient(ConnectedClient newClient)
	{
		//envoie d’abord un message à tous les clients pour annoncer la nouvelle connexion :
		for (ConnectedClient client : clients) 
		{
			client.sendMessage2("Le client "+newClient.GetId()+" vient de se connecter");
		}
		
		//ajoute le client connecté, passé en paramètre, à notre liste clients :
		this.clients.add(newClient);			
	}
	
	//envoie le message mess à tous les clients sauf celui dont l’identifiant est id
	public void broadcastMessage(Message mess, int id)
	{
		for (ConnectedClient client : clients) 
		{
			client.sendMessage(mess);
		}
	}
	
	
	//appeler la méthode closeClient() du client qui se déconnecte, le supprimer de la liste clients, 
	//et enfin prévenir les clients restants que discClient vient de se déconnecter, en leur envoyant un message :
	public void disconnectedClient(ConnectedClient discClient)
	{
		for (ConnectedClient client : clients) 
		{
			client.sendMessage(new Message("server", "Le client " +
			discClient.GetId() + " nous a quitté"));
		}
	}
	
	public int GetPort()
	{
		return this.port;
	}
}
