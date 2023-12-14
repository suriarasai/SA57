package sg.edu.iss.reactordemo.flux;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class FluxSelectionFunTest {

	
  @Test
  public void skipAFew() {
    Flux<String> countFlux = Flux.just(
        "one", "two", "skip a few", "nine", "ten")
        .skip(3);

    StepVerifier.create(countFlux)
        .expectNext("nine", "ten")
        .verifyComplete();
  }

  @Test
  public void skipAFewSeconds() {
    Flux<String> countFlux = Flux.just(
        "one", "two", "skip a few", "nine", "ten")
        .delayElements(Duration.ofSeconds(1))
        .skip(Duration.ofSeconds(4));

    StepVerifier.create(countFlux)
    	.expectNext("nine", "ten")
    	.verifyComplete();
  }

  @Test
  public void take() {
    Flux<String> nationalParkFlux = Flux.just(
        "Yellowstone", "Yosemite", "Grand Canyon", "Zion", "Acadia")
        .take(3);

    StepVerifier.create(nationalParkFlux)
        .expectNext("Yellowstone", "Yosemite", "Grand Canyon")
        .verifyComplete();
  }

  @Test
  public void takeForAwhile() {
    Flux<String> nationalParkFlux = Flux.just(
        "Yellowstone", "Yosemite", "Grand Canyon", "Zion", "Grand Teton")
        .delayElements(Duration.ofSeconds(1))
        .take(Duration.ofMillis(3500));

    StepVerifier.create(nationalParkFlux)
        .expectNext("Yellowstone", "Yosemite", "Grand Canyon")
        .verifyComplete();
  }

  @Test
  public void filter() {
    Flux<String> nationalParkFlux = Flux.just(
        "Yellowstone", "Yosemite", "Grand Canyon", "Zion", "Grand Teton")
        .filter(np -> !np.contains(" "));

    StepVerifier.create(nationalParkFlux)
        .expectNext("Yellowstone", "Yosemite", "Zion")
        .verifyComplete();
  }

  @Test
  public void distinct() {
    Flux<String> animalFlux = Flux.just(
        "koala", "kangaroo", "posum", "wombats", "platypus", "anteater", "kangaroo", "posum", "wombats")
        .distinct();

    StepVerifier.create(animalFlux)
        .expectNext("koala", "kangaroo", "posum", "wombats", "platypus", "anteater")
        .verifyComplete();
  }

}
