package Morpion.Controller.Jeu;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Analyse {
	private String type;
	private boolean[] valide = new boolean[9]; 
	
	public Analyse(String type) {
		this.type = type;
		for (int i = 0; i < valide.length; i++) {
			valide[i] = false;
		}
	}
	
	public void Ajouter(int i) {
		valide[i] = true;
	}
	
	public void Verifier() {
		boolean v = false;
		if (valide[0] && valide[4] && valide[8] ) 
		{
			 v = true;	
		}
		if (valide[2] && valide[4] && valide[6] ) 
		{
			 v = true;	
		}
			
		for (int i = 0; i < valide.length-2; i++) {
			if (valide[i] && valide[i+1] && valide[i+2] && (i == 0 || i == 3 || i == 6)) 
			{
				 v = true;
			}			
		}
		for (int i = 0; i < valide.length-6; i++) {
			if (valide[i] && valide[i+3] && valide[i+6] ) 
			{
				 v = true;
			}			
		}
		if (v) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setContentText(this.type +" a gagngé !");
	        alert.show();
		}
	}
}
