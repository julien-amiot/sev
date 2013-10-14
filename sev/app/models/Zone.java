package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Zone 
extends	Model 
{
	private static final long serialVersionUID = 1L;
	public static Finder<String, Zone> find = new Finder<String, Zone>(String.class, Zone.class);
	@Id
	public String libelle;

	public static List<Zone> lister()
	{
		return find.all();
	}
	
	public static void creer(Zone ventilateur)
	{
		ventilateur.save();
	}
	
	public static Zone detail(String libelle)
	{
		return find.ref(libelle);
	}
	
	public static void supprimer(String libelle)
	{
		find.ref(libelle).delete();
	}
}