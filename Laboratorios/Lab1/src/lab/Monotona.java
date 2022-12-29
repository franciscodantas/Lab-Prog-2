package lab;

import java.util.Scanner;

public class Monotona {
	public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int numero1 = entrada.nextInt();
        int numero2 = entrada.nextInt();
        int numero3 = entrada.nextInt();
        int numero4 = entrada.nextInt();
        if(numero1 > numero2 && numero2 > numero3 && numero3 > numero4){
            System.out.println("POSSIVELMENTE ESTRITAMENTE DECRESCENTE");
        }else{
            if(numero1 < numero2 && numero2 < numero3 && numero3 < numero4){
                System.out.println("POSSIVELMENTE ESTRITAMENTE CRESCENTE");
            }else{
                System.out.println("FUNCAO NAO ESTRITAMENTE CRES/DECR");
            }
        }
    }
}
