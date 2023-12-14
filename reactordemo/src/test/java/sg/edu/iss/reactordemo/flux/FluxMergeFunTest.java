package sg.edu.iss.reactordemo.flux;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

class FluxMergeFunTest {

	
	 @Test
	  public void mergeFluxes() {
	    // delays needed to avoid the first flux from streaming the
	    // data through before subscribing to the second flux.

	    Flux<String> characterFlux = Flux
	        .just("", "", "")
	        .delayElements(Duration.ofMillis(500));
	    Flux<String> foodFlux = Flux
	        .just("", "", "")
	        .delaySubscription(Duration.ofMillis(250))
	        .delayElements(Duration.ofMillis(500));

	    Flux<String> mergedFlux = characterFlux.mergeWith(foodFlux);

	    StepVerifier.create(mergedFlux)
	        .expectNext("")
	        .expectNext("")
	        .expectNext("")
	        .expectNext("")
	        .expectNext("")
	        .expectNext("")
	        .verifyComplete();
	  }

	  @Test
	  public void zipFluxes() {
	    Flux<String> characterFlux = Flux
	        .just("", "", "");
	    Flux<String> foodFlux = Flux
	        .just("", "", "");

	    Flux<Tuple2<String, String>> zippedFlux =
	        Flux.zip(characterFlux, foodFlux);

	    StepVerifier.create(zippedFlux)
	          .expectNextMatches(p ->
	              p.getT1().equals("") &&
	              p.getT2().equals(""))
	          .expectNextMatches(p ->
	              p.getT1().equals("") &&
	              p.getT2().equals(""))
	          .expectNextMatches(p ->
	              p.getT1().equals("") &&
	              p.getT2().equals(""))
	          .verifyComplete();
	  }

	  @Test
	  public void zipFluxesToObject() {
	    Flux<String> characterFlux = Flux
	        .just("", "", "");
	    Flux<String> foodFlux = Flux
	        .just("", "", "");

	    Flux<String> zippedFlux =
	        Flux.zip(characterFlux, foodFlux, (c, f) -> c + " eats " + f);

	    StepVerifier.create(zippedFlux)
	          .expectNext(" eats ")
	          .expectNext(" eats ")
	          .expectNext(" eats ")
	          .verifyComplete();
	  }

	  @Test
	  public void firstWithSignalFlux() {
	    // delay needed to "slow down" the slow Flux

	    Flux<String> slowFlux = Flux.just("tortoise", "snail", "sloth")
	          .delaySubscription(Duration.ofMillis(100));
	    Flux<String> fastFlux = Flux.just("hare", "cheetah", "squirrel");

	    Flux<String> firstFlux = Flux.firstWithSignal(slowFlux, fastFlux);

	    StepVerifier.create(firstFlux)
	        .expectNext("hare")
	        .expectNext("cheetah")
	        .expectNext("squirrel")
	        .verifyComplete();
	  }

}
