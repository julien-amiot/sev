package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class EtatVentilateur 
extends	Model 
{
	private static final long serialVersionUID = 1L;
	public static Finder<String, EtatVentilateur> find = new Finder<String, EtatVentilateur>(String.class, EtatVentilateur.class);
	@Id
	public String libelle;
	@Required
	public String couleurHexadecimale;

	public static List<EtatVentilateur> lister()
	{
		return find.all();
	}
	
	public static void creer(EtatVentilateur etatVentilateur)
	{
		etatVentilateur.save();
	}
	
	public static EtatVentilateur detail(String libelle)
	{
		return find.ref(libelle);
	}
	
	public static void supprimer(String libelle)
	{
		find.ref(libelle).delete();
	}
}