import java.util.List;
import java.util.Map;

import models.EtatCCVM;
import models.EtatVentilateur;
import models.Ligne;
import models.Reseau;
import models.Section;
import models.Station;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

import com.avaje.ebean.Ebean;

public class Global 
extends GlobalSettings 
{
    public void onStart(Application app) 
    {
    	DonneesBouchon.insert(app);
    }
    
    static class DonneesBouchon 
    {
        public static void insert(Application app) 
        {
        	Map<String,List<Object>> all = (Map<String,List<Object>>)Yaml.load("donneesBouchon.yml");
        	
            if (Ebean.find(Reseau.class).findRowCount() == 0) 
            {
            	Ebean.save(all.get("reseaux"));
            }
            
            if (Ebean.find(Ligne.class).findRowCount() == 0) 
            {
            	Ebean.save(all.get("lignes"));
            }
            
            if (Ebean.find(Section.class).findRowCount() == 0) 
            {
            	Ebean.save(all.get("sections"));
            }
            
            if (Ebean.find(EtatCCVM.class).findRowCount() == 0) 
            {
            	Ebean.save(all.get("etatsCCVM"));
            }
            
            if (Ebean.find(EtatVentilateur.class).findRowCount() == 0) 
            {
            	Ebean.save(all.get("etatsVentilateur"));
            }
            
            if (Ebean.find(Station.class).findRowCount() == 0) 
            {
            	List<Object> listeDesStations = all.get("stations");
            	Ebean.save(listeDesStations);
            }
        }
    }
}