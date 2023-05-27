package me.bamboo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TacoOrder {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Date placedAt = new Date();
	
	@NotBlank(message = "目的地地址不得为空。")
	private String deliveryName;
	@NotBlank(message = "目的地街道不得为空。")
	private String deliveryStreet;
	@NotBlank(message = "目的地城市不得为空。")
	private String deliveryCity;
	@NotBlank(message = "目的地省份不得为空。")
	private String deliveryState;
	@NotBlank(message = "目的地邮编不得为空。")
	private String deliveryZip;
	
	@CreditCardNumber(message = "无效的信用卡卡号。")
	private String ccNumber;
	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([2-9][0-9])$", message = "无效的日期格式")
	private String ccExpiration;
	@Digits(integer = 3, fraction = 0, message = "无效的CVV格式。")
	private String ccCVV;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Taco> tacos = new ArrayList<>();
	
	public void addTaco(Taco taco) {
		this.tacos.add(taco);
	}

}
