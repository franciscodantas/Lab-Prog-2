package com.franciscodantas.lunr.documento;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * DocumentoService é a base de lógica de operações sobre o documento. Demais
 * classes devem fazer uso desse serviço para acessar e operar sobre documentos.
 * 
 * Deve existir apenas um único objeto do tipo DocumentoService em todo o
 * sistema para garantir unicidade dos documentos e das operações realizadas.
 * 
 * @author francisco antonio dantas
 */
public class DocumentoService {

	/*
	 * Repositório. Configurado externamente para que essa classe não se
	 * responsabilize por sua inicialização.
	 */
	private DocumentoRepository dr;

	/**
	 * Construtor padrão do documento service.
	 *
	 */
	public DocumentoService() {
		this.dr = new DocumentoRepository(); 
	}
	
	/**
	 * Adiciona um documento. O documento já deve ter um ID. Este ID será usado para
	 * registro no sistema. Caso o documento já exista, o documento será
	 * sobrescrito.
	 * 
	 * @param d Documento a ser inserido.
	 */
	public void adicionaDocumento(Documento d) {
		this.dr.adiciona(d);
	}

	/**
	 * Recupera um documento. Caso um documento não exista, isso é retornado na
	 * forma de um Optional.
	 * 
	 * @param id ID do documento a ser recuperado.
	 * @return Retorna o documento recuperado do documento.
	 */
	public Optional<Documento> recuperaDocumento(String id) {
		return this.dr.recupera(id);
	}

	/*
	 * GAMBARE SENPAI! ٩(＾◡＾)۶ SE NÃO DER CERTO.... GRITE! (✖╭╮✖)
	 */
	private Documento recuperaDocumentoOuFalhe(String id) {
		Optional<Documento> optional = this.dr.recupera(id);
		if (optional.isEmpty()) {
			throw new NoSuchElementException("Documento não existe");
		}
		return optional.get();
	}
	

	/**
	 * Contabiliza o total de documentos cadastrados na base de dados.
	 * 
	 * @return O total de documentos presentes. Documentos são identificados
	 *         unicamente pelo ID, e não por seus textos.
	 */
	public int totalDocumentos() {
		return this.dr.totalDocumentos();
	}

	/**
	 * Concatena dois documentos. Os documentos passam a ter uma nova identidade
	 * gerada no formato: _MERGE + ID1 + | + ID2.
	 * 
	 * O merge é feito a partir do texto extraído do documento.
	 * 
	 * @param id1 ID do primeiro documento a ser mergeado.
	 * @param id2 ID do segundo documeto a ser mergeado.
	 * @return O ID do novo documento gerado a partir da concatenação.
	 */
	public String concatena(String id1, String id2) {
		Documento d1 = this.recuperaDocumentoOuFalhe(id2);
		Documento d2 = this.recuperaDocumentoOuFalhe(id2);
		String novoId = "_MERGE" + id1 + "|" + id2;
		String novoTexto = Stream
				.concat(Stream.of(d1.getTexto()), Stream.of(d2.getTexto()))
				.collect(Collectors.joining())
				.toString();
		this.adicionaDocumento(new DocumentoTexto(novoId, novoTexto));
		return novoId;
	}

	/**
	 * Sumariza um documento pegando as 5 palavras mais comuns com mais de 5 letras.
	 * Em caso de empate entre os elementos mais comuns, não há garantia de ordem.
	 * 
	 * @param id Id do documento a ser sumarizado.
	 * @return Lista de sumarização do documento com até 5 palavras.
	 */
	public String[] sumariza(String id) {
		Documento d = this.recuperaDocumentoOuFalhe(id);
		List<String> collected = Stream.of(d.getTexto())
			.filter((x) -> x.length() > 5)
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
			.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByValue())
			.limit(5)
			.map(Entry::getKey)
			.collect(Collectors.toList());
		return collected.toArray(new String[] {});
	}

	/**
	 * Realiza uma busca simples por documentos que possuem determinado termo. A
	 * busca implementada pelo DocumentoService não deve ser complexa.
	 * 
	 * @param termo Termo a ser buscado
	 * @return Documentos que possuem o termo.
	 */
	public Set<Documento> busca(String termo) {
		return this.dr.busca(termo);
	}
	
	/**
	 * Realiza uma busca avançada por documentos que possuam todos
	 * os metadados requeridos. 
	 * 
	 * @param metadados Todos os metadados a serem buscados.
	 * @return Documentos que possuam os metadados.
	 */
	public Map<Documento, Integer> buscaAvancada(Map<String,String> metadados) {
		return this.dr.buscaAvancada(metadados);
	}
	
	/**
	 * Retorna o texto original do documento, sem nenhum tratamento.
	 * 
	 * @param id identificação do documento.
	 * @return Texto original.
	 */
	public String getOriginal(String id) {
		return this.recuperaDocumento(id).get().getOriginal();
	}
	
	
}
