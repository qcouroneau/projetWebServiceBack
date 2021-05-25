package net.codeJava.caveAVin.controller;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;

import net.codeJava.caveAVin.classes.Bouteille;
import net.codeJava.caveAVin.classes.ListOfUsers;
import net.codeJava.caveAVin.classes.User;
import net.codeJava.caveAVin.jsonObject.UserForm;
import net.codeJava.caveAVin.jsonObject.BouteilleForm;

@Controller
public class HomeController {
	
	private User currentUser = null;
	
	/*
	 * Fonction qui permet de rÃ©cupÃ©rer un login et un mot de passe pour vÃ©rifier si l'utilisateur existe
	 * Si l'utilisateur existe, ce servlet va permettre la connexion
	 */
	@PostMapping(value = "/connexion", consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public void connexion(@RequestBody UserForm loginForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
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
	
	/* 
	 * Fonction qui dÃ©connecte l'utilisateur courant de sa session
	 */
	@PostMapping(value = "/deconnexion", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public void deconnexion(HttpServletRequest request, HttpServletResponse response) throws IOException {	
		this.currentUser = null;
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		if(this.currentUser == null) {
		    response.getWriter().write("1");
		} else {
		    response.getWriter().write("-1");
		}
	}
	
	/*
	 * Fonction qui rÃ©cupÃ¨re un Nom, un prenom, un login, un mot de passe et un email, dans le but d'ajouter un nouvel utilisateur Ã  la liste des utilisateurs
	 */
	@RequestMapping(value="/ajoutUtilisateur", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:4200")
	public void ajoutUtilisateur(@RequestBody UserForm loginForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
		ListOfUsers.getInstance().ajoutUtilisateur(loginForm.getNom(), loginForm.getPrenom(), loginForm.getLogin(), loginForm.getPassword(), loginForm.getEmail());
	}
	
	/*
	 * Fonction qui rÃ©cupÃ¨re un nom, un cÃ©page, une annÃ©e, une description et une quantitÃ© pour ajouter une nouvelle bouteille Ã  la liste des bouteilles
	 */
	@RequestMapping(value="/ajoutBouteille", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:4200")
	public void ajoutBouteille(@RequestBody BouteilleForm modifForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.currentUser.getCave().ajoutNouvelleBouteille(new Bouteille(modifForm.getNom(), modifForm.getCepage(), modifForm.getAnnee(), modifForm.getDescription()), modifForm.getQuantite());
	}
	
	/*
	 * Fonction qui permet de supprimer un bouteille de la liste grÃ¢ce Ã  son id
	 */
	@RequestMapping(value="/supprimerBouteille/{string}", method = RequestMethod.DELETE, consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public void supprimerBouteille(@PathVariable("string") String id, HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		if(this.currentUser.getCave().bouteilleExiste(Integer.parseInt(id))) {
			this.currentUser.getCave().supprimerBouteille(Integer.parseInt(id));
			response.getWriter().write("1");
		} else {
			response.getWriter().write("-1");
		}
	}
	
	/*
	 * Fonction qui permet de modifier la quatitÃ© de bouteilles disponible, pour une bouteille donnÃ©e
	 */
	@PostMapping(value = "/modifierBouteille", consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")	
	public void modifierQuantite(@RequestBody BouteilleForm modifForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
				
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    		
		if(this.currentUser.getCave().bouteilleExiste(modifForm.getId())) {
			this.currentUser.getCave().modifierBouteille(modifForm);
			response.getWriter().write("1");
		} else {
			response.getWriter().write("-1");
		}
	}
	
	/*
	 * Fonction qui permet Ã  utilisateur de modifier ses informations (mot de passe, nom, email,... ) 
	 */
	@PostMapping(value = "/modifierUtilisateur", consumes = "application/json", produces = "application/json")
	@CrossOrigin(origins = "http://localhost:4200")	
	public void modifierUtilisateur(@RequestBody UserForm loginForm, HttpServletRequest request, HttpServletResponse response) throws IOException {
				
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
	    this.currentUser.modifierUtilisateur(loginForm);
	}
	
	/*
	 * Fonction qui permet de rÃ©cupÃ©rer l'ensemble des bouteilles, et leurs informations prÃ©sentes dans la liste 
	 */
	@RequestMapping(value="/recupererBouteilles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:4200")
	@ResponseBody
	public void returnBouteilles(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<Bouteille, Integer> caveAVin = this.currentUser.getCave().getCave();
		String bouteillesJsonString = "[";
		StringBuffer sb= new StringBuffer();
		
		Iterator<Map.Entry<Bouteille, Integer>> iterator = caveAVin.entrySet().iterator();
		
		while(iterator.hasNext()) {
			Map.Entry<Bouteille, Integer> entry = iterator.next();	
			sb.append(new Gson().toJson(entry.getKey()));
			
			sb.deleteCharAt(sb.length()-1);
			bouteillesJsonString += sb.toString();
			bouteillesJsonString += ",\n\"quantite\":\"" + entry.getValue() + "\"},";
			sb.setLength(0);
		}
		
		sb.append(bouteillesJsonString);
		sb.deleteCharAt(sb.length()-1);
		bouteillesJsonString = sb.toString();
		bouteillesJsonString += "]\n";
		
		System.out.println(bouteillesJsonString);
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(bouteillesJsonString);
	}
	
	/*
	 * Fonction qui permet de rÃ©cupÃ©rer une bouteille et ses informations, pour un ID donnÃ© 
	 */
	@RequestMapping(value="/recupererBouteille/{string}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:4200")
	public void returnBouteille(@PathVariable("string") String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		StringBuffer sb= new StringBuffer();
		Bouteille b = null;
		int quantite = 0;
		
		if(this.currentUser.getCave().bouteilleExiste(Integer.parseInt(id))) {
			b = this.currentUser.getCave().recupBouteille(Integer.parseInt(id));
			quantite = this.currentUser.getCave().recupQuantiteBouteille(b);
		} 
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
		
		if(b != null) {
			sb.append(new Gson().toJson(b));
			sb.deleteCharAt(sb.length()-1);
			String bouteille = sb.toString();
			bouteille += ",\"quantite\":\"" + quantite + "\"}";
		    response.getWriter().write(bouteille);
		} else {
			response.getWriter().write("-1");
		}
	}
}
