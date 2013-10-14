package controllers;

import models.Ligne;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class LigneControleur 
extends Controller
{
	static Form<Ligne> ligneForm = form(Ligne.class);
	
	public static Result listerLignes()
	{
		return ok(views.html.ligne.render(Ligne.lister(), ligneForm));
	}
	
	public static Result ajouterLigne()
	{
		Form<Ligne> filledForm = ligneForm.bindFromRequest();
		
		if (filledForm.hasErrors()) 
		{
			return badRequest(views.html.ligne.render(Ligne.lister(), filledForm));
		}
		else 
		{
			Ligne.creer(filledForm.get());
		    return redirect(routes.LigneControleur.listerLignes());  
		}
	}
	
	public static Result supprimerLigne(String libelle)
	{
		Ligne.supprimer(libelle);
		return redirect(routes.LigneControleur.listerLignes());
	}
	
	public static Result detailLigne(String libelle)
	{
		Ligne.detail(libelle);
		return redirect(routes.LigneControleur.listerLignes());
		//TODO
	}
}