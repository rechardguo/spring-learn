package rechard.learn.springbatch.bean;

import rechard.learn.springbatch.bean.validation.constraints.CardNumberValidator;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class User {

	@NotNull
	private String name;
	@Min(1)
	private int id;

	@NotNull
    @CardNumberValidator(prefix="abc")
	private String cardNumber;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
