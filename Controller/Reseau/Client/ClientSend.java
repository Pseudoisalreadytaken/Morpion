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
	
	public ClientSend(Socket unSocket, ObjectOutputStream unOut, String unPseudo)
	{
		this.socket = unSocket;
		this.out = unOut;
		this.pseudo = unPseudo;
		/*try
		{
			this.out = new ObjectOutputStream(this.socket.getOutputStream());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}*/
	}
	
	//invite l’utilisateur à saisir un message, et l’envoie :
	public void run()
	{
		Scanner sc = new Scanner(System.in);
		while (true) 
		{
			System.out.print("Votre message >> ");
			//On attend un message
			while(attenteMess == false)
			{
				System.out.println("nop");
				try {
				TimeUnit.SECONDS.sleep(1);
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
			System.out.println("Message détecter");
			attenteMess = false;
			//Création du message
			String m = pseudo + " : " + this.unMessageAEnvouer;
			//Reset du champ message a envoyer
			this.unMessageAEnvouer = "";
			System.out.println(m);
			
			Message mess = new Message("client", m);
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
	
	public void ButtonSendPushed(boolean unBoolean) {
		this.attenteMess = unBoolean;
	}
	
	public void GetMessAEnvoyer(String unMessage) {
		this.unMessageAEnvouer = unMessage;
	}
	
	public boolean GetAttenteMess()
	{
		return this.attenteMess;
	}

}
