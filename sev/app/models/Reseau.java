package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import play.db.ebean.Model;

@Entity
public class Reseau
extends Model 
{
	private static final long serialVersionUID = 1L;
	@Id
	public String libelle;
	@OneToMany(cascade = CascadeType.ALL)
	public List<Ligne> lignes;
	public static Finder<String, Reseau> find = new Finder<String, Reseau>(String.class, Reseau.class);
	
	public static List<Reseau> lister()
	{
		return find.all();
	}
	
	public static void creer(Reseau reseau)
	{
		reseau.save();
	}

	public static Reseau detail(String libelle)
	{
		return find.ref(libelle);
	}
	
	public static void supprimer(String libelle)
	{
		find.ref(libelle).delete();
	}
	
	public static Map<String, String> selectOptions()
	{
		Map<String, String> mapOptions = new LinkedHashMap<String, String>();
		
		for (Reseau reseau : lister())
		{
			mapOptions.put("" + reseau.libelle, reseau.libelle);
		}
		
		return mapOptions;
	}
	
	public String toString()
	{
		return libelle;
	}
}