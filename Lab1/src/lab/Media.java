package lab;

import java.util.Scanner;

public class Media {
	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		float primeiro = entrada.nextFloat();
		float segundo = entrada.nextFloat();
		double media = (primeiro + segundo)/2;
		
		if(media >= 7.0){
		    System.out.println("pass: True!");
		} 
		else {
		    System.out.println("pass: False!");
		}
	}
}
