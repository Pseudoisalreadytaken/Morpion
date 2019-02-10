package Morpion.Controller.Reseau.Server;

import java.util.ArrayList;
import java.util.List;

import Morpion.Controller.Reseau.Common.Message;

public class Server {
	
	private int port;
	private List<ConnectedClient> clients;
	private List<ConnectedClient> clientsJoueursMorpion;
	private List<Integer> emplacementDejaPrisMorpion = new ArrayList<Integer>();
	
	public Server(int unPort)
	{
		this.port = unPort;
		this.clients = new ArrayList<ConnectedClient>();
		this.clientsJoueursMorpion = new ArrayList<ConnectedClient>();
		//lancer un thread à partir de la classe Connection.
		Thread threadConnection = new Thread(new Connection(this));
		threadConnection.start();
	}
	
	
	public void addClient(ConnectedClient newClient)
	{	
		//ajoute le client connecté, passé en paramètre, à notre liste clients :
		this.clients.add(newClient);			
	}
	
	public void addClientDansPartieMorpion(ConnectedClient newClient)
	{	
		//ajoute le client connecté, passé en paramètre, à notre liste clients :
		this.clientsJoueursMorpion.add(newClient);			
	}
	
	//envoie le message mess à tous les clients
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
	
	//permet de retourner la liste des joueur actuel du morpion
	public List<ConnectedClient> GetListClientJoueurMorpion()
	{
		return this.clientsJoueursMorpion;
	}
	
	//permet de retourner la liste des emplacement déja pris sur le morpion
	public List<Integer> GetListEmplacementDejaPrisMorpion()
	{
		return this.emplacementDejaPrisMorpion;
	}
}
