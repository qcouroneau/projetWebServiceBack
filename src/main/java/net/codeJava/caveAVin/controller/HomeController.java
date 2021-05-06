package net.codeJava.caveAVin.controller;

import java.io.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping(value="/ajoutBouteille", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public void ajoutBouteille(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}
	
	@RequestMapping(value="/supprimerBouteille", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public void supprimerBouteille(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}
	
	@RequestMapping(value="/modifierBouteille", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:3000")
	@ResponseBody
	public void modifierQuantite(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	}
}
