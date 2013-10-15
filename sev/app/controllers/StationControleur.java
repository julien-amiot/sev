package controllers;

import java.util.HashMap;
import java.util.Map;

import models.Station;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class StationControleur 
extends Controller
{
	static Form<Station> stationForm = form(Station.class);
	
	public static Result listerStations()
	{
		return ok(views.html.station.render(Station.lister(), stationForm));
	}
	
	public static Result ajouterStation()
	{
		/*Form<Station> filledForm = stationForm.bindFromRequest();*/
		
		Map<String, String> newData = new HashMap<String, String>();
		Map<String, String[]> urlFormEncoded = play.mvc.Controller.request().body().asFormUrlEncoded();
		
		/*if (urlFormEncoded != null) 
		{
			for (String key : urlFormEncoded.keySet()) 
			{
				String[] value = urlFormEncoded.get(key);
				
				if (value.length == 1) 
				{
					newData.put(key, value[0]);
				}
				else if (value.length > 1) 
				{
					for (int i = 0; i < value.length; i++) 
					{
						newData.put(key + "[" + i + "]", value[i]);
					}
				}
			}
		}*/
        
        if (urlFormEncoded != null) 
        {
        	for (String key : urlFormEncoded.keySet()) 
        	{
        		String[] value = urlFormEncoded.get(key);
        		
        		if (value.length == 1) 
        		{
        			newData.put(key, value[0]);
        		}
        		else if (value.length > 1) 
        		{
        			String keyPrefix = key;
        			String keyPostfix = "";
        			int pos = key.indexOf(".");
        			
        			if (pos > -1) 
        			{
        				keyPrefix = key.substring(0, pos);
        				keyPostfix = key.substring(pos, key.length());
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
		Station.detail(id);
		return redirect(routes.StationControleur.listerStations());
		//TODO
	}
}