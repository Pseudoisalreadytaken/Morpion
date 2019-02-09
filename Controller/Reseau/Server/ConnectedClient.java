package Morpion.Controller.Reseau.Server;

//ctrl+Maj+O
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

//import de las classe message du package common
import Morpion.Controller.Reseau.Common.Message;

public class ConnectedClient implements Runnable {

	private static int idCounter;
	private int id;
	private Server server;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public ConnectedClient(Server unServer, Socket unSocket)
	{
		try
		{
			this.server = unServer;
			this.socket = unSocket;
			this.id = idCounter;
			idCounter++;
			//cr�e le flux out � partir du socket :
			this.out = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Nouvelle connexion, id = " + id);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	//Permet de r�ceptionner les messages du client
	public synchronized void run()
	{
		try
		{
			//cr�er l'objet in � partir du socket :
			this.in = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//Tant que la connexion est active
		boolean isActive = true;
		while (isActive) 
		{
			try
			{
				//On attend un nouveau message gr�ce � l�attribut in :
				Message mess = (Message) in.readObject();
				//Une fois un message recu, on l'envoie a tous le monde
				server.broadcastMessage(mess);
				
				//Si le message vien d'un client d�connecter, alors on indique au serveur que le client courant vient de
				//se d�connecter, et on met fin � la boucle while :
				if (mess.GetSender().equals("ClientDeconnecter"))
				{
					isActive = false;
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			//On attend 1 seconde
			try
			{
				TimeUnit.SECONDS.sleep(1);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
		server.disconnectedClient(this);
	}
	
	//envoie le message aux clients en utilisant out :
	public void sendMessage(Message m)
	{
		try
		{
			this.out.writeObject(m);
			this.out.flush();
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	//ferme les deux flux in et out, ainsi que le socket :
	public void closeClient()
	{
		try
		{
			this.in.close();
			this.out.close();
			this.socket.close();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public int GetId()
	{
		return this.id;
	}
}
