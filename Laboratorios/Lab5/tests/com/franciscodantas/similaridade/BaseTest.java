package com.franciscodantas.similaridade;

import org.junit.jupiter.api.BeforeEach;

import com.franciscodantas.apresentacao.ApresentacaoController;
import com.franciscodantas.lunr.LunrApp;
import com.franciscodantas.lunr.busca.BuscaController;
import com.franciscodantas.lunr.documento.DocumentoController;

public class BaseTest {
	public static final String TEXTO1_ID = "789";
	public static final String TEXTO2_ID = "ABC";
	public static final String TEXTO3_ID = "DIF";
	public static final String JAVA1_ID = "DIF1";
	public static final String JAVA2_ID = "DIF2";
	public static final String HTML1_ID = "DIF3";
	public static final String HTML2_ID = "DIF4";
	
	protected DocumentoController documentoController;
	protected BuscaController buscaController;
	protected SimilaridadeController similaridadeController;
	protected ApresentacaoController apresentacaoController;
	protected DocumentoExemplos de;

	@BeforeEach
	void setUp() throws Exception {
		LunrApp lunrApp = new LunrApp();
		this.documentoController = lunrApp.getDocumentoController();
		this.buscaController = lunrApp.getBuscaController();
		this.similaridadeController = lunrApp.getSimilaridadeController();
		this.apresentacaoController = lunrApp.getApresentacaoController();
		this.de = new DocumentoExemplos();
		this.documentoController.adicionaDocumentoTxt(TEXTO1_ID, "Uma casa feliz é uma casa bonita");
		this.documentoController.adicionaDocumentoTxt(TEXTO2_ID, "Um dia feliz é um bom dia");
		this.documentoController.adicionaDocumentoTxt(TEXTO3_ID, "A B C import html");
		this.documentoController.adicionaDocumentoJava(JAVA1_ID, de.sampleJava1());
		this.documentoController.adicionaDocumentoJava(JAVA2_ID, de.sampleJava2());
		this.documentoController.adicionaDocumentoHtml(HTML1_ID, de.sampleHTML1());
		this.documentoController.adicionaDocumentoHtml(HTML2_ID, de.sampleHTML2());
	}
}
