package models;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Station 
extends Model 
{
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	public ClefStation clefStation;
	@ManyToOne
	public List<Station> stationsPrecedentes;
	@Required
	public boolean enSurface;
	public static Finder<ClefStation, Station> find = new Finder<ClefStation, Station>(ClefStation.class, Station.class);
	
	public static List<Station> lister()
	{
		return find.all();
	}
	
	public static void creer(Station station)
	{
		station.save();
	}
	
	public static Station detail(ClefStation clefStation)
	{
		return find.ref(clefStation);
	}	
	
	public static void supprimer(ClefStation clefStation)
	{
		find.ref(clefStation).delete();
	}	
	
	@Embeddable
	public class ClefStation
	{
		@Required
		public String libelle;
		@Required
		public Section section;
		
		@Override
		public int hashCode() 
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
			result = prime * result + ((section == null) ? 0 : section.hashCode());
			return result;
		}
		
		@Override
		public boolean equals(Object obj) 
		{
			if (this == obj)
			{
				return true;
			}
			
			if (obj == null)
			{
				return false;
			}
				
			if (getClass() != obj.getClass())
			{
				return false;
			}
				
			ClefStation other = (ClefStation) obj;
			
			if (!getOuterType().equals(other.getOuterType()))
			{
				return false;
			}
				
			if (libelle == null) 
			{
				if (other.libelle != null)
				{
					return false;
				}
			} 
			else if (!libelle.equals(other.libelle))
			{
				return false;
			}
			
			if (section == null) 
			{
				if (other.section != null)
				{
					return false;
				}
			} 
			else if (!section.equals(other.section))
			{
				return false;
			}
			
			return true;
		}
		
		private Station getOuterType() 
		{
			return Station.this;
		}
	}
}