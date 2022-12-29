package com.franciscodantas.lunr;

import org.junit.jupiter.api.BeforeEach;

import com.franciscodantas.apresentacao.ApresentacaoController;
import com.franciscodantas.lunr.busca.BuscaController;
import com.franciscodantas.lunr.documento.DocumentoController;
import com.franciscodantas.similaridade.SimilaridadeController;

class BaseTest {

	public static final String TEXTO1_ID = "789";
	public static final String TEXTO2_ID = "ABC";
	public static final String JAVA_ID = "456";
	public static final String HTML_ID = "123";
	
	protected DocumentoController documentoController;
	protected BuscaController buscaController;
	protected SimilaridadeController similaridadeController;
	protected ApresentacaoController apresentacaoController;

	@BeforeEach
	void setUp() throws Exception {
		LunrApp lunrApp = new LunrApp();
		this.documentoController = lunrApp.getDocumentoController();
		this.buscaController = lunrApp.getBuscaController();
		this.similaridadeController = lunrApp.getSimilaridadeController();
		this.apresentacaoController = lunrApp.getApresentacaoController();
		var exemplo = new DocumentoExemplos();
		this.documentoController.adicionaDocumentoHtml(HTML_ID, exemplo.sampleHTML());
		this.documentoController.adicionaDocumentoJava(JAVA_ID, exemplo.sampleJava());
		this.documentoController.adicionaDocumentoTxt(TEXTO1_ID, "um arquivo! texto simples.\r\nuse DUAS linhas apenas.");
		this.documentoController.adicionaDocumentoTxt(TEXTO2_ID, "um arquivo! texto simples.\r\nuse TRÊS linhas agora.\r\nMAIS AVANÇO!");
	}
	
}
