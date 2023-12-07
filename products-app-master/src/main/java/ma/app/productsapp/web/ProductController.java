package ma.app.productsapp.web;

import ma.app.productsapp.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;


@EnableMethodSecurity
@Controller
public class ProductController{
	
	@Autowired
	OAuth2AuthorizedClientService oauth2ClientService;
	
	@Autowired
	RestTemplate restTemplate;
	
    @Autowired
    private ProductRepository productRepository;
   

    @GetMapping("/")
    //@PreAuthorize("hasRole('client-user')")
    public String index(){
        return "index";
    }
    @GetMapping("/products")
    //@PreAuthorize("hasRole('client-admin')")
    public String products(Model model,@AuthenticationPrincipal OidcUser principal
			) {
		
		  Authentication authentication =
		  SecurityContextHolder.getContext().getAuthentication();
		  OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
		  authentication;
		  
		  OAuth2AuthorizedClient oauth2Client =
		  oauth2ClientService.loadAuthorizedClient(oauthToken.
		  getAuthorizedClientRegistrationId(), oauthToken.getName());
		  
		  String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		  System.out.println("jwtAccessToken = " + jwtAccessToken);
		  
		  
		  System.out.println("Principal = " + principal);
		  
		  OidcIdToken idToken = principal.getIdToken(); 
		  String idTokenValue = idToken.getTokenValue(); 
		  System.out.println("idTokenValue = " + idTokenValue);
		  model.addAttribute("products",productRepository.findAll());
		  return "products";
    }
    
    @GetMapping("/suppliers")
    public String suppliers(Model model,@AuthenticationPrincipal OidcUser principal
			) {
		
		  Authentication authentication =
		  SecurityContextHolder.getContext().getAuthentication();
		  OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
		  authentication;
		  
		  OAuth2AuthorizedClient oauth2Client =
		  oauth2ClientService.loadAuthorizedClient(oauthToken.
		  getAuthorizedClientRegistrationId(), oauthToken.getName());
		  
		  String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		  System.out.println("jwtAccessToken = " + jwtAccessToken);
		  
		  
		  System.out.println("Principal = " + principal);
		  
		  OidcIdToken idToken = principal.getIdToken(); 
		  String idTokenValue = idToken.getTokenValue(); 
		  System.out.println("idTokenValue = " + idTokenValue);
    	  String url = "http://localhost:8082/all"; 
		  HttpHeaders headers = new HttpHeaders(); 
		  headers.add("Authorization", "Bearer " + jwtAccessToken);
		  HttpEntity<List<Supplier>> entity = new HttpEntity<>(headers);
		  ResponseEntity<List<Supplier>> responseEntity = restTemplate.exchange(url,
		  HttpMethod.GET, entity, new ParameterizedTypeReference<List<Supplier>>()
		  {});
		  
		  List<Supplier> pageSuppliers = responseEntity.getBody();
		  model.addAttribute("suppliers",pageSuppliers);
		  return "suppliers";
    }
    
    @GetMapping("/all")
    public String allsuppliers(Model model,@AuthenticationPrincipal OidcUser principal
			) {
		
		  Authentication authentication =
		  SecurityContextHolder.getContext().getAuthentication();
		  OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken)
		  authentication;
		  
		  OAuth2AuthorizedClient oauth2Client =
		  oauth2ClientService.loadAuthorizedClient(oauthToken.
		  getAuthorizedClientRegistrationId(), oauthToken.getName());
		  
		  String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();
		  System.out.println("jwtAccessToken = " + jwtAccessToken);
		  
		  
		  System.out.println("Principal = " + principal);
		  
		  OidcIdToken idToken = principal.getIdToken(); 
		  String idTokenValue = idToken.getTokenValue(); 
		  System.out.println("idTokenValue = " + idTokenValue);
    
		  String url = "http://localhost:8082/suppliers"; 
		  HttpHeaders headers = new HttpHeaders(); 
		  headers.add("Authorization", "Bearer " + jwtAccessToken);
		  HttpEntity<List<Supplier>> entity = new HttpEntity<>(headers);
		  ResponseEntity<PagedModel<Supplier>>response = restTemplate.exchange(url,
				  HttpMethod.GET, entity, new ParameterizedTypeReference<PagedModel<Supplier>>()
				  {});
		  
	        model.addAttribute("supplier",response.getBody().toString());
	        return "supphateoas";
    }
  
}
class Supplier{
    private Long id;
    private String name;
    private String email;
   
	public Supplier() {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Supplier [id=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
    
}
