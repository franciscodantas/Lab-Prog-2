package com.franciscodantas.lunr;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class DocumentoTest extends BaseTest {

	@Test
	void testAusente() {
		var documentoOpt = this.documentoController.recuperarDocumento("IDNaoExistente");
		assertTrue(documentoOpt.isEmpty());
	}
	
	@Test
	void testHTML() {
		var documentoOpt = this.documentoController.recuperarDocumento(HTML_ID);
		assertTrue(documentoOpt.isPresent());
		var doc = documentoOpt.get();
		assertEquals(HTML_ID, doc.getId(), "ID padrão do HTML");
		assertEquals("158", "" + doc.getTexto().length);
		assertEquals("46", doc.getMetadados().get("LINHAS"));
		assertEquals("html", doc.getMetadados().get("TIPO"));
		assertEquals("24", doc.getMetadados().get("BRUTE_TAGS"));
		assertTrue(doc.getMetadados().get("HEAD").length() > 10);
		assertEquals(0.59, doc.metricaTextoUtil(), 0.01);
	}
	
	@Test
	void testJava() {
		var documentoOpt = this.documentoController.recuperarDocumento(JAVA_ID);
		assertTrue(documentoOpt.isPresent());
		var doc = documentoOpt.get();
		assertEquals(JAVA_ID, doc.getId(), "ID padrão do Java");
		assertEquals(110, doc.getTexto().length);
		assertEquals("43", doc.getMetadados().get("LINHAS"));
		assertEquals("java", doc.getMetadados().get("TIPO"));
		assertEquals("10", doc.getMetadados().get("IMPORTS"));
		assertEquals("TRUE", doc.getMetadados().get("AUTHOR"));
		assertEquals(0.56, doc.metricaTextoUtil(), 0.01);
	}

	
	@Test
	void testTexto1() {
		var documentoOpt = this.documentoController.recuperarDocumento(TEXTO1_ID);
		assertTrue(documentoOpt.isPresent());
		var doc = documentoOpt.get();
		assertEquals(TEXTO1_ID, doc.getId(), "ID padrão do Texto");
		assertEquals(8, doc.getTexto().length, "Tamanho de 107 termos");
		assertArrayEquals(new String[] {"DUAS", "apenas", "arquivo", "linhas", "simples", "texto", "um", "use"}, doc.getTexto());
		assertEquals(0.80, doc.metricaTextoUtil(), 0.01);
	}
	
	@Test
	void testTotalDocumento() {
		assertEquals(4, this.documentoController.totalDocumentos());
	}
	
	@Test
	void testConcatena() {
		var documentoOpt = this.documentoController.recuperarDocumento(TEXTO1_ID);
		assertTrue(documentoOpt.isPresent());
		var documentoOpt2 = this.documentoController.recuperarDocumento(JAVA_ID);
		assertTrue(documentoOpt2.isPresent());
		String novoId = "_MERGE" + JAVA_ID + "|" + TEXTO1_ID;
		String concatenado = this.documentoController.concatenaDocumentos(JAVA_ID, TEXTO1_ID);
		assertEquals(this.documentoController.recuperarDocumento(novoId),this.documentoController.recuperarDocumento(concatenado));
		assertNotEquals(this.documentoController.recuperarDocumento(JAVA_ID),this.documentoController.recuperarDocumento(concatenado));
		assertNotEquals(this.documentoController.recuperarDocumento(TEXTO1_ID),this.documentoController.recuperarDocumento(concatenado));
	}
	
	@Test
	void testConcatenaErro() {
		var documentoOpt = this.documentoController.recuperarDocumento("IdErro");
		assertFalse(documentoOpt.isPresent());
		var documentoOpt2 = this.documentoController.recuperarDocumento(JAVA_ID);
		assertTrue(documentoOpt2.isPresent());
		try {
			String novoId = "_MERGE" + JAVA_ID + "|" + "IdErro";
			String concatenado = this.documentoController.concatenaDocumentos(JAVA_ID, "IdErro");
			assertEquals(this.documentoController.recuperarDocumento(novoId),this.documentoController.recuperarDocumento(concatenado));
			assertNotEquals(this.documentoController.recuperarDocumento(JAVA_ID),this.documentoController.recuperarDocumento(concatenado));
			assertNotEquals(this.documentoController.recuperarDocumento(TEXTO1_ID),this.documentoController.recuperarDocumento(concatenado));
		}catch(NoSuchElementException e) {
			assertEquals("Documento não existe", e.getMessage());
		}
		
	}
	
}
