package net.codeJava.caveAVin.controller;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import net.codeJava.caveAVin.classes.Bouteille;
import net.codeJava.caveAVin.classes.Cave;

@Controller
public class HomeController {
	
	@RequestMapping(value="/ajoutBouteille", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public void ajoutBouteille(@RequestBody Map<String, String> foo, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nom = foo.get("nom");
		String cepage = foo.get("cepage");
		String annee = foo.get("annee");
		String description = foo.get("description");
		int quantite= Integer.parseInt(foo.get("quantite"));
		
		Cave.getInstance().ajoutNouvelleBouteille(new Bouteille(nom, cepage, annee, description), quantite);
	}
	
	@RequestMapping(value="/supprimerBouteille", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public void supprimerBouteille(@RequestBody Map<String, Integer> foo, HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = foo.get("id");
		
		if(Cave.getInstance().bouteilleExiste(id)) {
			Cave.getInstance().supprimerBouteille(id);
		}
	}
	
	@RequestMapping(value="/modifierBouteille", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public void modifierQuantite(@RequestBody Map<String, Integer> foo, HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = foo.get("id");
		int quantite = foo.get("quantite");
		
		if(Cave.getInstance().bouteilleExiste(id)) {
			Cave.getInstance().changerQuantite(id, quantite);
		}
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public void returnBouteilles(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HashMap<Bouteille, Integer> caveAVin = Cave.getInstance().getCave();
		String bouteillesJsonString = new Gson().toJson(caveAVin.keySet());
		
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(bouteillesJsonString);
	}
}
