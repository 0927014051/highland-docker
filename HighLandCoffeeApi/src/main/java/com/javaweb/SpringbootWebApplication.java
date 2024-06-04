package com.javaweb;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringbootWebApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebApplication.class, args);
	}
	@GetMapping("/message")
	public String messgae() {
		return "deploy success with azure ";
	}
	
	@GetMapping("/ip")
	public String get() throws UnknownHostException {
		
		InetAddress IP=InetAddress.getLocalHost();
		
		return IP.toString();
	}
	
	@GetMapping("/check-roles/all")
public String checkRoles(Authentication authentication) {
    if (authentication != null && authentication.isAuthenticated()) {
        System.out.println("Authorities: " + authentication.getAuthorities());
        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_CUSTOMER"))) {
            return "User has role CUSTOMER";
        } else {
            return "User does not have role CUSTOMER";
        }
    } else {
        return "User is not authenticated";
    }
}
	
}
