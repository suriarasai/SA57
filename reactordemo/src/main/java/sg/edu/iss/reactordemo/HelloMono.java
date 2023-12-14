package sg.edu.iss.reactordemo;

import reactor.core.publisher.Mono;

public class HelloMono {
	
	public static void main(String[] args) {
        // Creating a Mono that emits a single value
        Mono<String> mono = Mono.just("Hello, Mono!");

        // Subscribing to the Mono to consume the emitted value
        mono.subscribe(System.out::println);
    }


}
