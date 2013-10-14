package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class EtatCCVM 
extends	Model 
{
	private static final long serialVersionUID = 1L;
	public static Finder<String, EtatCCVM> find = new Finder<String, EtatCCVM>(String.class, EtatCCVM.class);
	@Id
	public String libelle;

	public static List<EtatCCVM> lister()
	{
		return find.all();
	}
	
	public static void creer(EtatCCVM etatCCVM)
	{
		etatCCVM.save();
	}
	
	public static EtatCCVM detail(String libelle)
	{
		return find.ref(libelle);
	}
	
	public static void supprimer(String libelle)
	{
		find.ref(libelle).delete();
	}
}