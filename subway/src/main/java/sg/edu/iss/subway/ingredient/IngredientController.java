package sg.edu.iss.subway.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/i100")
public class IngredientController {

	@Autowired
	private IngredientRepository repository;

	public IngredientController(IngredientRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public Flux<Ingredient> getAllIngredients() {
		return repository.findAll();
	}

	@GetMapping("{id}")
	public Mono<Ingredient> getIngredient(@PathVariable String id) {
		return repository.findById(new Long(id));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Ingredient> saveIngredient(@RequestBody Ingredient ingredient) {
		return repository.save(ingredient);
	}

	@PutMapping("{id}")
		    public Mono<ResponseEntity<Ingredient>> updateIngredient(@PathVariable(value = "id") String id,
		                                                       @RequestBody Ingredient ingredient) {
		        return repository.save(ingredient)
       				              .map(updateIngredient -> new ResponseEntity<>(updateIngredient, HttpStatus.OK))
		                         .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		    }

	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteEmployee(@PathVariable(value = "id") String id) {
		Long lid = new Long(id);
		//repository.deleteById(repository.findById(lid));
		return null; 
	}

	@DeleteMapping
	public Mono<Void> deleteAllEmployees() {
		return repository.deleteAll();
	}
}


