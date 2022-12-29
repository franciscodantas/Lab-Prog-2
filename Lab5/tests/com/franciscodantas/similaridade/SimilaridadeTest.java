package com.franciscodantas.similaridade;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class SimilaridadeTest extends BaseTest {

	@Test
	void testSimilaridadeDocsDiferentes() {
		assertEquals(0.2,this.similaridadeController.similaridade(TEXTO1_ID, TEXTO2_ID), 0.01);
		assertEquals(0.46,this.similaridadeController.similaridade(HTML1_ID, HTML2_ID), 0.01);
		assertEquals(0.57,this.similaridadeController.similaridade(JAVA1_ID, JAVA2_ID), 0.01);
	}
	
	@Test
	void testSimilaridadeDocsIguais() {
		assertEquals(1,this.similaridadeController.similaridade(TEXTO1_ID, TEXTO1_ID), 0.01);
		assertEquals(1,this.similaridadeController.similaridade(HTML1_ID, HTML1_ID), 0.01);
		assertEquals(1,this.similaridadeController.similaridade(JAVA1_ID, JAVA1_ID), 0.01);
	}
	
	@Test
	void testSemSimilaridade() {
		assertEquals(0,this.similaridadeController.similaridade(TEXTO1_ID, TEXTO3_ID), 0.01);
		assertEquals(0,this.similaridadeController.similaridade(HTML1_ID, TEXTO1_ID), 0.01);
		assertEquals(0,this.similaridadeController.similaridade(JAVA1_ID, TEXTO1_ID), 0.01);
	}
	
	@Test
	void testSimilaridadeTiposDiferentes() {
		assertEquals(0.009,this.similaridadeController.similaridade(HTML1_ID, TEXTO3_ID), 0.001);
		assertEquals(0.025,this.similaridadeController.similaridade(JAVA1_ID, TEXTO3_ID), 0.001);
	}
	
	@Test
	void testSimilaridadeDocNullVazio() {
		try {
			assertEquals(0.2,this.similaridadeController.similaridade(null, TEXTO2_ID), 0.01);
		}
		catch(NullPointerException e) {
			assertEquals("ID não pode ser nulo", e.getMessage());
		}
		
		try {
			assertEquals(0.2,this.similaridadeController.similaridade("", TEXTO2_ID), 0.01);
		}catch(IllegalArgumentException e) {
			assertEquals("ID não pode ser vazio", e.getMessage());
		}
	}
	
	@Test
	void testSimilaridadeDocIdInvalido() {
		try {
			assertEquals(0.2,this.similaridadeController.similaridade("A012", TEXTO2_ID), 0.01);
		}
		catch(NoSuchElementException e) {
			assertEquals("No value present", e.getMessage());
		}
	}

}
