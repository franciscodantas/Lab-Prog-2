package com.franciscodantas.similaridade;

/**
 * Controller para operações de similaridade.
 * 
 * @author francisco antonio dantas
 */
public class SimilaridadeController {
	
	/**
	 * SimilaridadeService a ser utilizado.
	 */
	private SimilaridadeService similaridadeService;
	

	/**
	 * Construtor padrão, inicializa com o service a ser usado pelo controller.
	 * 
	 * @param similaridadeService SimilaridadeService a ser utilizado.
	 */
	public SimilaridadeController(SimilaridadeService similaridadeService) {
		this.similaridadeService = similaridadeService;
	}

	/**
	 * Operação de similaridade entre dois documentos. Para geração da similaridade
	 * serão considerados os termos de cada um dos documentos realizando um cálculo
	 * entre a quantiadde de termos em comum. A similaridade é um valor entre 0 e 1
	 * representando o percentual de semelhança.
	 * 
	 * @param docId1 Primeiro documento como base.
	 * @param docId2 Segundo documento como base.
	 * @return Valor entre 0 e 1 (inclusives) representando a similaridade entre os
	 *         documentos.
	 */
	public double similaridade(String docId1, String docId2) {
		return similaridadeService.similaridade(docId1, docId2);
	}

}
