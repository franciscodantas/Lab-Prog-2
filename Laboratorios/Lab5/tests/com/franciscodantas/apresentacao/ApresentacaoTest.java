package com.franciscodantas.apresentacao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import com.franciscodantas.lunr.documento.DocumentoDTO;

class ApresentacaoTest extends BaseTest {

	@Test
	void testApresentaNPrimeiras() {
		assertEquals("Apresentação das primeiras 5 linhas:" + "\n" + 
					 "package com.franciscodantas.lunr;" + "\n\n" + 
					 "import java.util.Optional;" + "\n\n" + 
					 "import com.franciscodantas.lunr.busca.BuscaController;" + "\n",
					 this.apresentacaoController.apresenta(JAVA_ID, "Primeiras"));
		
		assertEquals("Apresentação das primeiras 5 linhas:" + "\n" 
					 + "<!doctype html>\n"
					 + "<html>\n"
					 + "<head>\n"
					 + "    <title>Example Domain</title>\n"
					 + "\n",
					 this.apresentacaoController.apresenta(HTML_ID, "Primeiras"));
		
		assertEquals("Apresentação das primeiras 5 linhas:" + "\n"
					 + "1 linha" + "\n"
					 + "2 linha" +  "\n"
					 + "3 linha" + "\n" 
					 + "4 linha" + "\n" 
					 + "5 linha" + "\n", 
					 this.apresentacaoController.apresenta(TEXTO2_ID, "Primeiras"));
	}
	
	@Test
	void testApresentaNUltimas() {
		assertEquals("Apresentação das ultimas 5 linhas:" + "\n"
					 + "10 linha" + "\n"
					 + "9 linha" + "\n" 
					 + "8 linha" + "\n"
					 + "7 linha" + "\n" 
					 + "6 linha" + "\n", this.apresentacaoController.apresenta(TEXTO2_ID, "Ultimas"));
		
		assertEquals("Apresentação das ultimas 5 linhas:" + "\n"
				 + "</html>" + "\n"
				 + "</body>" + "\n" 
				 + "</div>" + "\n"
				 + "    <p><a href=\"https://www.iana.org/domains/example\">More information...</a></p>" + "\n" 
				 + "    domain in literature without prior coordination or asking for permission.</p>" + "\n", 
				 this.apresentacaoController.apresenta(HTML_ID, "Ultimas"));
		
		assertEquals("Apresentação das ultimas 5 linhas:" + "\n"
				 + "}" + "\n"
				 + "	}" + "\n" 
				 + "		this.dc.adicionaDocumentoTxt(id, use);" + "\n"
				 + "	public void adicionaDocumentoTxt(String id, String use) {" + "\n" 
				 + "	" + "\n", 
				 this.apresentacaoController.apresenta(JAVA_ID, "Ultimas"));
	}
	
	@Test
	void testApresentaCaps() {
		DocumentoDTO documentoTxt = this.documentoController.recuperarDocumento(TEXTO2_ID).get();
		String capsTxt = documentoTxt.getOriginal().toUpperCase();
		assertEquals("Apresentação em caixa alta:" + "\n" + capsTxt + "\n", this.apresentacaoController.apresenta(TEXTO2_ID, "Caps"));
		DocumentoDTO documentoHtml = this.documentoController.recuperarDocumento(HTML_ID).get();
		String capsHtml = documentoHtml.getOriginal().toUpperCase();
		assertEquals("Apresentação em caixa alta:" + "\n" + capsHtml, this.apresentacaoController.apresenta(HTML_ID, "Caps"));
		DocumentoDTO documentoJava = this.documentoController.recuperarDocumento(JAVA_ID).get();
		String capsJava = documentoJava.getOriginal().toUpperCase();
		assertEquals("Apresentação em caixa alta:" + "\n" + capsJava + "\n", this.apresentacaoController.apresenta(JAVA_ID, "Caps"));
	}
	
