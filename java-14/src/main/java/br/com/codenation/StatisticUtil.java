package br.com.codenation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticUtil {

	public static int average(int[] elements) {
		int sum = 0;
		for (int i = 0; i < elements.length; i++) {
			sum += elements[i];
		}
		
		return sum / elements.length;
	}

	public static int mode(int[] elements) {
		Arrays.sort(elements);
		int current = elements[0];
		int max = 0;
		int sum = 0;
		int moda = 0;
		for (int i = 0; i < elements.length; i++) {
			if (current  == elements[i]) {
				sum++;
			} else {
				if (sum > max) {
					max = sum;
				    moda = elements[i-1];
				}
				current = elements[i];
		        System.out.println(sum+" "+current);
			    sum = 1;
			}
		}
	
		if (sum > max) {
			max = sum;
		    moda = elements[elements.length-1];
		}
		
		return moda;
	}

	public static int median(int[] elements) {
		Arrays.sort(elements);
		int median = 0;
		int middle = elements.length / 2;
		if (elements.length % 2 == 0)
			median = (elements[middle] + elements[middle-1]) / 2;
		else 
			median = elements[middle];
		return median;
	}
}