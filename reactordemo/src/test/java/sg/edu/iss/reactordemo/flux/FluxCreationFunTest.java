package sg.edu.iss.reactordemo.flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class FluxCreationFunTest {

	/**
	 * To create a flux from just() function
	 */
	@Test
	void testFluxJust() {
		 Flux<String> coffeeFlux = Flux
			        .just("Black", "Espresso", "Lathe", "Flat White", "Cappuccino", "Mocha");
			    StepVerifier.create(coffeeFlux)
			        .expectNext("Black")
			        .expectNext("Espresso")
			        .expectNext("Lathe")
			        .expectNext("Flat White")
			        .expectNext("Cappuccino")
			        .expectNext("Mocha")
			        .verifyComplete();
	}
	
	@Test
	void testFluxFromArray() {
		
		 String[] coffee = new String[] {
				 "Black", "Espresso", "Lathe", "Flat White", "Cappuccino", "Mocha" };

			    Flux<String> coffeeFlux = Flux.fromArray(coffee);

			    StepVerifier.create(coffeeFlux)
		        .expectNext("Black")
		        .expectNext("Espresso")
		        .expectNext("Lathe")
		        .expectNext("Flat White")
		        .expectNext("Cappuccino")
		        .expectNext("Mocha")
			        .verifyComplete();
		
	}
	
	@Test
	void testFluxFromIterable() {
		
		List<String> coffeeList = new ArrayList<>();
		coffeeList.add("Black");
		coffeeList.add("Espresso");
		coffeeList.add("Lathe");
		coffeeList.add("Flat White");
		coffeeList.add("Cappuccino");
		coffeeList.add("Mocha");
		
		Flux<String> coffeeFlux = Flux.fromIterable(coffeeList);
		
	    StepVerifier.create(coffeeFlux)
        .expectNext("Black")
        .expectNext("Espresso")
        .expectNext("Lathe")
        .expectNext("Flat White")
        .expectNext("Cappuccino")
        .expectNext("Mocha")
	        .verifyComplete();
	}
	
	@Test
	void testFluxFromStream() {
		
		 Stream<String> coffee = Stream.of("Black", "Espresso", "Lathe", "Flat White", "Cappuccino", "Mocha");

			    Flux<String> coffeeFlux = Flux.fromStream(coffee);
		
	    StepVerifier.create(coffeeFlux)
        .expectNext("Black")
        .expectNext("Espresso")
        .expectNext("Lathe")
        .expectNext("Flat White")
        .expectNext("Cappuccino")
        .expectNext("Mocha")
	        .verifyComplete();
		
	}

	@Test
	void testFluxFromIntreval() {
	    Flux<Long> intervalFlux =
	            Flux.interval(Duration.ofSeconds(1))
	                .take(5);

	        StepVerifier.create(intervalFlux)
	           .expectNext(0L)
	            .expectNext(1L)
	            .expectNext(2L)
	            .expectNext(3L)
	            .expectNext(4L)
	            .verifyComplete();
		
	}
	
	@Test
	void testFluxFromRange() {
	    Flux<Integer> intervalFlux =
	            Flux.range(1, 5);

	        StepVerifier.create(intervalFlux)
	            .expectNext(1)
	            .expectNext(2)
	            .expectNext(3)
	            .expectNext(4)
	            .expectNext(5)
	            .verifyComplete();
		
	}
	
}
