package com.franciscodantas.lunr.busca;

import java.util.Map;

import com.franciscodantas.lunr.documento.Documento;
import com.franciscodantas.lunr.documento.DocumentoService;

/**
 * Representa um conjunto de buscas suportadas pelo Lunr.
 * 
 * @author francisco antonio dantas
 *
 */
public interface Busca {
	
	/**
	 * Realiza uma busca com base na consulta ao DocumentoService passado.
	 * 
	 * Essa busca pode ser simples, procurando apenas termos dentro dos textos e retornando em quais
	 * documentos os termos foram encontrados ou de modo avançado onde se faz uma pesquisa com base em metadados
	 * passados.
	 * 
	 * @param ds DocumentoService a ser utilizado para busca.
	 * @return Mapa com os documentos encontrados e o fator de relevância de cada operação.
	 */
	public Map<Documento, Integer> busca(DocumentoService ds);
	
	/**
	 * Descreve uma consulta para explicar a consulta que foi feita.
	 * 
	 * @return Descrição da busca, onde cada linha representa um parâmetro de busca 
	 * e as colunas representam um detelhamento de cada parâmetro.
	 */
	public String[][] descreveConsulta();
}
