package com.franciscodantas.apresentacao;

/**
 * Cria uma apresentação onde o texto passado será representado pela
 * suas 5 primeiras linhas
 * 
 * @author francisco antonio dantas
 *
 */
public class ApresentaNPrimeiras implements Apresentacao {
	
	@Override
	public String apresenta(String[] original) {
		String retorno = "Apresentação das primeiras 5 linhas:" + "\n";
		int nPrimeiras = 5;
		for(int i = 0; i < nPrimeiras; i++) {
			retorno += original[i] + "\n";
		}
		return retorno;
	}
}
