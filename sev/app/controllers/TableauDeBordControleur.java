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

public class TableauDeBordControleur 
extends Controller
{
	public static Result index()
	{
		return ok(views.html.tableauDeBord.render(Station.lister()));
	}
}