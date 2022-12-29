package com.franciscodantas.apresentacao;

/**
 * Cria uma apresentação onde o texto passado será convertido todo
 * em caixa alta.
 * 
 * @author francisco antonio dantas
 *
 */
public class ApresentaCaps implements Apresentacao {

	@Override
	public String apresenta(String[] original) {
		String retorno = "Apresentação em caixa alta:" + "\n";
		for(int i = 0; i < original.length; i++) {
			retorno += original[i].toUpperCase() + "\n";
		}
		return retorno;
	}

}
