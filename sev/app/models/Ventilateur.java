package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Ventilateur
extends
	Model
{
	private static final long serialVersionUID = 1L;
	@Id
	public Integer id;
	@Required
	public String libelle;
	public static Finder<Integer, Ventilateur> find = new Finder<Integer, Ventilateur>(Integer.class, Ventilateur.class);
	
	public static List<Ventilateur> lister()
	{
		return find.all();
	}
	
	public static void creer(Ventilateur ventilateur)
	{
		ventilateur.save();
	}
	
	public static void supprimer(Integer id)
	{
		find.ref(id).delete();
	}
}