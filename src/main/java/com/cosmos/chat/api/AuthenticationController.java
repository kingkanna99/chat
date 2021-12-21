package com.cosmos.chat.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.cosmos.chat.domain.model.Role;
import com.cosmos.chat.domain.model.User;
import com.cosmos.chat.domain.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
   
    @Autowired
	private UserService userService;


	@GetMapping("/")
	public String start() {
		return "pagina iniziale";
	}

	@PostMapping("/new-account")
	public String newAccount(@Valid @RequestBody User user) {
        userService.createUser(user);
		return "ok aggiunto con successo";
	}

    @PostMapping("/login")
    public String myLogin(@RequestBody User user, HttpServletRequest req){
        return userService.login(user.getUsername(), user.getPassword(), req);
    }

    @PostMapping("/new-role")
    public String newRole(@RequestBody Role role){
        userService.createRole(role);
        return "ruolo creato";
    }

    @GetMapping("/prova")
    public String prova(HttpServletRequest req){
        return "prova" + req.getSession().getAttribute("login");
    }

    @GetMapping("/invalidate")
    public String invalidate(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        return userService.invalidate(username, request);
    }

    @PostMapping("/delete-User")
    public String deleteUser(@RequestBody String username, HttpServletRequest request){
        return userService.deleteUser(username, request);
    }
}












