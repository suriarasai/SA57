package sg.edu.iss.reactordemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import sg.edu.iss.reactordemo.model.User;

@RestController
public class UsefulController {
	

	@GetMapping("/users")
    public Flux<User> flux() {
		User u1 = new User("GU XINRAN","Soft Spoken");
		User u2 = new User("MERYL","Invisible");
        // Returns a Flux that emits a sequence of strings
        return Flux.just(u1,u2);
    }
	
	

}
