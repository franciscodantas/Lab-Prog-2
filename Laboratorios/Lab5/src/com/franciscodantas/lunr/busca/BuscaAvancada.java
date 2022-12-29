package com.franciscodantas.lunr.busca;

import java.util.HashMap;
import java.util.Map;
import com.franciscodantas.lunr.documento.Documento;
import com.franciscodantas.lunr.documento.DocumentoService;

/**
 * A BuscaAvancada é uma forma de busca em que se utiliza dos metadados para
 * se fazer a procura de documentos que tenham todos os metadados pedidos.
 * 
 * @author francisco antonio dantas
 *
 */
public class BuscaAvancada implements Busca {
	
	/**
	 * metadados que serão usados para a pesquisa nos documentos
	 */
	private Map<String, String> metadados;
	
	/**
	 * Criador padrão da BuscaAvançada, cria os metadados que serão analizados
	 * durante as buscas.
	 * 
	 * @param metadados Metadados a serem pesquisados.
	 */
	public BuscaAvancada(Map<String, String> metadados) {
		this.metadados = metadados;
	}

	/**
	 * Realiza a busca a partir da consulta ao DocumentoService.
	 * 
	 * O DocumentoService realiza operações de busca utilizando os metadados
	 * dos documentos, mas sem ordenação.
	 * 
	 * @param ds DocumentoService a ser utilizado para busca.
	 * @return Mapa com os documentos encontrados e o numero referente aquele documento.
	 */
	@Override
	public Map<Documento, Integer> busca(DocumentoService ds) {
		Map<Documento, Integer> respostaDocumento = new HashMap<>();
		respostaDocumento = ds.buscaAvancada(this.metadados);
		return respostaDocumento;
	}
	
	@Override
	/**
	 * Descreve uma consulta para explicar a consulta que foi feita.
	 * 
	 * @return Descrição da busca, onde cada linha representa um parâmetro de busca 
	 * e as colunas representam um detelhamento de cada parâmetro.
	 */
	public String[][] descreveConsulta() {
		String[] values = (String[]) this.metadados.keySet().toArray();
		String[][] resultado = new String[this.metadados.size()][this.metadados.size()];
		for (int i = 0; i < resultado.length; i++) {
			resultado[i] = new String[] {"TERMO " + values[i], "VALOR " + this.metadados.get(values[i])};
		}
		return resultado;
	}

}
