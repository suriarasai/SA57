package sg.edu.iss.reactordemo.flux;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class FluxMapFunTest {

	@Test
	  public void map() {
	    Flux<Person> personFlux = Flux
	      .just("Mahathma Gandhi India", "Lee Kuan Yew", "Martin King Luther")
	      .map(n -> {
	        String[] split = n.split("\\s");
	        return new Person(split[0], split[1], split[2]);
	      });

	    StepVerifier.create(personFlux)
	        .expectNext(new Person("Mahathma", "Gandhi", "India"))
	        .expectNext(new Person("Lee", "Kuan", "Yew"))
	        .expectNext(new Person("Martin", "King", "Luther"))
	        .verifyComplete();
	  }

	 

	  @Data
	  @AllArgsConstructor
	  private static class Person {
	    private final String firstName;
	    private final String middleName;
	    private final String lastName;
	  }

}
