package net.codeJava.caveAVin.controller;

import java.io.*;
import java.util.HashMap;

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

import net.codeJava.caveAVin.classes.Bouteille;
import net.codeJava.caveAVin.classes.ListOfUsers;
import net.codeJava.caveAVin.classes.User;
import net.codeJava.caveAVin.jsonObject.LoginForm;
import net.codeJava.caveAVin.jsonObject.ModifForm;

@Controller
public class HomeController {
	
	private User currentUser = null;
	
	@PostMapping(value = "/connexion", consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")
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
	
	@PostMapping(value = "/connexion", consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public void deconnexion(@RequestBody LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) throws IOException {	
		this.currentUser = null;
	}
	
	@RequestMapping(value="/ajoutBouteille", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:4200")
	public void ajoutUtilisateur(@RequestBody LoginForm loginForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ListOfUsers.getInstance().ajoutUtilisateur(loginForm.getNom(), loginForm.getPrenom(), loginForm.getLogin(), loginForm.getPassword());
	}
	
	@RequestMapping(value="/ajoutBouteille", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:4200")
	public void ajoutBouteille(@RequestBody ModifForm modifForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.currentUser.getCave().ajoutNouvelleBouteille(new Bouteille(modifForm.getNom(), modifForm.getCepage(), modifForm.getAnnee(), modifForm.getAnnee()), modifForm.getQuantite());
	}
	
	@RequestMapping(value="/supprimerBouteille", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public void supprimerBouteille(@RequestBody ModifForm modifForm, HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		if(this.currentUser.getCave().bouteilleExiste(modifForm.getId())) {
			this.currentUser.getCave().supprimerBouteille(modifForm.getId());
			response.getWriter().write("1");
		} else {
			response.getWriter().write("-1");
		}
	}
	
	@PostMapping(value = "/modifierBouteille", consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")	
	public void modifierQuantite(@RequestBody ModifForm modifForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
				
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    		
		if(this.currentUser.getCave().bouteilleExiste(modifForm.getId())) {
			this.currentUser.getCave().modifierBouteille(modifForm);
			response.getWriter().write("1");
		} else {
			response.getWriter().write("-1");
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
	
	@RequestMapping(value="/recupererBouteilles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:4200")
	public void returnBouteille(@RequestBody ModifForm modifForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		Bouteille b = null;
		
		if(this.currentUser.getCave().bouteilleExiste(modifForm.getId())) {
			b = this.currentUser.getCave().recupBouteille(modifForm.getId());
		} 
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		if(b != null) {
			String bouteille = new Gson().toJson(b);
			
		    response.getWriter().write(bouteille);
		} else {
			response.getWriter().write("-1");
		}
	}
}
