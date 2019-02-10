package Morpion.Controller.Reseau.Client;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


import Morpion.Controller.Reseau.Common.Message;
import Morpion.FrontEnd.ClientPanel;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ClientReceive implements Runnable {


	public Client client;
	public Socket socket;
	private String pseudo;
	private ObjectInputStream in;

	
	public ClientReceive(Client unClient, Socket unSocket, String unPseudo)
	{
		this.client = unClient;
		this.socket = unSocket;
		this.pseudo = unPseudo;
	}
	
	
	
	public synchronized void run()
	{
		//crée l'object in à partir du socket
		try
		{
			//créer l'objet in à partir du socket :
			in = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//Attend un nouveau message à l'aide de in tant que la connexion est active. Si le
		//message reçu ne vien pas d'un client déconnecter, on l’affiche sur la sortie standard. Si le message reçu vien d'un client déconnecter, la
		//connexion devient inactive, et on appelle la méthode disconnectedServer() de l’attribut client
		//
		Message mess;
		while(!Thread.interrupted())
		{
			try
			{
				//On attend un message
				mess = (Message) in.readObject();
				//On ajoute le messsage sur le FrontEnd
				Morpion.FrontEnd.ClientPanel.AjouterMess(mess.GetContent(), mess.GetSender());
				System.out.println(pseudo);		
				if(mess.GetCroixOuRond().equals("croix")) 
				{
					Morpion.FrontEnd.ClientPanel.AjouterCroix(mess.GetX1(), mess.GetX2(), mess.GetY1(), mess.GetY2());
				}	
				else if(mess.GetCroixOuRond().equals("rond"))
				{
					double xDuRond = (mess.GetX1() + mess.GetX2())/2;
					double yDuRond = (mess.GetY1() + mess.GetY2())/2;
					Morpion.FrontEnd.ClientPanel.AjouterRond(xDuRond,yDuRond);
				}
				if(mess.GetWin()) 
				{
					Platform.runLater(new Runnable() {
					    @Override
					    public void run() {
							Alert alert = new Alert(AlertType.CONFIRMATION);
					        alert.setContentText( " gagngé !");
					        alert.show();
					    }
					});		
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		} 
	}
	
}