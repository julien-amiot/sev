package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Section
extends Model 
{
	private static final long serialVersionUID = 1L;
	@Id
	public String libelle;
	@Required
	@ManyToOne
	public Ligne ligne;
	@ManyToMany
	public List<Station> stations;	
	public static Finder<String, Section> find = new Finder<String, Section>(String.class, Section.class);
	
	public static List<Section> lister()
	{
		return find.all();
	}
	
	public static void creer(Section section)
	{
		section.save();
	}

	public static Section detail(String libelle)
	{
		return find.ref(libelle);
	}
	
	public static void supprimer(String libelle)
	{
		find.ref(libelle).delete();
	}
	
	public Ligne getLigne()
	{
		return ligne;
	}
	
	public static Map<String, String> selectOptions()
	{
		Map<String, String> mapOptions = new LinkedHashMap<String, String>();
		
		for (Section reseau : lister())
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