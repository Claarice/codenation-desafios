package br.com.codenation.paymentmethods;

public class CreditCard implements PriceStrategy {
	public Double calculate (Double price) {
		return price * 0.98;
	}
}
