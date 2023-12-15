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

	    Flux<String> studentFlux = Flux
	        .just("Zou", "Syed", "Priya")
	        .delayElements(Duration.ofMillis(500));
	    Flux<String> foodFlux = Flux
	        .just("Fish Ball Soup", "Nasi Lemak", "Bhatura")
	        .delaySubscription(Duration.ofMillis(250))
	        .delayElements(Duration.ofMillis(500));

	    Flux<String> mergedFlux = studentFlux.mergeWith(foodFlux);

	    StepVerifier.create(mergedFlux)
	        .expectNext("Zou")
	        .expectNext("Fish Ball Soup")
	        .expectNext("Syed")
	        .expectNext("Nasi Lemak")
	        .expectNext("Priya")
	        .expectNext("Bhatura")
	        .verifyComplete();
	  }
      // Catresian 
	  // CSV Col, Col others mix?
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
	        .just("A", "B", "C");
	    Flux<String> foodFlux = Flux
	        .just("Soup", "Toast", "Salad");

	    Flux<String> zippedFlux =
	        Flux.zip(characterFlux, foodFlux, (c, f) -> c + " eats " + f);

	    StepVerifier.create(zippedFlux)
	          .expectNext("A eats Soup")
	          .expectNext("B eats Toast")
	          .expectNext("B eats Salad")
	          .verifyComplete();
	  }

	  @Test
	  public void firstWithSignalFlux() {
	    // delay needed to "slow down" the slow Flux

	    Flux<String> slowFlux = Flux.just("tortoise", "snail", "sloth") // MySQL
	          .delaySubscription(Duration.ofMillis(100));
	    Flux<String> fastFlux = Flux.just("hare", "cheetah", "squirrel"); // Mongo

	    Flux<String> firstFlux = Flux.firstWithSignal(slowFlux, fastFlux);

	    StepVerifier.create(firstFlux)
	        .expectNext("hare")
	        .expectNext("cheetah")
	        .expectNext("squirrel")
	        
	        .verifyComplete();
	  }

}
