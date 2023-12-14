package sg.edu.iss.reactordemo.flux;

import org.junit.jupiter.api.Test;

import lombok.Data;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class FluxMapFunTest {

	@Test
	  public void map() {
	    Flux<Person> personFlux = Flux
	      .just("Mahathma Gandhi", "Lee KuanYew", "Martin KingLuther")
	      .map(n -> {
	        String[] split = n.split("\\s");
	        return new Person(split[0], split[1]);
	      });

	    StepVerifier.create(personFlux)
	        .expectNext(new Person("Mahathma", "Gandhi"))
	        .expectNext(new Person("Lee", "KuanYew"))
	        .expectNext(new Person("Martin", "KingLuther"))
	        .verifyComplete();
	  }

	 

	  @Data
	  private static class Person {
	    private final String firstName;
	    private final String lastName;
	  }

}
