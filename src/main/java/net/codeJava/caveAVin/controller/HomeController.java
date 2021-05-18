package net.codeJava.caveAVin.controller;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;

import jsonObject.LoginForm;
import jsonObject.ModifForm;
import net.codeJava.caveAVin.classes.Bouteille;
import net.codeJava.caveAVin.classes.ListOfUsers;
import net.codeJava.caveAVin.classes.User;

@Controller
public class HomeController {
	
	private User currentUser = null;
	
	@PostMapping(value = "/connexion", consumes = "application/json", produces = "application/json")
	public void connexion(@RequestBody LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		for(User u: ListOfUsers.getInstance().getList()) {
			if(u.getLogin().equals(loginForm.getLogin()) && u.getPassword().equals(loginForm.getPassword())) {
				this.currentUser = u;
			}
		}
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		if(this.currentUser != null) {
			String userconnected = new Gson().toJson(this.currentUser);
			
		    response.getWriter().write(userconnected);
		} else {
		    response.getWriter().write("-1");
		}
	}
	
	@RequestMapping(value="/ajoutBouteille", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	public void ajoutBouteille(@RequestBody MultiValueMap<String, String> foo, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nom = foo.get("nom").toString().replaceAll("[\\[\\]]", "");
		String cepage = foo.get("cepage").toString().replaceAll("[\\[\\]]", "");
		String annee = foo.get("annee").toString().replaceAll("[\\[\\]]", "");
		String description = foo.get("description").toString().replaceAll("[\\[\\]]", "");
		int quantite= Integer.parseInt(foo.get("quantite").toString().replaceAll("[\\[\\]]", ""));
		
		this.currentUser.getCave().ajoutNouvelleBouteille(new Bouteille(nom, cepage, annee, description), quantite);
	}
	
	@RequestMapping(value="/supprimerBouteille", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public void supprimerBouteille(@RequestBody ModifForm modifForm, HttpServletRequest request, HttpServletResponse response) throws IOException {

		if(this.currentUser.getCave().bouteilleExiste(modifForm.getId())) {
			this.currentUser.getCave().supprimerBouteille(modifForm.getId());
		}
	}
	
	@PostMapping(value = "/modifierBouteille", consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")	
	public void modifierQuantite(@RequestBody ModifForm modifForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
				
		if(this.currentUser.getCave().bouteilleExiste(modifForm.getId())) {
			this.currentUser.getCave().changerQuantite(modifForm.getId(), modifForm.getQuantite());
		}
	}
	
	@RequestMapping(value="/recupererBouteilles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	public void returnBouteilles(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<Bouteille, Integer> caveAVin = this.currentUser.getCave().getCave();
		String bouteillesJsonString = new Gson().toJson(caveAVin.keySet());
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(bouteillesJsonString);
	}
}
