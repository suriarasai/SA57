package sg.edu.iss.reactordemo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {
	
    @GetMapping("/mono")
    public Mono<String> mono() {
        // Returns a Mono that emits "Hello, Mono!"
        return Mono.just("Hello, Mono!");
    }

    @GetMapping("/flux")
    public Flux<String> flux() {
        // Returns a Flux that emits a sequence of strings
        return Flux.just("Hello", "Flux", "World");
    }


}
