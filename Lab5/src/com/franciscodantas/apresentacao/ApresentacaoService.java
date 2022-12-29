package com.franciscodantas.apresentacao;

import java.util.Optional;

import com.franciscodantas.lunr.documento.Documento;
import com.franciscodantas.lunr.documento.DocumentoService;

/**
 * Componente para tratamento da lógica de negócio relativa 
 * a apresentação de documentos.
 * 
 * @author francisco antonio dantas
 */
public class ApresentacaoService {
	
	/**
	 * DocumentoService a ser utilizado pelo ApresentacaoService.
	 */
	private DocumentoService documentoService;
	
	/**
	 * Interface que representa as formas de apresentar o documento.
	 */
	private Apresentacao apresentacao;
	
	private ValidadorApresentacao va;

	/**
	 * Inicialização da lógica de serviço.
	 * 
	 * @param documentoService DocumentoService a ser utilizado pelo
	 *                         ApresentacaoService.
	 */
	public ApresentacaoService(DocumentoService documentoService) {
		this.documentoService = documentoService;
		this.va = new ValidadorApresentacao();
	}

	/**
	 * Realiza a apresentação do documento indicado.
	 * 
	 *  
	 * @param docId Documento a ser apresentado.
	 * @param tipoApresentacao modo de apresentacao a ser aplicado sobre o documento.
	 * @return Uma String que apresenta as linhas de acordo com o tipo solicitado: as 5 pŕimeiras linhas,
	 * as 5 ultimas linhas(comecando da ultima) ou o texto todo em Caixa alta;
	 */
	public String apresenta(String docId, String tipoApresentacao) {
		va.valida(docId, tipoApresentacao);
		Optional<Documento> doc = this.documentoService.recuperaDocumento(docId);
		String[] original = doc.get().getOriginal().split("\n");
		switch(tipoApresentacao) {
		case "Primeiras":
			this.apresentacao = new ApresentaNPrimeiras();
			return this.apresentacao.apresenta(original);
		case "Ultimas":
			this.apresentacao = new ApresentaNUltimas();
			return this.apresentacao.apresenta(original);
		case "Caps":
			this.apresentacao = new ApresentaCaps();
			return this.apresentacao.apresenta(original);
		default:
			throw new IllegalArgumentException("Metodo de apresentação desconhecido");
		}
	}
}
