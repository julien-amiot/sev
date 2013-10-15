package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Station 
extends Model 
{
	private static final long serialVersionUID = 1L;
	@Id
	public Integer id;
	@Required
	public String libelle;
	@ManyToMany
	public List<Section> sections;
	/*@ManyToMany
	public List<Station> stationsPrecedentes;*/
	/*@Required
	public boolean enSurface;*/
	public static Finder<Integer, Station> find = new Finder<Integer, Station>(Integer.class, Station.class);
	
	public static List<Station> lister()
	{
		return find.all();
	}
	
	public static void creer(Station station)
	{
		station.save();
		station.saveManyToManyAssociations("sections");
	}
	
	public static Station detail(Integer id)
	{
		return find.ref(id);
	}	
	
	public static void supprimer(Integer id)
	{
		find.ref(id).delete();
	}
	
	public List<Section> getListeSections()
	{
		return sections;
	}
}