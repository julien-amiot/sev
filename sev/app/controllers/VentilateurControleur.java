package controllers;

import models.Ventilateur;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class VentilateurControleur 
extends Controller
{
	static Form<Ventilateur> ventilateurForm = form(Ventilateur.class);
	
	public static Result listerVentilateurs()
	{
		return ok(views.html.ventilateur.render(Ventilateur.lister(), ventilateurForm));
	}
	
	public static Result ajouterVentilateur()
	{
		Form<Ventilateur> filledForm = ventilateurForm.bindFromRequest();
		
		if (filledForm.hasErrors()) 
		{
			return badRequest(views.html.ventilateur.render(Ventilateur.lister(), filledForm));
		}
		else 
		{
			Ventilateur.creer(filledForm.get());
		    return redirect(routes.VentilateurControleur.listerVentilateurs());  
		}
	}
	
	public static Result supprimerVentilateur(Integer id)
	{
		Ventilateur.supprimer(id);
		return redirect(routes.VentilateurControleur.listerVentilateurs());
	}
}