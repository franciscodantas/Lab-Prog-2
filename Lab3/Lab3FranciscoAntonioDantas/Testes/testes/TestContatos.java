package testes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agenda.*;

/**
 * 
 * @author Francisco Antonio
 *
 */
public class TestContatos {
	Contato contato;
	
	/**
	 * faz antes de cada teste
	 */
	@BeforeEach
	public void criaContato() {
		contato = new Contato("Francisco", "Antonio", "83981442233");
	}
	
	/**
	 * testa a representacao de um contato
	 */
	@Test
	public void representaContato() {
		assertEquals("\n" + "Francisco Antonio" + "\n" + "83981442233" + "\n", contato.toString());
	}
	
	/**
	 * testa a representacao de um contato com favorito
	 */
	@Test
	public void representaComFavortio() {
		contato.favoritar();
		assertEquals("\n<3 " + "Francisco Antonio" + "\n" + "83981442233" + "\n", contato.toString());
	}
	
	/**
	 * testa a representacao de um contato com tag
	 */
	@Test
	public void representaComTag() {
		contato.adicionarTag("Ufcg", "1");
		assertEquals("\n" + "Francisco Antonio" + "\n" + "83981442233" + "\n" + "Ufcg", contato.toString());
	}
	
	/**
	 * testa se dois contatos sao iguais
	 */
	@Test
	public void contatoIguais() {
		Contato contato2 = new Contato("Francisco", "Antonio", "83981442233");
		assertTrue(contato.equals(contato2));
	}
	
	/**
	 * testa se dois contatos sao diferentes
	 */
	@Test
	public void contatoDiferente() {
		Contato contato2 = new Contato("Luana", "Taynara", "83981442233");
		assertFalse(contato.equals(contato2));
	}
	
	/**
	 * testa a representacao do nome completo de um contato
	 */
	@Test
	public void nomeCompleto() {
		assertEquals("Francisco Antonio", contato.getNomeCompleto());
	}
	
	/**
	 * testa a adicao de uma tag ao contato
	 */
	@Test
	public void adicionaTag() {
		contato.adicionarTag("Ufcg", "1");
		assertEquals("\n" + "Francisco Antonio" + "\n" + "83981442233" + "\n" + "Ufcg", contato.toString());
	}
	
	/**
	 * favorita um contato 
	 */
	@Test
	public void favoritar() {
		contato.favoritar();
		assertEquals("\n<3" + " " + "Francisco Antonio" + "\n" + "83981442233" + "\n", contato.toString());
	}
	
	/**
	 * confere se e um contato favoritado
	 */
	@Test
	public void eFavorito() {
		contato.favoritar();
		assertTrue(contato.getfavorito());
	}
}
