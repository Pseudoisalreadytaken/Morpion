package Morpion.Controller.Reseau.Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Morpion.Controller.Reseau.Server.Connection;

//import server.ServerSocket;

public class Client {
	//private int id;
	private String address;
	private int port;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;



	public Client(String uneAdresse, int unPort)
	{
		//this.id = unId;
		this.address = uneAdresse;
		this.port = unPort;
		try
		{
			
			this.socket = new Socket(this.address, this.port);
			this.out = new ObjectOutputStream(this.socket.getOutputStream());
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		Thread threadClientSend = new Thread(new ClientSend(this.socket, this.out));
		Thread threadClientReceive = new Thread(new ClientReceive(this, this.socket));
		threadClientSend.start();
		threadClientReceive.start();
	}
	
	
	public void disconnectedServer()
	{
		try
		{
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
	
	
	/*public int GetId()
	{
		return this.id;
	}
	
	public void SetId(int unId)
	{
		this.id = unId;
	} */
}