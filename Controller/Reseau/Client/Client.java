package Morpion.Controller.Reseau.Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Morpion.Controller.Reseau.Common.Message;
import Morpion.Controller.Reseau.Server.Connection;

//import server.ServerSocket;

public class Client {
	private String address;
	private int port;
	private String pseudo;
	
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	private Thread threadClientSend;
	private Thread threadClientReceive;
	private ClientSend leClientSend;
	private ClientReceive leClientReceive;


	public Client(String uneAdresse, int unPort, String unPseudo)
	{
		this.address = uneAdresse;
		this.port = unPort;
		this.pseudo = unPseudo;
		
		try
		{			
			this.socket = new Socket(this.address, this.port);
			this.out = new ObjectOutputStream(this.socket.getOutputStream());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//Création de l'objet Message avec le message qui indique que ce client vien de ce connecter
		Message mess = new Message("nouveauClient",this.pseudo + " vient de se connecter", this.pseudo);
		try
		{
			out.writeObject(mess);
			out.flush();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//Création du ClientSend et du ClientReceive
		this.leClientSend = new ClientSend(this.socket, this.out, this.pseudo);
		this.leClientReceive = new ClientReceive(this, this.socket, this.pseudo);
		
		//Lancement des Threads
		this.threadClientSend = new Thread(this.leClientSend);
		this.threadClientReceive = new Thread(this.leClientReceive);
		threadClientSend.start();
		threadClientReceive.start();
	}
	
	
	public void disconnectedServer()
	{
		try
		{
			threadClientSend.sleep(100);
			threadClientSend.interrupt();
			threadClientReceive.sleep(100);
			threadClientReceive.interrupt();
			
			this.in.close();
			this.out.close();
			this.socket.close();
			System.exit(0);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public Thread GeTthreadClientSend()
	{
		return this.threadClientSend;
	}
	
	public ClientSend GetLeClientSend()
	{
		return this.leClientSend;
	}
	
	public String GetPseudo()
	{
		return this.pseudo;
	}
	
	public Socket GetSocket()
	{
		return this.socket;
	}
	
}