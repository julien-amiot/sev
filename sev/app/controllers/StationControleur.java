package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import models.Ligne;
import models.Station;

import org.codehaus.jackson.map.ObjectMapper;

import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import utils.OptionSelect;

public class StationControleur 
extends Controller
{
	static Form<Station> stationForm = form(Station.class);
	
	public static Result listerStations()
	{
		return ok(views.html.station.render(Station.lister(), stationForm));
	}
	
	public static Result listerStationsSelonLigneJSON()
	{
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
	    Ligne ligne = Ligne.detail(parameters.get("critere")[0]);
		ObjectMapper mapper = new ObjectMapper();

		try
		{
			String stationsJSON = mapper.writeValueAsString(OptionSelect.convertitDepuisMap(Station.selectOptions(ligne)));
			return ok(stationsJSON);
		}
		catch (IOException ioe)
		{
			return badRequest(Messages.get("serveur.erreur.general"));
		}
	}
	
	public static Result ajouterStation()
	{
		Map<String, String> newData = new HashMap<String, String>();
		Map<String, String[]> urlFormEncoded = play.mvc.Controller.request().body().asFormUrlEncoded();
        
        if (urlFormEncoded != null) 
        {
        	for (String key : urlFormEncoded.keySet()) 
        	{
        		String[] value = urlFormEncoded.get(key);
        		
        		if (value.length == 1 && !key.endsWith("[]")) 
        		{
        			newData.put(key, value[0]);
        		}
        		else if (value.length > 1 || key.endsWith("[]")) 
        		{
        			String keyPrefix = key.replace("[]", "");
        			String keyPostfix = "";
        			int pos = key.indexOf(".");
        			
        			if (pos > -1) 
        			{
        				keyPrefix = key.substring(0, pos);
        				keyPostfix = key.substring(pos, key.length()).replace("[]", "");
        			}
        			
        			for (int i = 0; i < value.length; i++) 
        			{
        				newData.put(keyPrefix + "[" + i + "]" + keyPostfix, value[i]);
        			}
        		}
        	}
        }

		Form<Station> filledForm = form(Station.class).bind(newData);
		
		if (filledForm.hasErrors()) 
		{
			return badRequest(views.html.station.render(Station.lister(), filledForm));
		}
		else 
		{
			Station.creer(filledForm.get());
		    return redirect(routes.StationControleur.listerStations());  
		}
	}
	
	public static Result supprimerStation(Integer id)
	{
		Station.supprimer(id);
		return redirect(routes.StationControleur.listerStations());
	}
	
	public static Result detailStation(Integer id)
	{
		Station.recupererSelonId(id);
		return redirect(routes.StationControleur.listerStations());
		//TODO
	}
}