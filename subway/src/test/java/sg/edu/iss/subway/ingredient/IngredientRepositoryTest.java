package sg.edu.iss.subway.ingredient;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import sg.edu.iss.subway.ingredient.Ingredient.Type;

@DataMongoTest
class IngredientRepositoryTest {

	@Autowired
	IngredientRepository ingredientRepo;

    @BeforeEach
    void setup() {
		Flux<Ingredient> deleteAndInsert = ingredientRepo.deleteAll()
				.thenMany(ingredientRepo.saveAll(Flux.just(new Ingredient(1L, "Multi Grain", Type.BREAD),
						new Ingredient(2L, "Ground Beef", Type.PROTEIN),
						new Ingredient(3L, "Cheddar Cheese", Type.DAIRY))));
		StepVerifier.create(deleteAndInsert).expectNextCount(3).verifyComplete();
	}

    @Test
    void shouldSaveAndFetchIngredients() {
	      
	      StepVerifier.create(ingredientRepo.findAll())
	          .recordWith(ArrayList::new)
	          .thenConsumeWhile(x -> true)
	          .consumeRecordedWith(ingredients -> {
	            assertThat(ingredients).hasSize(3);
	            assertThat(ingredients).contains(
	                new Ingredient(1L, "Multi Grain", Type.BREAD));
	            assertThat(ingredients).contains(
	                new Ingredient(2L, "Ground Beef", Type.PROTEIN));
	            assertThat(ingredients).contains(
	                new Ingredient(3L, "Cheddar Cheese", Type.DAIRY));
	          })
	          .verifyComplete();
	      
	      StepVerifier.create(ingredientRepo.findById(1L))
	          .assertNext(ingredient -> {
	              ingredient.equals(new Ingredient(1L, "Multi Grain", Type.BREAD));
	          });
	}
}
