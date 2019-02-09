package Morpion.Controller.Jeu;
import javafx.geometry.Point2D;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Terrain {
	
	private Rectangle[] rectangle = new Rectangle[9] ;
	private Point2D[] pointMilieu = new Point2D[9];
	
	public Terrain(double hauteurFenetre, double largeurFenetre) {
		int compteur = 0;
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
				Rectangle r = new Rectangle(i * largeurFenetre/3 , j * hauteurFenetre/3, largeurFenetre/3, hauteurFenetre/3);
				//r.setFill(null);
				r.setStroke(Color.WHITE);
				this.rectangle[compteur] = r;
				
				
				Point2D p1 = new Point2D(i * largeurFenetre/3 , j * hauteurFenetre/3);
				Point2D p2 = new Point2D(i * largeurFenetre/3 +  largeurFenetre/3 , j * hauteurFenetre/3 +  hauteurFenetre/3);			
				this.pointMilieu[compteur] = p1.midpoint(p2);
				compteur ++;
			}
        }
	}
	public Rectangle[] GetRectangle() {
		return this.rectangle;
	}
	public Point2D GetMilieu(int i) {
		return this.pointMilieu[i];
	}	
}
