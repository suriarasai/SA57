package sg.edu.iss.reactordemo.flux;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class FluxAllAndAnyFunTest {

	
	@Test
	  public void all() {
	    Flux<String> animalFlux = Flux.just(
	        "platypus", "elephant", "koala", "eagle", "kangaroo");

	    Mono<Boolean> hasAMono = animalFlux.all(a -> a.contains("a"));
	    StepVerifier.create(hasAMono)
	      .expectNext(true)
	      .verifyComplete();

	    Mono<Boolean> hasKMono = animalFlux.all(a -> a.contains("k"));
	    StepVerifier.create(hasKMono)
	      .expectNext(false)
	      .verifyComplete();
	  }

	  @Test
	  public void any() {
	    Flux<String> animalFlux = Flux.just(
	        "platypus", "elephant", "koala", "eagle", "kangaroo");

	    Mono<Boolean> hasAMono = animalFlux.any(a -> a.contains("a"));

	    StepVerifier.create(hasAMono)
	      .expectNext(true)
	      .verifyComplete();

	    Mono<Boolean> hasZMono = animalFlux.any(a -> a.contains("z"));
	    StepVerifier.create(hasZMono)
	      .expectNext(false)
	      .verifyComplete();
	  }
}
