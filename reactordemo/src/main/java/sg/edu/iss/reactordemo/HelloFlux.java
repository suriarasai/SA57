package sg.edu.iss.reactordemo;

import reactor.core.publisher.Flux;

public class HelloFlux {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Creating a Flux that emits a sequence of values
        Flux<String> flux = Flux.just("Hello", "Flux", "Jupiter");

        // Subscribing to the Flux to consume the emitted values
        flux.subscribe(System.out::println);
	}

}
