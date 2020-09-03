package challenge;

import java.util.*;

public class CriptografiaCesariana implements Criptografia {

    public static ArrayList<Character> alfabeto = new ArrayList<Character>();
    public static int n = 3;
    public static int index = 0;

    public CriptografiaCesariana() {
        for(char i='a'; i<='z';i++) {
            alfabeto.add(i);
        }

    }

    @Override   
    public String criptografar(String texto) {
        if (texto == "") {
            throw new IllegalArgumentException();
        } else if (texto == null) {
            throw new NullPointerException();

        }

        texto = texto.toLowerCase();
        String criptografado = "";

        for (int i = 0; i < texto.length(); i++) {
            index = alfabeto.indexOf(texto.charAt(i)) + 3;
            
            if (index > 25) {
                index = index - 25;
            }

            if (alfabeto.indexOf(texto.charAt(i)) == -1) {
                criptografado += texto.charAt(i);
            } else {
                criptografado += alfabeto.get(index).toString();
            }

        }

        return criptografado;
    }

    @Override
    public String descriptografar(String texto) {
        if (texto == "") {
            throw new IllegalArgumentException();
        } else if (texto == null) {
            throw new NullPointerException();

        }

        texto = texto.toLowerCase();
        String descriptografado = "";

        for (int i = 0; i < texto.length(); i++) {
            index = alfabeto.indexOf(texto.charAt(i)) - 3;

            if (index <= 0) {
                index = 26 + index;
            }

            if (alfabeto.indexOf(texto.charAt(i)) == -1) {
                descriptografado += texto.charAt(i);
            } else {
                descriptografado += alfabeto.get(index);
            }
        }
        
        return descriptografado;
    }
}
