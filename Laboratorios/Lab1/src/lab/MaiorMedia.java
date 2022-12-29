package lab;

import java.util.Scanner;

public class MaiorMedia {
	public static void main(String[] args) {
		int soma = 0;
        String resultado = " ";
        Scanner entrada = new Scanner(System.in);
        String lista = entrada.nextLine();
        String[] array = lista.split("\\s+");
        for(int i = 0; i < array.length;i ++){
           soma  += Integer.parseInt(array[i]);
        } 
        float media = soma/array.length;
        for(int i = 0; i < array.length;i ++){
        	if (Integer.parseInt(array[i]) > media){
        		resultado += array[i] + " ";
        	}
        } 
       System.out.println(resultado.trim());
   }
}
