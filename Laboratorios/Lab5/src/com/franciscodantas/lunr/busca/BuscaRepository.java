package com.franciscodantas.lunr.busca;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.franciscodantas.lunr.documento.DocumentoDTO;

/**
 * Entidade de repositório para os históricos de busca.
 * 
 * Não há necessidade de otimizar as buscas ou acesso para além da inserção
 * ordenada e acesso a partir de um índice específico.
 * 
 * @author francisco antonio dantas
 */
class BuscaRepository {

	private List<HistoricoBusca> buscas;
	private ValidadorBusca validador;

	/**
	 * Construtor padrão para inicialização das entidades básicas.
	 */
	BuscaRepository() {
		this.buscas = new ArrayList<>();
		this.validador = new ValidadorBusca();
	}

	/**
	 * Cadastra uma busca no histórico.
	 * 
	 * @param buscaSimples Operação de busca realizada.
	 * @param documentos   Documentos resultantes dessa busca.
	 */
	public void adicionaBusca(BuscaSimples buscaSimples, DocumentoDTO[] documentos) {
		String[] ids = Stream.of(documentos)
			.map(DocumentoDTO::getId)
			.collect(Collectors.toList())
			.toArray(new String[] {});
		this.buscas.add(new HistoricoBusca(buscaSimples, ids));
	}
	
	/**
	 * Cadastra uma busca no histórico.
	 * 
	 * @param buscaAvancada Operação de busca realizada.
	 * @param documentos   Documentos resultantes dessa busca.
	 */
	public void adicionaBusca(BuscaAvancada buscaAvancada, DocumentoDTO[] documentos) {
		String[] ids = Stream.of(documentos)
				.map(DocumentoDTO::getId)
				.collect(Collectors.toList())
				.toArray(new String[] {});
		this.buscas.add(new HistoricoBusca(buscaAvancada, ids));
	}

	/**
	 * Retorna um histórico de busca. O histórico é inserido na ordem em que as
	 * buscas são realizadas, associados a posição da busca na lista de buscas.
	 * 
	 * @param numero Posição (a partir de 0) do histórico de busca a ser retornado.
	 * @return Histórico de busca a ser retornado.
	 */
	public HistoricoBusca recuperar(int numero) {
		this.validador.valida(numero);
		if (numero > this.buscas.size()) {
			throw new IllegalArgumentException("Histórico inexistente: " + numero);
		}
		return this.buscas.get(numero);
	}

}
