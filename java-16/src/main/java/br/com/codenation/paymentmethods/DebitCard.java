package br.com.codenation.paymentmethods;

public class DebitCard implements PriceStrategy {
	public Double calculate(Double price) {
		return price * 0.95;
	}
}
