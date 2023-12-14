package sg.edu.iss.reactordemo.flux;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

class FluxTestSomethingUseful {


	  @Test
	  public void logSimple() {
	    Flux<String> dressColors = Flux.just(
	        "white", "yellow", "orange", "green", "purple", "blue")
	      .log();
	    dressColors.subscribe();
	  }
	  
	  @Test
	  public void logMapping() {
	    Flux<String> dressColors = Flux.just(
	        "white", "yellow", "orange", "green", "purple", "blue")
	      .map(cb -> cb.toUpperCase())
	      .log()
	      ;
	    dressColors.subscribe(); 
	  }
	  
	  @Test
	  public void logFlatMapping() throws Exception {
	    Flux<String> dressColors = Flux.just(
	        "white", "yellow", "orange", "green", "purple", "blue")
	      .flatMap(cb -> Mono.just(cb)
	          .map(c -> c.toUpperCase())
	          .log()
	          .subscribeOn(Schedulers.parallel())
	      )
	      ;
	    dressColors.subscribe(); 
	    
	    Thread.sleep(3000L);
	  }
}
