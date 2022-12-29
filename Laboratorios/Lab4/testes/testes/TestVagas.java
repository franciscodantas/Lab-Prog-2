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

public class TestVagas {
	
	public Vaga vaga;
	
	@BeforeEach
	public void criaVaga() {
		vaga = new Vaga(0, 4, "Rua Rodrigues Alves");
	}
	
	@Test
	public void testToString() {
		assertEquals("0 - Rua Rodrigues Alves - https:// - LIVRE", vaga.toString());
	}
	
	@Test
	public void mudaStatus() {
		vaga.mudarStatus();
		assertEquals("OCUPADO", vaga.getStatus());
	}
	
	@Test
	public void usos() {
		vaga.mudarStatus();
		assertEquals(1, vaga.getUsos());
		vaga.mudarStatus();
		vaga.mudarStatus();
		assertEquals(2, vaga.getUsos());
	}
	
	@Test
	public void valorDevido() {
		assertEquals(3.4, vaga.valorDevido(1), 0.1);
	}
	
	@Test
	public void criaVagaUrl() {
		Vaga vaga2 =  new Vaga(0, 4, "Rua Rodrigues Alves","https://goo.gl/maps/kdQcHaG4XRJZN4VR9");
		assertEquals("0 - Rua Rodrigues Alves - https://goo.gl/maps/kdQcHaG4XRJZN4VR9 - LIVRE", vaga2.toString());
	}
	
	@Test
	public void vagasIguais() {
		Vaga vaga2 =  new Vaga(0, 4, "Rua Rodrigues Alves");
		assertTrue(vaga.equals(vaga2));
	}
}
