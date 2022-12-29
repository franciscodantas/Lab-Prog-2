package lab;

import java.util.Scanner;

public class Wally {
	public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        while (true){
            String nomeNovo = "?";
            String[] listaNomes = entrada.nextLine().split("\\s+");
            if(listaNomes[0].equals("wally")){
                break;
            }
            for(int i = 0; i < listaNomes.length; i++){
                if(listaNomes[i].length() == 5){
                    nomeNovo = listaNomes[i];
                }
            }
            System.out.println(nomeNovo);
        }
        
    }
}
