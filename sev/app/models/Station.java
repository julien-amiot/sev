package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

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
	@Size(min = 1)
	@ManyToMany
	public List<Section> sections;
	@ManyToMany
	@JoinTable(
		name = "station_to_stationPrecedente",
		joinColumns = {@JoinColumn(name = "station_id", referencedColumnName = "id", nullable = false)},
		inverseJoinColumns = {@JoinColumn(name = "stationPrecedente_id", referencedColumnName = "id", nullable = false)}
	)
	public List<Station> stationsPrecedentes;
	@ManyToMany(mappedBy = "stationsPrecedentes")
	public List<Station> stationsSuivantes;
	@Required
	public boolean isEnSurface;
	public static Finder<Integer, Station> find = new Finder<Integer, Station>(Integer.class, Station.class);
	
	public static List<Station> lister()
	{
		return find.all();
	}
	
	public static List<Station> listerSelonLigne(Ligne ligne)
	{
		return find.where().eq("sections.ligne.libelle", ligne.libelle).findList();
	}
	
	public static List<Station> listerSelonSection(Section section)
	{
		return find.where().contains("sections", section.libelle).findList();
	}
	
	public static void creer(Station station)
	{
		station.save();
		station.saveManyToManyAssociations("stationsPrecedentes");
		station.saveManyToManyAssociations("sections");
	}
	
	public static Station recupererSelonId(Integer id)
	{
		return find.ref(id);
	}	
	
	public static void supprimer(Integer id)
	{
		Station station = recupererSelonId(id);
		
		for (Section section : station.getSections())
		{
			section.getStations().remove(station);
		}
		
		station.getSections().clear();
		station.saveManyToManyAssociations("sections");
		
		for (Station stationPrecedente : station.getStationsPrecedentes())
		{
			stationPrecedente.getStationsSuivantes().remove(station);
		}
		
		station.getStationsPrecedentes().clear();
		
		for (Station stationSuivante : station.getStationsSuivantes())
		{
			stationSuivante.getStationsPrecedentes().remove(station);
		}
		
		station.getStationsSuivantes().clear();
		
		station.saveManyToManyAssociations("stationsPrecedentes");
		station.saveManyToManyAssociations("stationsSuivantes");
		station.delete();
	}
	
	public List<Section> getListeSections()
	{
		return sections;
	}
	
	public Ligne getLigne()
	{
		if (getListeSections().size() > 0)
		{
			return getListeSections().get(0).getLigne();
		}
		else
		{
			return null;
		}
	}
	
	public Reseau getReseau()
	{
		if (getListeSections().size() > 0)
		{
			return getListeSections().get(0).getLigne().getReseau();
		}
		else
		{
			return null;
		}
	}
	
	public static Map<String, String> selectOptions(Object critere)
	{
		Map<String, String> mapOptions = new LinkedHashMap<String, String>();
		List<Station> listeStations;
		
		if (critere instanceof Ligne)
		{
			listeStations = listerSelonLigne((Ligne)critere);
		}
		else if (critere instanceof Section)
		{
			listeStations = listerSelonSection((Section)critere);
		}
		else
		{
			listeStations = lister();
		}
		
		for (Station station : listeStations)
		{
			mapOptions.put("" + station.id, station.libelle);
		}
		
		return mapOptions;
	}

	public List<Section> getSections()
	{
		return sections;
	}

	public List<Station> getStationsPrecedentes() 
	{
		return stationsPrecedentes;
	}

	public List<Station> getStationsSuivantes() 
	{
		return stationsSuivantes;
	}
	
	public String toString()
	{
		return libelle;
	}
}