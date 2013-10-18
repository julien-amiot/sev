package controllers;

import java.io.IOException;
import java.util.Map;

import models.Ligne;
import models.Section;

import org.codehaus.jackson.map.ObjectMapper;

import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import utils.OptionSelect;

public class SectionControleur 
extends Controller
{
	static Form<Section> sectionForm = form(Section.class);
	
	public static Result listerSections()
	{
		return ok(views.html.section.render(Section.lister(), sectionForm));
	}
	
	public static Result listerSectionsJSON()
	{
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
	    Ligne ligne = Ligne.detail(parameters.get("critere")[0]);
		ObjectMapper mapper = new ObjectMapper();

		try
		{
			String sectionsJSON = mapper.writeValueAsString(OptionSelect.convertitDepuisMap(Section.selectOptions(ligne)));
			return ok(sectionsJSON);
		}
		catch (IOException ioe)
		{
			return badRequest(Messages.get("serveur.erreur.general"));
		}
	}
	
	public static Result ajouterSection()
	{
		Form<Section> filledForm = sectionForm.bindFromRequest();
		
		if (filledForm.hasErrors()) 
		{
			return badRequest(views.html.section.render(Section.lister(), filledForm));
		}
		else 
		{
			Section.creer(filledForm.get());
		    return redirect(routes.SectionControleur.listerSections());  
		}
	}
	
	public static Result supprimerSection(String libelle)
	{
		Section.supprimer(libelle);
		return redirect(routes.SectionControleur.listerSections());
	}
	
	public static Result detailSection(String libelle)
	{
		Section.detail(libelle);
		return redirect(routes.SectionControleur.listerSections());
		//TODO
	}
}