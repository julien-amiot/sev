package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Expression;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Ligne
extends Model 
{
	private static final long serialVersionUID = 1L;
	@Id
	public String libelle;
	@Required
	public String guideOperateurLigneURI;
	@Required
	public String guideOperateurLigneDegradeURI;
	@OneToMany(cascade = CascadeType.ALL)
	public List<Section> sections;
	@ManyToOne
	public Reseau reseau;
	public static Finder<String, Ligne> find = new Finder<String, Ligne>(String.class, Ligne.class);
	
	public static List<Ligne> lister()
	{
		return find.all();
	}
	
	public static List<Ligne> listerSelonReseau(Reseau reseau)
	{
		return find.where(Expr.eq("reseau", reseau)).findList();
	}
	
	public static void creer(Ligne ligne)
	{
		ligne.save();
	}
	
	public static void supprimer(String libelle)
	{
		find.ref(libelle).delete();
	}
	
	public static Ligne detail(String libelle)
	{
		return find.ref(libelle);
	}
	
	public static Map<String, String> selectOptions(Reseau reseau)
	{
		Map<String, String> mapOptions = new LinkedHashMap<String, String>();
		List<Ligne> listeLignes;
		
		if (reseau == null)
		{
			listeLignes = lister();
		}
		else
		{
			listeLignes = listerSelonReseau(reseau);
		}
		
		for (Ligne ligne : listeLignes)
		{
			mapOptions.put("" + ligne.libelle, ligne.libelle);
		}
		
		return mapOptions;
	}
	
	public Reseau getReseau()
	{
		return reseau;
	}
	
	public String toString()
	{
		return libelle;
	}
}