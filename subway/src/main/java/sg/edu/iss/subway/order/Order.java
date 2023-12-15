package sg.edu.iss.subway.order;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	
	  @Id
	  private String id;
	  private Date placedAt = new Date();
  
	  //private User user;
	 
	  private String deliveryName;
	 
	  private String deliveryStreet;
	 
	  private String deliveryCity;
	 
	  private String deliveryState;
	 
	  private String deliveryZip;
	 
	  private String ccNumber;
	 
	  private String ccExpiration;
	 
	  private String ccCVV;

}
