package Morpion.Controller.Reseau.Client;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import Morpion.Controller.Reseau.Common.Message;
import Morpion.FrontEnd.ClientPanel;
import javafx.scene.control.TextArea;

public class ClientSend implements Runnable {
	
	private Socket socket;
	private ObjectOutputStream out;
	private String pseudo;
	
	private boolean attenteMess = false;
	private String unMessageAEnvouer = "";
	private String senderDuMessAEnvoyer = "Client";
	private double x1;
	private double x2;
	private double y1;
	private double y2;
	private int emplacementCliquer;
	boolean reset = false;
	
	public ClientSend(Socket unSocket, ObjectOutputStream unOut, String unPseudo)
	{
		this.socket = unSocket;
		this.out = unOut;
		this.pseudo = unPseudo;
	}
	
	//invite l’utilisateur à saisir un message, et l’envoie :
	public void run()
	{
		Scanner sc = new Scanner(System.in);
		while (!Thread.interrupted()) 
		{
			//On attend un message
			while(attenteMess == false)
			{
				//on attend 0.05 sec
				try {
				TimeUnit.MILLISECONDS.sleep(50);
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			//Message détecter
			attenteMess = false;
			//Création du texte du message
			String m = pseudo + " " + this.unMessageAEnvouer;
			//Reset du champ message a envoyer
			this.unMessageAEnvouer = "";
			
			//Création de l'objet Message avec le message a envoyer
			Message mess = new Message(senderDuMessAEnvoyer, m, this.pseudo);
			
			//Remet le senderDuMessageEnvoyer par defaut
			this.senderDuMessAEnvoyer = "client";
			
			mess.SetX1(x1);
			mess.SetX2(x2);
			mess.SetY1(y1);
			mess.SetY2(y2);
			
			//reset des champs location morpion cliquer
			this.x1 = 0;
			this.x2 = 0;
			this.y1 = 0;
			this.y2 = 0;
			
			mess.SetEmplacementCliquer(emplacementCliquer);
			//reset des champs emplcaementCliquer
			this.emplacementCliquer = -1;
			
			mess.SetReset(reset);

			try
			{
				out.writeObject(mess);
				out.flush();
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}		
		}
	}
	
	public void EnvoyerLeMessage(boolean unBoolean) {
		this.attenteMess = unBoolean;
	}
	
	public void SetMessAEnvoyer(String unMessage) {
		this.unMessageAEnvouer = unMessage;
	}
	
	public void SetSenderDuMessAEnvoyer(String leSender) {
		this.senderDuMessAEnvoyer = leSender;
	}
	
	public boolean GetAttenteMess()
	{
		return this.attenteMess;
	}
	public void SetX1(double x1) {
		this.x1 = x1;
	}
	public void SetX2(double x2) {
		this.x2 = x2;
	}
	public void SetY1(double y1) {
		this.y1 = y1;
	}
	public void SetY2(double y2) {
		this.y2 = y2;
	}
	public void SetEmplacementCliquer(int unEmplacementCliquer)
	{
		this.emplacementCliquer = unEmplacementCliquer;
	}
	public void SetReset(boolean reset)
	{
		this.reset = reset;
	}

}
