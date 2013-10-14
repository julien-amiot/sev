import play.*;
import play.libs.*;

import java.util.*;

import com.avaje.ebean.*;

import models.*;

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
        }
    }
}