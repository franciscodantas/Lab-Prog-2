package com.franciscodantas.lunr.busca;

import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.franciscodantas.lunr.documento.Documento;
import com.franciscodantas.lunr.documento.DocumentoDTO;
import com.franciscodantas.lunr.documento.DocumentoService;

/**
 * BuscaService é responsável por centralizar todas as operações de lógica de
 * busca.
 * 
 * O BuscaService tem como atribuições ordenar e limitar as buscas realizadas,
 * bem como cadastrá-las no histórico de buscas.
 * 
 * A busca em si é realizada por uma entidade recebida que deve retornar um mapa
 * de documentos que atende tais buscas e o valor de relevância associado a cada
 * tipo de busca, onde o valor mais alto representa uma maior relevância.
 * 
 * @author francisco antonio dantas
 */
public class BuscaService {

	private DocumentoService ds;
	private BuscaRepository br;

	/**
	 * Construtor padrão, recebendo o DocumentoService para realizar as operações de
	 * documento, bem como preparar o repositório de buscas.
	 * 
	 * @param ds DocumentoService a ser utilizado pelo BuscaService.
	 */
	public BuscaService(DocumentoService ds) {
		this.ds = ds;
		this.br = new BuscaRepository();
	}

	/**
	 * Realiza uma operação de busca simples. Toda busca deve retornar um documento
	 * e grau de relevância (de maior para menor).
	 * 
	 * @param buscaSimples Busca a ser realizada.
	 * @return Resultado das buscas.
	 */
	public DocumentoDTO[] busca(BuscaSimples buscaSimples) {
		Map<Documento, Integer> respostaDocumento = buscaSimples.busca(this.ds);
		DocumentoDTO[] documentos = ordena(respostaDocumento);
		this.br.adicionaBusca(buscaSimples, documentos);
		return documentos;
	}
	
	/**
	 * Realiza uma operação de busca avançada. Toda busca deve retornar um conjunto de
	 * documentos que tenham todos os metadados passados.
	 * 
	 * @param buscaAvancada Busca a ser realizada.
	 * @return Resultado das buscas.
	 */
	public DocumentoDTO[] busca(BuscaAvancada buscaAvancada) {
		Map<Documento, Integer> respostaDocumento = buscaAvancada.busca(this.ds);
		DocumentoDTO[] documentos = ordena(respostaDocumento);
		this.br.adicionaBusca(buscaAvancada, documentos);
		return documentos;
	}

	/*
	 * Toda busca deve:
	 * - ordenar de acordo com o valor de relevância (valor no mapa)
	 * - retornar até 5 elementos (ou menos)
	 */
	private DocumentoDTO[] ordena(Map<Documento, Integer> respostaDocumento) {
		return respostaDocumento.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.limit(5)
				.map(Entry::getKey)
				.map(DocumentoDTO::new)
				.collect(Collectors.toList())
				.toArray(new DocumentoDTO[] {});
	}

	/**
	 * Recupera o histórico de busca a partir do número de busca (onde zero
	 * representa a primeira busca).
	 * 
	 * @param numero Número da busca a ser recuperada do histórico.
	 * @return HistóricoBusca na ordem cadastrada.
	 */
	public HistoricoBusca recuperaHistorico(int numero) {
		return this.br.recuperar(numero);
	}

}
