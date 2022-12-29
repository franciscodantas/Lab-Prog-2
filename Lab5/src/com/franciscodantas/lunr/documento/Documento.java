package com.franciscodantas.lunr.documento;

import java.util.Map;

/**
 * Documento representa um conjunto textual que será indexado pelo Lunr.
 */
public interface Documento {

	/**
	 * Retorna a quantidade de texto útil, sendo definido como a quantidade de
	 * caracteres úteis (sem caracteres estranhos e sem espaços) sobre o total de
	 * caracteres original (incluindo espaços).
	 * 
	 * @return Percentual de texto útil (entre 0 e 1, inclusives).
	 */
	double metricaTextoUtil();

	/**
	 * Retorna a identificação do documento. A documentação é definida pelo próprio
	 * documento e é uma String sem formatação específica. Todo documento gerado
	 * pelo Lunr começa com o símbolo "_". O identificador não pode ser vazio ou
	 * nulo.
	 * 
	 * @return Identificação do documento.
	 * @throws NullPointerException Caso o ID seja nulo.
	 */
	String getId();

	/**
	 * Retorna os termos do texto em formato de String. Não podem existir termos
	 * vazios (só de espaços, tabulações ou string vazia).
	 * 
	 * @return Array de termos extraídos do documento.
	 */
	String[] getTexto();
	
	/**
	 * Retorna o texto original que foi passado, sem nenhum tipo de tratamento (possue \n e espaços em branco).
	 * 
	 * @return String que representa o texto armazenado no documento.
	 */
	String getOriginal();

	/**
	 * Retorna metadados referente ao documento. Detalhes como seu tipo, autor, ou
	 * características próprias do documento.
	 * 
	 * Idealmente, todo metadado terá como características:
	 * 
	 * LINHAS, TAMANHO (número de caracteres), METADATADATE (hora atual do sistema
	 * na criação dos metadados, em ms), TIPO.
	 * 
	 * @return Mapa com os metadados descrito em forma textual.
	 */
	Map<String, String> getMetadados();

}
