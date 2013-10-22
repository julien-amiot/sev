package controllers;

import models.Ventilateur;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Application 
extends 
	Controller 
{
	static Form<Ventilateur> ventilateurForm = form(Ventilateur.class);
	
	public static Result index() 
	{
		return redirect(routes.StationControleur.listerStations());
	}
}