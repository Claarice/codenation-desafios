package com.challenge.desafio;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.stream.Stream;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

public class CalculadorDeClasses implements Calculavel {
	
	@Override
	public BigDecimal somar(Object object) {
		return Stream.of(object.getClass().getDeclaredFields())
				.filter(field -> field.isAnnotationPresent(Somar.class))
				.map(field -> getValueOfField(field, object))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public BigDecimal subtrair(Object object) {
		return Stream.of(object.getClass().getDeclaredFields())
				.filter(field -> field.isAnnotationPresent(Subtrair.class))
				.map(field -> getValueOfField(field, object))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public BigDecimal totalizar(Object object) {
		return somar(object).subtract(subtrair(object));
	}
	
	private BigDecimal getValueOfField(Field field, Object o) {
		try {
			field.setAccessible(true);
			return (BigDecimal) field.get(o);
		} catch (IllegalAccessException e) {
			return BigDecimal.ZERO;
		}
	}
}
