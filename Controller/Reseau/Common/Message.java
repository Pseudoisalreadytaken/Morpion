package Morpion.Controller.Reseau.Common;

import java.io.Serializable;

public class Message implements Serializable{
	
	private String sender;
	private String content;
	private String pseudoDuSender;
	
	public Message (String unSender, String unContent, String unPseudoDuSender)
	{
		this.sender = unSender;
		this.content = unContent;
		this.pseudoDuSender = unPseudoDuSender;
	}
	
	public String GetContent()
	{
		return this.content;
	}
	
	public String GetSender()
	{
		return this.sender;
	}
	
	public void SetSender(String unSender)
	{
		this.sender = unSender;
	}
	
	public String GetPseudoDuSender()
	{
		return this.pseudoDuSender;
	}
}