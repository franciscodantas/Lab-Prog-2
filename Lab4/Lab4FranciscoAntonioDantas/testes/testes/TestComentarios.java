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

public class TestComentarios {
	public Comentarios comentarios;
	
	@BeforeEach
	public void criaVaga() {
		comentarios = new Comentarios();
	}
	
	@Test
	public void adicionaComentario() {
		comentarios.criaComentario("Que legal");
		assertEquals("Que legal () \n", comentarios.listaComentarios());
	}
	
	@Test
	public void adicionaComentarioComAutor() {
		comentarios.criaComentario("Que legal", "Francisco");
		assertEquals("Que legal (Francisco) \n", comentarios.listaComentarios());
	}
	
	@Test
	public void listaComentarios() {
		comentarios.criaComentario("Que legal", "Francisco");
		comentarios.criaComentario("Maneiro");
		assertEquals("Que legal (Francisco) \n" + "Maneiro () \n", comentarios.listaComentarios());
	}
	
	@Test
	public void criaComentariosEmExcesso() {
			comentarios.criaComentario("Que legal", "Francisco");
			comentarios.criaComentario("Maneiro");
			comentarios.criaComentario("Que legal", "Francisco");
			comentarios.criaComentario("Maneiro");
			comentarios.criaComentario("Maneiro");
			comentarios.criaComentario("Maneiro");
			assertEquals("Maneiro () \n" + "Maneiro () \n" + "Que legal (Francisco) \n" + "Maneiro () \n" + "Maneiro () \n", comentarios.listaComentarios());
	}
}
