package sg.edu.iss.subway.ingredient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Ingredient {
	  @Id
	  private Long id;
	  private String name;
	  private Type type;
	 
	  public enum Type {
	    BREAD, PROTEIN, DAIRY, VEGGIES, SAUCE, SOUP, WRAP
	  }

}
