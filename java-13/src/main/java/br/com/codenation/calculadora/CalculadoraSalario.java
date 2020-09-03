package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		//Use o Math.round apenas no final do método para arredondar o valor final.
		//Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-
		if (salarioBase >= 1039) {
			double salario = calcularInss(salarioBase);

			return Math.round(calcularIRRF(salario));
		}

		return Math.round(0.0);
	}
	
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		if (salarioBase <= 1500) {
			return calcularPorcentagem(salarioBase, 8);
		} else if (salarioBase > 1500 && salarioBase <= 4000) {
			return calcularPorcentagem(salarioBase, 9);
		} else if (salarioBase > 4000) {
			return calcularPorcentagem(salarioBase, 11);
		}
 
		return 0.0;
	}

	private double calcularIRRF(double salario) {
		double descontoIRRF = 0.0;

		if (salario <= 3000) {
			return salario;
		} else if (salario > 3000 && salario <= 6000) {
			return calcularPorcentagem(salario, 7.5);
		} else if (salario > 6000) {
			return calcularPorcentagem(salario, 15);
		}

		return 0.0;
	}

	private double calcularPorcentagem(double salarioBase, double porcentagem) {
		return (1 - (porcentagem / 100)) * salarioBase;
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/