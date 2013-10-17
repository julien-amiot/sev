package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.avaje.ebean.Expr;

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
	@ManyToMany(cascade = CascadeType.ALL)
	public List<Station> stations;	
	public static Finder<String, Section> find = new Finder<String, Section>(String.class, Section.class);
	
	public static List<Section> lister()
	{
		return find.all();
	}
	
	public static List<Section> listerSelonLigne(Ligne ligne)
	{
		return find.where(Expr.eq("ligne", ligne)).findList();
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
	
	public static Map<String, String> selectOptions(Ligne ligne)
	{
		Map<String, String> mapOptions = new LinkedHashMap<String, String>();
		List<Section> listeSections;
		
		if (ligne == null)
		{
			listeSections = lister();
		}
		else
		{
			listeSections = listerSelonLigne(ligne);
		}
		
		for (Section section : listeSections)
		{
			mapOptions.put("" + section.libelle, section.libelle);
		}
		
		return mapOptions;
	}
	
	public List<Station> getStations()
	{
		return stations;
	}
	
	public String toString()
	{
		return libelle;
	}
}