	@Test
	void testApresentaErroMetodo() {
		try {
			assertEquals("1 LINHA" + "\n" + "2 LINHA" +  "\n" + "3 LINHA" + "\n" + "4 LINHA" + "\n" + "5 LINHA" + "\n" + "6 LINHA" + "\n" + "7 LINHA" + "\n" + "8 LINHA" + "\n" + "9 LINHA" + "\n" + "10 LINHA" + "\n", this.apresentacaoController.apresenta(TEXTO2_ID, "Erro"));
			fail("Era para ter ocorrido um erro");
		} catch(IllegalArgumentException e) {
			assertEquals("Metodo de apresentação desconhecido", e.getMessage());
		}
	}
	
	@Test
	void testApresentaErroID() {
		try {
			assertEquals("1 LINHA" + "\n" + "2 LINHA" +  "\n" + "3 LINHA" + "\n" + "4 LINHA" + "\n" + "5 LINHA" + "\n" + "6 LINHA" + "\n" + "7 LINHA" + "\n" + "8 LINHA" + "\n" + "9 LINHA" + "\n" + "10 LINHA" + "\n", this.apresentacaoController.apresenta(null, "Erro"));
			fail("Era para ter ocorrido um erro");
		} catch(NullPointerException e) {
			assertEquals("ID não pode ser nulo", e.getMessage());
		}
		
		try {
			assertEquals("1 LINHA" + "\n" + "2 LINHA" +  "\n" + "3 LINHA" + "\n" + "4 LINHA" + "\n" + "5 LINHA" + "\n" + "6 LINHA" + "\n" + "7 LINHA" + "\n" + "8 LINHA" + "\n" + "9 LINHA" + "\n" + "10 LINHA" + "\n", this.apresentacaoController.apresenta("", "Erro"));
			fail("Era para ter ocorrido um erro");
		} catch(IllegalArgumentException e) {
			assertEquals("ID não pode ser vazio", e.getMessage());
		}
		
		try {
			assertEquals("1 LINHA" + "\n" + "2 LINHA" +  "\n" + "3 LINHA" + "\n" + "4 LINHA" + "\n" + "5 LINHA" + "\n" + "6 LINHA" + "\n" + "7 LINHA" + "\n" + "8 LINHA" + "\n" + "9 LINHA" + "\n" + "10 LINHA" + "\n", this.apresentacaoController.apresenta("A1n9", "Erro"));
			fail("Era para ter ocorrido um erro");
		} catch(NoSuchElementException e) {
			assertEquals("No value present", e.getMessage());
		}
	}
	
	@Test
	void testApresentaErroMetodoVazioNull() {
		try {
			assertEquals("1 LINHA" + "\n" + "2 LINHA" +  "\n" + "3 LINHA" + "\n" + "4 LINHA" + "\n" + "5 LINHA" + "\n" + "6 LINHA" + "\n" + "7 LINHA" + "\n" + "8 LINHA" + "\n" + "9 LINHA" + "\n" + "10 LINHA" + "\n", this.apresentacaoController.apresenta(TEXTO2_ID, null));
			fail("Era para ter ocorrido um erro");
		} catch(NullPointerException e) {
			assertEquals("Tipo de apresentação não pode ser nula", e.getMessage());
		}
		
		try {
			assertEquals("1 LINHA" + "\n" + "2 LINHA" +  "\n" + "3 LINHA" + "\n" + "4 LINHA" + "\n" + "5 LINHA" + "\n" + "6 LINHA" + "\n" + "7 LINHA" + "\n" + "8 LINHA" + "\n" + "9 LINHA" + "\n" + "10 LINHA" + "\n", this.apresentacaoController.apresenta(TEXTO2_ID, ""));
			fail("Era para ter ocorrido um erro");
		} catch(IllegalArgumentException e) {
			assertEquals("Tipo de apresentação não pode ser vazio", e.getMessage());
		}
	}
	
	@Test
	void testApresentaErroTamanho() {
		try {
			assertEquals("Uma casa feliz é uma casa bonita" + "\n", this.apresentacaoController.apresenta(TEXTO1_ID, "Primeiras"));
			fail("Era para ter ocorrido um erro");
		} catch(ArrayIndexOutOfBoundsException e) {
			assertEquals("Index 1 out of bounds for length 1", e.getMessage());
		}
	}

}
