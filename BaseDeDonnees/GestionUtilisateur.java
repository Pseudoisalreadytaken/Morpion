package Morpion.BaseDeDonnees;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class GestionUtilisateur {
	
	
	public static Connection GetConnection(String unUrl, String unUtilisateur, String unMotDePasse) throws SQLException
    {
        return DriverManager.getConnection(unUrl, unUtilisateur, unMotDePasse);
    }
	
	
	//Connexion à la base de donnée, retourne cette connexion
	public static Connection ConnexionBDD() throws SQLException
	{
		//Regarde si le driver est bon
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		//Récupération des données pour la BDD
		Properties prop = new Properties();
		try
		{
			FileInputStream input = new FileInputStream("src/Morpion/BaseDeDonnees/superGlobaleBDD.properties");
			prop.load(input);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		String url = prop.getProperty("url");
		String utilisateur = prop.getProperty("utilisateur");
		String motDePasse = prop.getProperty("motDePasse");
		
		//Tentative de connexion à la base de données
		Connection connexionALaBDD = GetConnection(url, utilisateur, motDePasse);
		return connexionALaBDD;		
	}
	
	
	//Retourne la liste des utilisateurs présent dans la base de données
	public static List<String> GetLesUtilisateursBDD()
	{
		List<String> listeDesUtilisateursBDD = new ArrayList<String>();
		try
		{
			Connection laConnexionBDD = ConnexionBDD();
			Statement state = laConnexionBDD.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM UTILISATEURMORPION");
			while (result.next()) {
				listeDesUtilisateursBDD.add(result.getString("pseudo"));
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return listeDesUtilisateursBDD;
	}
	
	
	//Ajoute un utilisateur dans la base de données
	public static void AjouterUnUtilisateur(String unPseudo, String unMotDePasse)
	{
		try
		{
			Connection laConnexionBDD = ConnexionBDD();
			
			Statement state = laConnexionBDD.createStatement();
			int result = state.executeUpdate("INSERT INTO UTILISATEURMORPION (pseudo, motDePasse) VALUES ('" + unPseudo + "', '" + unMotDePasse + "')");	
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	
	//Vérification si le mot de passe d'un utilisateur correspond
	public static boolean verifierMotDePasseBDD(String unPseudo, String unMotDePasse)
	{
		boolean laVerifMdp = false;
		try
		{
			Connection laConnexionBDD = ConnexionBDD();
			
			Statement state = laConnexionBDD.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM UTILISATEURMORPION WHERE pseudo = '" + unPseudo + "'");
			
			while (result.next()) {
				if (result.getString("motDePasse").equals(unMotDePasse))
				{
					laVerifMdp = true;
				}
			}

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return laVerifMdp;
	}
	
	
}
