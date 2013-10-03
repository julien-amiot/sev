package controllers;

import models.Ventilateur;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application 
extends 
	Controller 
{
	static Form<Ventilateur> ventilateurForm = form(Ventilateur.class);
	
	public static Result index() 
	{
		return redirect(routes.Application.listerVentilateurs());
	}
	
	public static Result listerVentilateurs()
	{
		return ok(views.html.index.render(Ventilateur.lister(), ventilateurForm));
	}
	
	public static Result ajouterVentilateur()
	{
		Form<Ventilateur> filledForm = ventilateurForm.bindFromRequest();
		
		if (filledForm.hasErrors()) 
		{
			return badRequest(views.html.index.render(Ventilateur.lister(), filledForm));
		}
		else 
		{
			Ventilateur.creer(filledForm.get());
		    return redirect(routes.Application.listerVentilateurs());  
		}
	}
	
	public static Result supprimerVentilateur(Integer id)
	{
		Ventilateur.supprimer(id);
		return redirect(routes.Application.listerVentilateurs());
	}
}