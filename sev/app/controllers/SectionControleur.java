package controllers;

import models.Ligne;
import models.Section;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class SectionControleur 
extends Controller
{
	static Form<Section> sectionForm = form(Section.class);
	
	public static Result listerSections()
	{
		return ok(views.html.section.render(Section.lister(), sectionForm));
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
	
	public static Result supprimerSection(Integer id)
	{
		Section.supprimer(id);
		return redirect(routes.SectionControleur.listerSections());
	}
}