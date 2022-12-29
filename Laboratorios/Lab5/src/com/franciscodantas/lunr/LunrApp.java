package com.franciscodantas.lunr;

import com.franciscodantas.apresentacao.ApresentacaoController;
import com.franciscodantas.apresentacao.ApresentacaoService;
import com.franciscodantas.lunr.busca.BuscaController;
import com.franciscodantas.lunr.busca.BuscaService;
import com.franciscodantas.lunr.documento.DocumentoController;
import com.franciscodantas.lunr.documento.DocumentoService;
import com.franciscodantas.similaridade.SimilaridadeController;
import com.franciscodantas.similaridade.SimilaridadeService;

/**
 * O Lunr é um sistema de cadastro e busca de documentos, bem como de realização
 * de operações de similaridade entre os documentos cadastrados.
 * 
 * O LunrApp por sua vez é responsável por centralizar todas as inicializações
 * de todos os objetos de controller e serviços existentes.
 * 
 * O Controller é responsável por ser uma interface com o usuário, e os serviços
 * são responsáveis pela lógica de negócio.
 * 
 * Por centralizar a inicialização dos objetos principais de lógica do sistema,
 * é importante que o LunrFacade seja a única fote de acesso as demais
 * operações.
 */
public class LunrApp {

	private DocumentoController documentoController;
	private BuscaController buscaController;
	private SimilaridadeController similaridadeController;
	private ApresentacaoController apresentacaoController;

	/**
	 * Inicializador do LunrApp.
	 * 
	 * Todas as entidades de interação no sistema devem ser obtidas a partir
	 * do LunrApp. Isto garante a correta inicialização das entidades.
	 */
	public LunrApp() {
		var documentoService = new DocumentoService();
		var buscaService = new BuscaService(documentoService);
		var similaridadeService = new SimilaridadeService(documentoService);
		var apresentacaoService = new ApresentacaoService(documentoService);
		
		this.documentoController = new DocumentoController(documentoService);
		this.buscaController = new BuscaController(buscaService);
		this.similaridadeController = new SimilaridadeController(similaridadeService);
		this.apresentacaoController = new ApresentacaoController(apresentacaoService);
	}
	
	/**
	 * Obtem o controller de documentos para interação com os usuários.
	 * 
	 * @return DocumentoController para receber requisições do usuário sobre os
	 *         documentos.
	 */
	public DocumentoController getDocumentoController() {
		return this.documentoController;
	}
	
	/**
	 * Obtem o controller de buscas para interação com os usuários.
	 * 
	 * @return BuscaController para receber requisições do usuário sobre as
	 *         buscas.
	 */
	public BuscaController getBuscaController() {
		return this.buscaController;
	}
	
	/**
	 * Obtem o controller de similaridade para interação com os usuários.
	 * 
	 * @return SimilaridadeController para receber requisições do usuário sobre as
	 *         operações de similaridades entre documentos.
	 */
	public SimilaridadeController getSimilaridadeController() {
		return this.similaridadeController;
	}
	
	/**
	 * Obtem o controller de apresentacao para interação com os usuários.
	 * 
	 * @return ApresentacaoController para receber requisições do usuário sobre as
	 *         operações de apresentação de documentos.
	 */
	public ApresentacaoController getApresentacaoController() {
		return this.apresentacaoController;
	}
	
}