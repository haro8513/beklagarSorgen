package controllers;

import play.mvc.*;

import java.util.List;

import javax.inject.Inject;

import com.avaje.ebean.Model;

import models.Person;
import play.*;
import play.data.Form;
import play.data.FormFactory;
import static play.libs.Json.toJson;

public class PersonController extends Controller {
	private FormFactory formFactory;
	
	@Inject
	public PersonController(FormFactory formFactory)
	{
		this.formFactory = formFactory;
	}

	public Result addPerson()
	{
		Form<Person> personForm = formFactory.form(Person.class);
		Person person = personForm.bindFromRequest().get();
		person.save();
		return redirect(routes.HomeController.index());
	}
	
	public Result getPersons()
	{
		List<Person> persons = new Model.Finder<String,Person>(String.class,Person.class).all();
		return ok(toJson(persons));
	}
	
}
