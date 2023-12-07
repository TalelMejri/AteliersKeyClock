package ma.app.productsapp.web;

import lombok.Data;
import ma.app.productsapp.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
	RestTemplate RestTemplate;
	
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
  
}
@Data
class Supplier{
    private Long id;
    private String name;
    private String email;
}
