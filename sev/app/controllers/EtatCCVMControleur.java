package controllers;

import models.EtatCCVM;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class EtatCCVMControleur 
extends Controller
{
	static Form<EtatCCVM> etatCCVMForm = form(EtatCCVM.class);
	
	public static Result listerEtatsCCVM()
	{
		return ok(views.html.etatCCVM.render(EtatCCVM.lister(), etatCCVMForm));
	}
	
	public static Result ajouterEtatCCVM()
	{
		Form<EtatCCVM> filledForm = etatCCVMForm.bindFromRequest();
		
		if (filledForm.hasErrors()) 
		{
			return badRequest(views.html.etatCCVM.render(EtatCCVM.lister(), filledForm));
		}
		else 
		{
			EtatCCVM.creer(filledForm.get());
		    return redirect(routes.EtatCCVMControleur.listerEtatsCCVM());  
		}
	}
	
	public static Result supprimerEtatCCVM(String libelle)
	{
		EtatCCVM.supprimer(libelle);
		return redirect(routes.EtatCCVMControleur.listerEtatsCCVM());
	}
	
	public static Result detailEtatCCVM(String libelle)
	{
		EtatCCVM.detail(libelle);
		return redirect(routes.EtatCCVMControleur.listerEtatsCCVM());
	}
}