package com.franciscodantas.apresentacao;

/**
 * Controller para operações relacionadas com a apresentação
 * de documentos.
 * 
 * @author francisco antonio dantas
 */
public class ApresentacaoController {
	
	/**
	 * ApresentacaoService a ser utilizado.
	 */
	private ApresentacaoService apresentacaoService;

	/**
	 * Construtor padrão, inicializa com o service a ser usado pelo controller.
	 * 
	 * @param apresentacaoService ApresentacaoService a ser utilizado.
	 */
	public ApresentacaoController(ApresentacaoService apresentacaoService) {
		this.apresentacaoService = apresentacaoService;
	}

	/**
	 * Operação de apresentação de um documento. Para a apresentação
	 * será possível exibição o documento de acordo com o modo de exibição
	 * especificado.
	 * 
	 * @param docId documento a ser apresentado.
	 * @param tipoApresentacao modo de apresentacao a ser aplicado sobre o documento.
	 * @return Uma String que apresenta as linhas de acordo com o tipo solicitado: as 5 pŕimeiras linhas,
	 * as 5 ultimas linhas(comecando da ultima) ou o texto todo em Caixa alta;
	 */
	public String apresenta(String docId, String tipoApresentacao) {
		return apresentacaoService.apresenta(docId, tipoApresentacao);
	}

}
