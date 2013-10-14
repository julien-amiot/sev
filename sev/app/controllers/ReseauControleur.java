package controllers;

import models.Ligne;
import models.Reseau;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class ReseauControleur 
extends Controller
{
	static Form<Reseau> reseauForm = form(Reseau.class);
	
	public static Result listerReseaux()
	{
		return ok(views.html.reseau.render(Reseau.lister(), reseauForm));
	}
	
	public static Result ajouterReseau()
	{
		Form<Reseau> filledForm = reseauForm.bindFromRequest();
		
		if (filledForm.hasErrors()) 
		{
			return badRequest(views.html.reseau.render(Reseau.lister(), filledForm));
		}
		else 
		{
			Reseau.creer(filledForm.get());
		    return redirect(routes.ReseauControleur.listerReseaux());  
		}
	}
	
	public static Result supprimerReseau(String libelle)
	{
		Reseau.supprimer(libelle);
		return redirect(routes.ReseauControleur.listerReseaux());
	}
	
	public static Result detailReseau(String libelle)
	{
		Reseau.detail(libelle);
		return redirect(routes.ReseauControleur.listerReseaux());
		//TODO
	}
}