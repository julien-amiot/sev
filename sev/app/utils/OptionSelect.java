package utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionSelect 
implements
	Serializable
{
	private static final long serialVersionUID = 1L;
	String clef;
	String valeur;
	
	public OptionSelect(String clef, String valeur)
	{
		this.clef = clef;
		this.valeur = valeur;
	}
	
	public static List<OptionSelect> convertitDepuisMap(Map<String, String> map)
	{
		List<OptionSelect> listeRetournee = new ArrayList<OptionSelect>();
		
		for(Map.Entry<String, String> tuple : map.entrySet())
		{
			listeRetournee.add(new OptionSelect(tuple.getKey(), tuple.getValue()));
		}
		
		return listeRetournee;
	}
	
	public static Map<String, String> listeVide()
	{
		return new HashMap<String, String>();
	}

	public String getClef() 
	{
		return clef;
	}

	public void setClef(String clef) 
	{
		this.clef = clef;
	}

	public String getValeur() 
	{
		return valeur;
	}

	public void setValeur(String valeur) 
	{
		this.valeur = valeur;
	}
}