package lab;

import java.util.Scanner;

public class Alunos {
	public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int maior = Integer.MIN_VALUE;
        int minimo = Integer.MAX_VALUE;
        int acima = 0;
        int abaixo = 0;
        int soma = 0;
        double media = 0;
        String[] alunos;
        String[] notas = new String[1];

        while (true) {
            alunos = entrada.nextLine().split("\\s+");
            if (alunos[0].equals("-")) {
                break;
            }
            if (notas[0] == null) {
                notas[0] = alunos[1];
            } else {
                String[] novoArray = new String[notas.length + 1];
                int i;
                for (i = 0; i < notas.length; i++) {
                    novoArray[i] = notas[i];
                } 
                novoArray[novoArray.length - 1] = alunos[1];
                notas = novoArray;
            }
        }

        for (int i = 0; i < notas.length; i++) {
            soma += Integer.parseInt(notas[i]);
        }

        media = soma/notas.length;

        for (int i = 0; i < notas.length; i++) {
            if (Integer.parseInt(notas[i]) > maior) {
                maior = Integer.parseInt(notas[i]);
            }
            if (Integer.parseInt(notas[i]) < minimo) {
                minimo = Integer.parseInt(notas[i]);
            }
            if (Integer.parseInt(notas[i]) >= 700) {
                acima += 1;
            }else{
                abaixo += 1;
            }
        }

        System.out.println("maior: " + maior);
        System.out.println("menor: " + minimo);
        System.out.println("media: " + (int) media);
        System.out.println("acima: " + acima);
        System.out.println("abaixo: " + abaixo);

    }
}
