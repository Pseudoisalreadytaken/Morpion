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
			//crée le flux out à partir du socket :
			this.out = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Nouvelle connexion, id = " + id);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	//Permet de réceptionner les messages du client
	public synchronized void run()
	{
		try
		{
			//créer l'objet in à partir du socket :
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
				//On attend un nouveau message grâce à l’attribut in :
				Message mess = (Message) in.readObject();
				boolean envoyerMess = true;
				
				//Si il s'agit d'une demande de rejoindre la partie de morpion
				if (mess.GetSender().equals("demandeRejoindrePartie"))
				{		
					//Si il n'y a pas déjà 2 joueurs
					if (server.GetListClientJoueurMorpion().size() < 2)
					{
						for(ConnectedClient unClientJoueurMorpion :server.GetListClientJoueurMorpion())
						{
							//Si le joueur est déjà dans la partie
							if (unClientJoueurMorpion.GetId() == this.GetId())
							{
								envoyerMess = false;
							}
						}
					}
					else
					{
						envoyerMess = false;
					}					
					if (envoyerMess) 
					{
						server.addClientDansPartieMorpion(this);
					}				
				}
				
				
				if (mess.GetSender().equals("demandeDeJouerUnTourMorpion"))
				{
					boolean estBienUnJoueurMorpion = false;
					int compteurPositionDuJoueurMorpion = 0;
					int positionDuJoueurMorpion = -1;
					
					for(ConnectedClient unClientJoueurMorpion :server.GetListClientJoueurMorpion())
					{
						//Si le joueur est bien dans la partie
						if (unClientJoueurMorpion.GetId() == this.GetId())
						{
							estBienUnJoueurMorpion = true;
							if (compteurPositionDuJoueurMorpion == 0)
							{
								mess.SetCroixOuRond("croix");
								positionDuJoueurMorpion = 0;
							}
							else
							{
								mess.SetCroixOuRond("rond");
								positionDuJoueurMorpion = 1;
							}
						}
						compteurPositionDuJoueurMorpion++;
					}				
					if (estBienUnJoueurMorpion == true)
					{
						int tourDuJoueur = server.GetListEmplacementDejaPrisMorpion().size()%2;
						//Si c'est bien a ce joueur de jouer
						if(positionDuJoueurMorpion == tourDuJoueur)
						{
							for(Integer unEmplacementDejaPrisMorpion :server.GetListEmplacementDejaPrisMorpion())
							{
								if(unEmplacementDejaPrisMorpion == mess.GetEmplacementCliquer())
								{
									envoyerMess = false;
								}
							}
							if(envoyerMess == true)
							{
								server.GetListEmplacementDejaPrisMorpion().add(mess.GetEmplacementCliquer());
								//Si toute les cases ont été cocher mais qu'il n'y a toujours pas de gagnant
								if (server.GetListEmplacementDejaPrisMorpion().size() == 9 && !mess.GetSender().equals("messageVictoireDunJoueur"))
								{
									mess.SetSender("messageDerniereCaseCocher");
								}
							}
						}
						else
						{
							envoyerMess = false;
						}
					}
					else
					{
						envoyerMess = false;
					}
				}
				
				
				
				//Si il s'agit d'un message de victoire d'un joueur ou d'égalité
				if (mess.GetSender().equals("messageVictoireDunJoueur") || mess.GetSender().equals("messageDerniereCaseCocher"))
				{		
					
					server.GetListClientJoueurMorpion().clear();
					server.GetListEmplacementDejaPrisMorpion().clear();
				}
				
				
				if (envoyerMess)
				{
					//Une fois un message recu, on l'envoie a tous le monde
					server.broadcastMessage(mess);
				}
				
				//Si le message vien d'un client déconnecter, alors on indique au serveur que le client courant vient de
				//se déconnecter, et on met fin à la boucle while :
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
