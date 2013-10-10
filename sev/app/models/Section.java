package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Section
extends Model 
{
	private static final long serialVersionUID = 1L;
	@Id
	public Integer id;
	@Required
	public String libelle;
	@Required
	@ManyToOne
	public Ligne ligne;
	public static Finder<Integer, Section> find = new Finder<Integer, Section>(Integer.class, Section.class);
	
	public static List<Section> lister()
	{
		return find.all();
	}
	
	public static void creer(Section ligne)
	{
		ligne.save();
	}
	
	public static void supprimer(Integer id)
	{
		find.ref(id).delete();
	}
}