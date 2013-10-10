package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

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
	@Required
	@ManyToMany
	public Section section;
	@ManyToOne
	public Station stationPrecedente;
	@Required
	public boolean enSurface;
}