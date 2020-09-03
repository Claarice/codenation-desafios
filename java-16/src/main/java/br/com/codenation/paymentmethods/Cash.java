package br.com.codenation.paymentmethods;

public class Cash implements PriceStrategy {
	public Double calculate(Double price) {
		return price * 0.9;
	}
}
