package com.cosmos.chat.chat.authentication.api;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.cosmos.chat.chat.authentication.domain.model.Role;
import com.cosmos.chat.chat.authentication.domain.model.User;
import com.cosmos.chat.chat.authentication.domain.service.UserService;

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
        //return "ciaooo" + user.getUsername() + user.getPassword();
    }

    @PostMapping("/new-role")
    public String newRole(@RequestBody Role role){
        //r1.setName("USER_ROLE");
        userService.createRole(role);
        return "ruolo creato";
    }

    @GetMapping("/prova")
    public String prova(HttpServletRequest req){
        return "prova" + req.getSession().getAttribute("login");
    }

    /*@GetMapping("/get-lista")
    public String getLista(HttpSession session){
        List<String> notes = (List<String>) session.getAttribute("NOTE_SESSION");
        if(notes==null){
            return "vuoto";
        }
        return notes.toString();
    }

    @PostMapping("/put-element")
    public String putElement(@RequestParam(value="elemento") String elemento, HttpServletRequest request){
        List<String> notes = (List<String>) request.getSession().getAttribute("NOTE_SESSION");
        if(notes==null){
            notes=new ArrayList<>();
            request.getSession().setAttribute("NOTE_SESSION", notes);
        }
        notes.add(elemento);
        request.getSession().setAttribute("NOTE_SESSION", notes);
        return "elemento inserito";
    }*/

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












