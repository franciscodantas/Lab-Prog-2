package com.franciscodantas.apresentacao;

/**
 * Cria uma apresentação onde o texto passado será representado pela
 * suas 5 primeiras linhas.
 * 
 * @author francisco antonio dantas
 *
 */
public class ApresentaNUltimas implements Apresentacao {

	@Override
	public String apresenta(String[] original) {
		String retorno = "Apresentação das ultimas 5 linhas:" + "\n";
		for(int i = original.length - 1; i >= original.length - 5; i--){
		    retorno += original[i] + "\n";
		}
		return retorno;
	}

}
