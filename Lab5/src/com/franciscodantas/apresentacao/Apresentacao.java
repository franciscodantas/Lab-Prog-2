package com.franciscodantas.apresentacao;

/**
 * Representa um conjunto de apresentações suportadas pelo sistema.
 * 
 * @author francisco antonio dantas
 *
 */
public interface Apresentacao {
	
	/**
	 * Realiza a apresentação de um documento solicitado, retornando uam
	 * String formatada de acordo com o que foi pedido(5 primeiras linhas,
	 * 5 ultimas linhas ou em caixa alta).
	 * 
	 * @param original O texto Original que será apresentado.
	 * @return Uma String com a apresentação do documento.
	 */
	public String apresenta(String[] original);
}
