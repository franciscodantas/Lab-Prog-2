package com.franciscodantas.lunr.busca;

import java.util.Map;

import com.franciscodantas.lunr.documento.DocumentoDTO;

/**
 * Controller para operações de busca. A busca representa uma entidade básica do
 * sistema mais complexa do que apenas um casamento de padrões de termos de
 * busca.
 * 
 * Quando a busca envolve alguma lógica mais complexa, cabe as entidades de
 * busca realizar tais operações.
 */
public class BuscaController {

	private BuscaService bs;
	private ValidadorBusca bv;

	/**
	 * Construtor padrão do BuscaController.
	 * 
	 * É importante manter um único controllador de busca por sistema.
	 * 
	 * @param bs BuscaService a ser utilizado pelo controller.
	 */
	public BuscaController(BuscaService bs) {
		this.bs = bs;
		this.bv = new ValidadorBusca();
	}

	/**
	 * Busca documentos de acordo com os termos.
	 * 
	 * Os documentos retornados devem ter pelo menos 1 dos termos, mas não todos. Os
	 * documentos são ordenados de acordo com os que tiverem mais termos presentes
	 * até os que tiverem menos termos.
	 * 
	 * @param termos Termos a serem procurado. Pelo menos 1 dos termos deve estar
	 *               presente nos documentos retornados.
	 * @return Array com DocumentoDTO com os resultados da busca, dos documentos que
	 *         mais tem mais termos, até os que tem menos termos.
	 */
	public DocumentoDTO[] busca(String[] termos) {
		this.bv.valida(termos);
		return this.bs.busca(new BuscaSimples(termos));
	}

	/**
	 * Busca documentos a partir dos metadados existentes.
	 * 
	 * Os documentos retornados precisam ter todos os metadados listados como
	 * parâmetro.
	 * 
	 * @param metadados Metadados que devem estar todos presentes nos documentos
	 *                  retornados.
	 * @return Array com DocumentoDTO. Não há ordenação definida.
	 */
	public DocumentoDTO[] busca(Map<String, String> metadados) {
		this.bv.valida(metadados.values());
		return this.bs.busca(new BuscaAvancada(metadados));
	}

	/**
	 * Retorna o histórico de buscas do usuário. A primeira busca tem número 0, e
	 * assim segue o histórico de busca sucessivamente. Na depuração, cada linha
	 * representa um parâmetro de busca e cada coluna o detalhamento desse
	 * parâmetro.
	 * 
	 * @param numero Número da busca a ser retornado seu histórico.
	 * @return Depuração da busca.
	 */
	public String[][] recuperaHistoricoDepuracao(int numero) {
		this.bv.valida(numero);
		return this.bs.recuperaHistorico(numero).debug();
	}

	/**
	 * Retorna o histórico de buscas do usuário. A primeira busca tem número 0, e
	 * assim segue o histórico de busca sucessivamente.
	 * 
	 * @param numero Número da busca a ser retornado seu histórico.
	 * @return Ids dos documentos de resultado.
	 */
	public String[] recuperaHistoricoIds(int numero) {
		this.bv.valida(numero);
		return this.bs.recuperaHistorico(numero).ids();
	}
	
}
