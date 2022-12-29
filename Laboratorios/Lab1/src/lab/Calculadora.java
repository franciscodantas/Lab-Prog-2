package lab;

import java.util.Scanner;

public class Calculadora {
	 public static void main(String[] args) {
	        Scanner entrada = new Scanner(System.in);
	        String operacao = entrada.next();

	        if(operacao.equals("+")){
	            float parcela1 = entrada.nextFloat();
	            float parcela2 = entrada.nextFloat();
	            float soma = parcela1 + parcela2;
	            System.out.println("RESULTADO: " + soma);
	        }else{
	            if(operacao.equals("-")){
	                float minuendo = entrada.nextFloat();
	                float subtraendo = entrada.nextFloat();
	                float subtracao = minuendo - subtraendo;
	                System.out.println("RESULTADO: " + subtracao);
	            }else{
	                if(operacao.equals("*")){
	                    float fator1 = entrada.nextFloat();
	                    float fator2 = entrada.nextFloat();
	                    float produto = fator1 * fator2;
	                    System.out.println("RESULTADO: " + produto);
	                }else{
	                    if(operacao.equals("/")){
	                        float dividendo = entrada.nextFloat();
	                        float divisor = entrada.nextFloat();
	                        if(divisor != 0.0){
	                            float quociente = dividendo/divisor;
	                            System.out.println("RESULTADO: " + quociente);
	                        }else{
	                            System.out.println("ERRO");
	                        }
	                    }else{
	                        System.out.println("ENTRADA INVALIDA");
	                    }
	                }
	            }
	        }
	    }
}
