package sg.edu.iss.subway.sandwich;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.iss.subway.ingredient.Ingredient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Sandwich {
	@Id
	private Long id;
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;
	@Size(min = 1, message = "I need atleast one ingredient to make a sandwich")
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	

}
