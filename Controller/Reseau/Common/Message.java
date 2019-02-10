package Morpion.Controller.Reseau.Common;

import java.io.Serializable;

public class Message implements Serializable{
	
	private String sender;
	private String content;
	private String pseudoDuSender;
	private double X1;
	private double X2;
	private double Y1;
	private double Y2;
	private String croixOuRond;
	private boolean win = false;

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
	public double GetX1() 
	{
		return this.X1;
	}
	public double GetX2()
	{
		return this.X2;
	}
	public double GetY1() 
	{
		return this.Y1;
	}
	public double GetY2() 
	{
		return this.Y2;
	}
	public void SetX1(double x1) {
		this.X1 = x1;
	}
	public void SetX2(double x2) {
		this.X2 = x2;
	}
	public void SetY1(double y1) {
		this.Y1 = y1;
	}
	public void SetY2(double y2) {
		this.Y2 = y2;
	}
	
	public String GetCroixOuRond() {
		return this.croixOuRond;
	}
	
	public void SetCroixOuRond(String uneCroixOuUnRond) {
		this.croixOuRond = uneCroixOuUnRond;
	}
	
	public boolean GetWin()
	{
		return this.win;
	}
	public void SetWin(boolean win) {
		this.win = win;
	}

}