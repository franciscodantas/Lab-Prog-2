package com.franciscodantas.apresentacao;

import org.junit.jupiter.api.BeforeEach;

import com.franciscodantas.lunr.LunrApp;
import com.franciscodantas.lunr.busca.BuscaController;
import com.franciscodantas.lunr.documento.DocumentoController;
import com.franciscodantas.similaridade.SimilaridadeController;

public class BaseTest {
	public static final String TEXTO1_ID = "789";
	public static final String TEXTO2_ID = "ABC";
	public static final String TEXTO3_ID = "DIF";
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
		this.documentoController.adicionaDocumentoTxt(TEXTO1_ID, "Uma casa feliz é uma casa bonita");
		this.documentoController.adicionaDocumentoTxt(TEXTO2_ID, "1 linha" + "\n" + "2 linha" +  "\n" + "3 linha" + "\n" + "4 linha" + "\n" + "5 linha" + "\n" + "6 linha" + "\n" + "7 linha" + "\n" + "8 linha" + "\n" + "9 linha" + "\n" + "10 linha");
		this.documentoController.adicionaDocumentoTxt(TEXTO3_ID, "A B C");
	}
}
