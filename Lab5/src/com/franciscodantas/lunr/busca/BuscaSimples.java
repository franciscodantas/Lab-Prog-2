package com.franciscodantas.lunr.busca;

import java.util.HashMap;
import java.util.Map;

import com.franciscodantas.lunr.documento.Documento;
import com.franciscodantas.lunr.documento.DocumentoService;

/**
 * BuscaSimples realiza uma operação de busca a partir de termos.
 * 
 * Dado os termos, deve se buscar por tais documentos e ordená-los de acordo com
 * a quantidade de termos que são atendidos pela busca.
 * 
 * Quanto mais termos da busca estão presentes, mais relevância tem o documento.
 * 
 * Não importa a quantidade de vezes que um termo aparece no documento, apenas
 * se o documento tem ou não o termo pelo menos uma vez.
 * 
 * Os documentos que não tem nenhum dos termos pesquisados, não devem ser
 * retornados.
 */
class BuscaSimples implements Busca {

	private String[] termos;

	/**
	 * Construtor padrão com os termos a serem encontrados.
	 * 
	 * Os termos não vazios são ignorados. Pelo menos 1 termo deve ser não vazio.
	 * 
	 * @param termos Termos a serem pesquisados.
	 */
	public BuscaSimples(String[] termos) {
		(new ValidadorBusca()).valida(termos);
		this.termos = termos;
	}
	
	@Override
	/**
	 * Realiza a busca a partir da consulta ao DocumentoService.
	 * 
	 * O DocumentoService realiza apenas operações simples de busca, mas sem
	 * ordenação ou tratamento da lógica de relevância.
	 * 
	 * @param ds DocumentoService a ser utilizado para busca.
	 * @return Mapa com os documentos encontrados e o fator de relevância de cada
	 *         operação.
	 */
	public Map<Documento, Integer> busca(DocumentoService ds) {
		Map<Documento, Integer> respostaDocumento = new HashMap<>();
		for (String termo : this.termos) {
			if (termo.isBlank()) {
				continue;
			}
			for (Documento d : ds.busca(termo)) {
				respostaDocumento.put(d, respostaDocumento.getOrDefault(d, 0) + 1);
			}
		}
		return respostaDocumento;
	}

	/**
	 * Descreve uma consulta para explicar a consulta que foi feita.
	 * 
	 * @return Descrição da busca, onde cada linha representa um parâmetro de busca
	 *         e as colunas representam um detelhamento de cada parâmetro.
	 */
	public String[][] descreveConsulta() {
		String[][] resultado = new String[this.termos.length][];
		for (int i = 0; i < resultado.length; i++) {
			resultado[i] = new String[] {"TERMO " + (i + 1), this.termos[i]};
		}
		return resultado;
	}
	
}
