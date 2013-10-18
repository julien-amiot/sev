package controllers;

import java.io.IOException;
import java.util.Map;

import models.Ligne;
import models.Reseau;

import org.codehaus.jackson.map.ObjectMapper;

import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import utils.OptionSelect;

public class LigneControleur 
extends Controller
{
	static Form<Ligne> ligneForm = form(Ligne.class);
	
	public static Result listerLignes()
	{
		return ok(views.html.ligne.render(Ligne.lister(), ligneForm));
	}
	
	public static Result listerLignesJSON()
	{
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
	    Reseau reseau = Reseau.detail(parameters.get("critere")[0]);
		ObjectMapper mapper = new ObjectMapper();

		try
		{
			String lignesJSON = mapper.writeValueAsString(OptionSelect.convertitDepuisMap(Ligne.selectOptions(reseau)));
			return ok(lignesJSON);
		}
		catch (IOException ioe)
		{
			return badRequest(Messages.get("serveur.erreur.general"));
		}
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