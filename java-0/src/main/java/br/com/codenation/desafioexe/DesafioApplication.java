package br.com.codenation.desafioexe;

import java.util.*;

public class DesafioApplication {
	
	public static List<Integer> fibonacci() {
		List<Integer> fib = new ArrayList<Integer>();
		fib.add(0);
		fib.add(1);
		
		for (int i = 1; i < fib.size(); i++) {
			int j = fib.get(i) + fib.get(i-1);
			if (fib.size() < 15) 
				fib.add(j);
		}

		return fib;
	}	

	public static Boolean isFibonacci(Integer a) {
		List<Integer> fib = fibonacci();
		
		return fib.contains(a);
	}

}