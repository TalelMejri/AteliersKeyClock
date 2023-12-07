package org.dsi.web;

import org.dsi.entity.Supplier;
import org.dsi.repo.SupplierRepo;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;

@RestController
@RequestMapping("/all")
public class SupplierController {
	
	    @Autowired
	    private SupplierRepo supplierRepo;
	
	    @GetMapping
	    @PreAuthorize("hasRole('ROLE_client-admin')")
	    public java.util.List<Supplier> products(Model model){
	        return supplierRepo.findAll();
	    }
	    
	   /* @ExceptionHandler(Exception.class)
	    public String exceptionHandler(Exception e, Model model){
	        model.addAttribute("errorMessage","problème d'autorisation");
	        return "errors";
	    }*/

	    /*@GetMapping("/jwt")
	    @ResponseBody
	    public Map<String,String> map(HttpServletRequest request){
	        KeycloakAuthenticationToken token =(KeycloakAuthenticationToken) request.getUserPrincipal();
	        KeycloakPrincipal principal=(KeycloakPrincipal)token.getPrincipal();
	        KeycloakSecurityContext keycloakSecurityContext=principal.getKeycloakSecurityContext();
	        Map<String,String> map = new HashMap<>();
	        map.put("access_token", keycloakSecurityContext.getTokenString());
	        return map;
	    }*/

}
