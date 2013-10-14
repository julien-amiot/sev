package controllers;

import models.Zone;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class ZoneControleur 
extends Controller
{
	static Form<Zone> zoneForm = form(Zone.class);
	
	public static Result listerZones()
	{
		return ok(views.html.zone.render(Zone.lister(), zoneForm));
	}
	
	public static Result ajouterZone()
	{
		Form<Zone> filledForm = zoneForm.bindFromRequest();
		
		if (filledForm.hasErrors()) 
		{
			return badRequest(views.html.zone.render(Zone.lister(), filledForm));
		}
		else 
		{
			Zone.creer(filledForm.get());
		    return redirect(routes.ZoneControleur.listerZones());  
		}
	}
	
	public static Result supprimerZone(String libelle)
	{
		Zone.supprimer(libelle);
		return redirect(routes.ZoneControleur.listerZones());
	}
	
	public static Result detailZone(String libelle)
	{
		Zone.detail(libelle);
		return redirect(routes.ZoneControleur.listerZones());
	}
}