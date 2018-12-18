package Morpion.Controller.Reseau.Common;

import java.io.Serializable;

public class Message implements Serializable{
	
	private String sender;
	private String content;
	
	public Message (String unSender, String unContent)
	{
		this.sender = unSender;
		this.content = unContent;
	}
	
	public String toString()
	{
		return "Ce message a pour sener : " + this.sender + " et pour content : " + this.content;
	}
	
	public String GetContent()
	{
		return this.content;
	}
	
	public void SetSender(String unSender)
	{
		this.sender = unSender;
	}
}