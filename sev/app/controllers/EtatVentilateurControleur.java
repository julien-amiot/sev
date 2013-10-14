package controllers;

import models.EtatVentilateur;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class EtatVentilateurControleur 
extends Controller
{
	static Form<EtatVentilateur> etatVentilateurForm = form(EtatVentilateur.class);
	
	public static Result listerEtatsVentilateur()
	{
		return ok(views.html.etatVentilateur.render(EtatVentilateur.lister(), etatVentilateurForm));
	}
	
	public static Result ajouterEtatVentilateur()
	{
		Form<EtatVentilateur> filledForm = etatVentilateurForm.bindFromRequest();
		
		if (filledForm.hasErrors()) 
		{
			return badRequest(views.html.etatVentilateur.render(EtatVentilateur.lister(), filledForm));
		}
		else 
		{
			EtatVentilateur.creer(filledForm.get());
		    return redirect(routes.EtatVentilateurControleur.listerEtatsVentilateur());  
		}
	}
	
	public static Result supprimerEtatVentilateur(String libelle)
	{
		EtatVentilateur.supprimer(libelle);
		return redirect(routes.EtatVentilateurControleur.listerEtatsVentilateur());
	}
	
	public static Result detailEtatVentilateur(String libelle)
	{
		EtatVentilateur.detail(libelle);
		return redirect(routes.EtatVentilateurControleur.listerEtatsVentilateur());
	}
}