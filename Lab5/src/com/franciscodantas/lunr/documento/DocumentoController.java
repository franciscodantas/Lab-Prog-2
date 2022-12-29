package com.franciscodantas.lunr.documento;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * O DocumentoController é responsável por receber requisições do usuário e do
 * sistema e converter em operações lógicas (DocumentoService).
 * 
 * A responsabilidade de validação das entradas é também operacionalizada aqui.
 */
public class DocumentoController {

	private DocumentoService ds;
	private ValidadorDocumentos validador;

	/**
	 * Construtor padrão de DocumentoController. Manter um controller por sistema.
	 * 
	 * @param ds DocumentService a ser utilizado pelo controller.
	 */
	public DocumentoController(DocumentoService ds) {
		this.ds = ds;
		this.validador = new ValidadorDocumentos();
	}

	/**
	 * Adiciona um documento texto, validando o ID e conteúdo para objetos não nulos
	 * e vazios.
	 * 
	 * @param id  ID do documento a ser adicionado.
	 * @param txt Texto do documento a ser adicionado.
	 */
	public void adicionaDocumentoTxt(String id, String txt) {
		this.validador.validacao(id, txt);
		this.ds.adicionaDocumento(new DocumentoTexto(id, txt));
	}

	/**
	 * Adiciona um documento HTML, validando o ID e o conteúdo para objetos não
	 * nulos e vazios.
	 * 
	 * O HTML não define um padrão gramatical correto para sua leitura. Assim, a
	 * extração de texto do HTML pode não operar corretamente.
	 * 
	 * @param id   ID do documento a ser inserido.
	 * @param html HTML do documento a ser adicionado.
	 */
	public void adicionaDocumentoHtml(String id, String html) {
		this.validador.validacao(id, html);
		this.ds.adicionaDocumento(new DocumentoHtml(id, html));		
	}

	/**
	 * Adiciona um documento java, validando o ID e o conteúdo para objetos não
	 * nulos e vazios.
	 * 
	 * Não se pretende aqui implementar um analisador léxico de java para realizar a
	 * extração, mas sim identificar as principais palavras utilizadas no texto,
	 * eliminando palavras-chave.
	 * 
	 * @param id   ID do documento a ser inserido
	 * @param java Código java do documento a ser adicionado.
	 */
	public void adicionaDocumentoJava(String id, String java) {
		this.validador.validacao(id, java);
		this.ds.adicionaDocumento(new DocumentoJava(id, java));		
	}

	/**
	 * Recupera um documento retornando um Optional Documento vazio caso o
	 * documento não seja encontrado.
	 * 
	 * @param id ID do documento a ser recuperado.
	 * @return Optional com o documento encontrado (ou vazio caso contrário).
	 */
	public Optional<DocumentoDTO> recuperarDocumento(String id) {
		this.validador.validacao(id);
		Optional<Documento> dOpt = this.ds.recuperaDocumento(id);
		if (dOpt.isEmpty()) {
			return Optional.empty();
		}
		Documento d = dOpt.get();
		return Optional.of(new DocumentoDTO(d));
	}

	/**
	 * Retorna o total de documentos cadastrados no sistema. Os documentos são
	 * identificado unicamente a partir dos seus IDs.
	 * 
	 * @return Retorna o total de documentos no sistema.
	 */
	public int totalDocumentos() {
		return this.ds.totalDocumentos();
	}

	/**
	 * Retorna o valor de métrica útil de um documento. A métrica útil é calculada
	 * como proporção do texto útil (extraído) em relação ao texto original. Por
	 * isso é um valor que vai de 0 a 1 (inclusivos).
	 * 
	 * @param id ID do documento a ser verificado.
	 * @return O valor de métrica de texto útil recuperada.
	 */
	public double recuperaMetricaTextoUtil(String id) {
		this.validador.validacao(id);
		Optional<Documento> recuperaDocumento = this.ds.recuperaDocumento(id);
		if (recuperaDocumento.isEmpty()) {
			throw new NoSuchElementException("Documento inexistente");
		}
		return recuperaDocumento.get().metricaTextoUtil();
	}

	/**
	 * Concatena dois documentos a partir do texto útil de ambos. Os documentos
	 * originais não são alterados. A chave do documento é identificada por _MERGE +
	 * ID1 + | + ID2.
	 * 
	 * @param id1 ID do primeiro documento a ser concatenado.
	 * @param id2 ID do segundo documento a ser concatenado.
	 * @return O ID do documento concatenado que foi cadastrado no sistema.
	 */
	public String concatenaDocumentos(String id1, String id2) {
		this.validador.validacao(id1);
		this.validador.validacao(id2);
		return this.ds.concatena(id1, id2);
	}

	/**
	 * Rertona um array de palavras úteis mais utilizadas no documento que tenha
	 * mais de 5 letras.
	 * 
	 * @param id ID do documento a ser sumarizado.
	 * @return Sumarização do documento em palavras mais utilizadas.
	 */
	public String[] sumariza(String id) {
		this.validador.validacao(id);
		return this.ds.sumariza(id);
	}

}
