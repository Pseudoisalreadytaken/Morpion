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
			System.out.println("cbn");
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
				System.out.println("ok0");
				//On attend un nouveau message gr�ce � l�attribut in :
				Message mess = (Message) in.readObject();
				System.out.println("ok1");
				//Si le message n'est pas null
				if (mess != null)
				{
					mess.SetSender(String.valueOf(id));
					System.out.println("ok2");
					server.broadcastMessage(mess, id);
					System.out.println("ok3");
				}
				//Si le message est null, alors on indique au serveur que le client courant vient de
				//se d�connecter, et on met fin � la boucle while :
				else
				{
					System.out.println("ok4");
					server.disconnectedClient(this);
					System.out.println("ok5");
					isActive = false;
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			
			try
			{
				TimeUnit.SECONDS.sleep(1);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			
		}
	}
	
	//envoie le message au client en utilisant out :
	public void sendMessage(Message m)
	{
		try
		{
			System.out.println("hi");
			this.out.writeObject(m);
			this.out.flush();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//envoie le message au client en utilisant out :
		public void sendMessage2(String m)
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
