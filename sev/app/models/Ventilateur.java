package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Ventilateur
extends
	Model
{
	private static final long serialVersionUID = 1L;
	@Id
	public String libelle;
	public static Finder<String, Ventilateur> find = new Finder<String, Ventilateur>(String.class, Ventilateur.class);
	
	public static List<Ventilateur> lister()
	{
		return find.all();
	}
	
	public static void creer(Ventilateur ventilateur)
	{
		ventilateur.save();
	}
	
	public static Ventilateur detail(String libelle)
	{
		return find.ref(libelle);
	}
	
	public static void supprimer(String libelle)
	{
		find.ref(libelle).delete();
	}
}