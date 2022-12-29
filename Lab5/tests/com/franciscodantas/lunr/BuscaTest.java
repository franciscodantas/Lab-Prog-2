package com.franciscodantas.lunr;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.franciscodantas.lunr.documento.DocumentoDTO;

class BuscaTest extends BaseTest {

	@Test
	void testAusente() {
		DocumentoDTO[] busca = this.buscaController.busca(new String[] {"ABCDEFGHI", "JKLMNOPQRST"});
		assertEquals(0, busca.length, "Sem resultados de busca");
	}
	
	@Test
	void testTermoUnico() {
		DocumentoDTO[] busca = this.buscaController.busca(new String[] {"public"});
		assertEquals(1, busca.length, "Apenas 1 resultado");
		assertEquals(JAVA_ID, busca[0].getId(), "Arquivo java");
	}	
	
	@Test
	void testTermoComum() {
		DocumentoDTO[] busca = this.buscaController.busca(new String[] {"use"});
		assertEquals(4, busca.length, "Todos os documentos");
		Set<String> ids = Stream.of(busca).map(DocumentoDTO::getId).collect(Collectors.toSet());
		Set<String> expectedIds = Stream.of(new String[] {TEXTO1_ID, TEXTO2_ID, HTML_ID, JAVA_ID}).collect(Collectors.toSet());
		assertEquals(expectedIds, ids);
	}
	
	@Test
	void testTermoComumETermoRaro() {
		DocumentoDTO[] busca = this.buscaController.busca(new String[] {"use", "public"});
		assertEquals(4, busca.length, "Todos os documentos");
		Set<String> ids = Stream.of(busca).map(DocumentoDTO::getId).collect(Collectors.toSet());
		Set<String> expectedIds = Stream.of(new String[] {TEXTO1_ID, TEXTO2_ID, HTML_ID, JAVA_ID}).collect(Collectors.toSet());
		assertEquals(expectedIds, ids);
		assertEquals(JAVA_ID, busca[0].getId(), "Arquivo java");
	}

	@Test
	void testHistoricoDeBusca() {
		this.buscaController.busca(new String[] {"public"});
		this.buscaController.busca(new String[] {"use"});
		this.buscaController.busca(new String[] {"use", "public"});
		
		Map<String, String> metadadosBuscados = new HashMap<>();
		metadadosBuscados.put("TIPO", "txt");
		this.buscaController.busca(metadadosBuscados);

		String[][] historicoDepuracao = this.buscaController.recuperaHistoricoDepuracao(0);
		String[] historicoIds= this.buscaController.recuperaHistoricoIds(0);
		assertEquals(1, historicoDepuracao.length);
		assertArrayEquals(new String[] {"TERMO 1", "public"}, historicoDepuracao[0]);
		assertArrayEquals(new String[] {JAVA_ID}, historicoIds);

		historicoDepuracao = this.buscaController.recuperaHistoricoDepuracao(1);
		historicoIds= this.buscaController.recuperaHistoricoIds(1);
		assertEquals(1, historicoDepuracao.length);
		assertArrayEquals(new String[] {"TERMO 1", "use"}, historicoDepuracao[0]);
		assertEquals(4, historicoIds.length);
		
		historicoDepuracao = this.buscaController.recuperaHistoricoDepuracao(2);
		historicoIds= this.buscaController.recuperaHistoricoIds(2);
		assertEquals(2, historicoDepuracao.length);
		assertArrayEquals(new String[] {"TERMO 1", "use"}, historicoDepuracao[0]);
		assertArrayEquals(new String[] {"TERMO 2", "public"}, historicoDepuracao[1]);
		
	}
	
	@Test
	void testBuscaAvancada() {
		Map<String, String> metadadosBuscados = new HashMap<>();
		metadadosBuscados.put("TIPO", "txt");
		DocumentoDTO[] busca = this.buscaController.busca(metadadosBuscados);
		assertEquals(2, busca.length, "Todos os documentos de texto");

		Set<String> ids = Stream.of(busca).map(DocumentoDTO::getId).collect(Collectors.toSet());
		Set<String> expectedIds = Stream.of(new String[] {TEXTO1_ID, TEXTO2_ID}).collect(Collectors.toSet());
		assertEquals(expectedIds, ids);
	}
	
	@Test
	void testBuscaAvancadaDoisMetadados() {
		Map<String, String> metadadosBuscados = new HashMap<>();
		metadadosBuscados.put("TIPO", "txt");
		metadadosBuscados.put("LINHAS", "1");
		DocumentoDTO[] busca = this.buscaController.busca(metadadosBuscados);
		assertEquals(1, busca.length, "Todos os documentos de texto");
		assertEquals(TEXTO1_ID, busca[0].getId());
	}
	
	
}
