package gr8.imb3.progra3.dto;

public class JwtResponse {
	   private String token;

	    public JwtResponse(String token) {
	        this.token = token;
	    }

	    public String getToken() {
	        return token;
	    }
}
