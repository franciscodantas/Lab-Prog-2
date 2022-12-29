package testes;

/**
 * 
 * @author Francisco Antonio Sousa
 *
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import estacionAqui.*;

class TestEstacionamento {
	
	public Estacionamento estacionamento;
	
	@BeforeEach
	public void criaEstacionamento() {
		estacionamento = new Estacionamento();
	}
	
	@Test
	public void criaVaga() {
		assertTrue(estacionamento.cadastravaga(3, "Rua rodrigues alves"));
	}
	
	@Test
	public void criaVagaComUrl() {
		assertTrue(estacionamento.cadastravaga(3, "Rua rodrigues alves", "https://goo.gl/maps/kdQcHaG4XRJZN4VR9"));
	}
	
	@Test
	public void criaVagaComErro() {
		try{
			assertTrue(estacionamento.cadastravaga(3, null));
			fail("era para ter acontecido uma excessão");
		}
		catch(IllegalArgumentException e){
			assertEquals("entrada invalida", e.getMessage());
		}
	}

	@Test
	void listarVagas() {
		estacionamento.cadastravaga(3, "Rua rodrigues alves","https://goo.gl/maps/kdQcHaG4XRJZN4VR9");
		estacionamento.cadastravaga(5, "Rua gato preto");
		estacionamento.mudarStatus(1);
		assertEquals("0 - Rua rodrigues alves - https://goo.gl/maps/kdQcHaG4XRJZN4VR9 - LIVRE\n" + "1 - Rua gato preto - https:// - OCUPADO\n", estacionamento.listavagas());
	}
	
	@Test
	void mudarStatus() {
		estacionamento.cadastravaga(3, "Rua rodrigues alves");
		estacionamento.mudarStatus(0);
		assertEquals("0 - Rua rodrigues alves - https:// - OCUPADO\n", estacionamento.listavagas());
	}
	
	@Test
	void simulaPreco() {
		estacionamento.cadastravaga(3, "Rua rodrigues alves");
		assertEquals(9.9, estacionamento.simulaValor(0, 3), 0.1);
	}
	
	@Test
	void simulaPrecoInvalido1() {
		estacionamento.cadastravaga(3, "Rua rodrigues alves");
		try {
			estacionamento.simulaValor(0, 0);
			fail("era para ter acontecido uma excessão");
		}catch(IllegalArgumentException e) {
			assertEquals("entrada invalida", e.getMessage());
		}
	}
	
	@Test
	void simulaPrecoInvalido2() {
		estacionamento.cadastravaga(3, "Rua rodrigues alves");
		try {
			estacionamento.simulaValor(1, 3);
			fail("era para ter acontecido uma excessão");
		}catch(IllegalArgumentException e) {
			assertEquals("entrada invalida", e.getMessage());
		}
	}
	
	@Test
	void simulaPrecoInvalido3() {
		estacionamento.cadastravaga(3, "Rua rodrigues alves");
		try {
			estacionamento.simulaValor(100, 3);
			fail("era para ter acontecido uma excessão");
		}catch(IllegalArgumentException e) {
			assertEquals("entrada invalida", e.getMessage());
		}
	}
	
	@Test
	void vagasAtivas() {
		estacionamento.cadastravaga(3, "Rua rodrigues alves");
		estacionamento.cadastravaga(3, "Rua gato preto");
		assertEquals(2, estacionamento.getVagasAtivas());
	}
	
	@Test
	void vagasLivre() {
		estacionamento.cadastravaga(3, "Rua rodrigues alves");
		assertEquals(0, estacionamento.vagaLivre());
	}
	
	@Test
	void vagasLivrePesquisa() {
		estacionamento.cadastravaga(3, "Rua rodrigues alves");
		assertEquals(0, estacionamento.vagaLivre("Rua rodrigues alves", 3));
	}
	
	@Test
	void vagasLivrePesquisaTudoOcupado() {
		estacionamento.cadastravaga(3, "Rua rodrigues alves");
		estacionamento.mudarStatus(0);
		assertEquals(-1, estacionamento.vagaLivre("Rua rodrigues alves", 3));
	}
	
	@Test
	void relatorio() {
		estacionamento.cadastravaga(3, "Rua rodrigues alves");
		estacionamento.mudarStatus(0);
		estacionamento.cadastravaga(5, "Rua gato preto");
		assertEquals("Vaga 0 - 1"+ "\n" +"Vaga 1 - 0" + "\n", estacionamento.relatorio());
	}

}
