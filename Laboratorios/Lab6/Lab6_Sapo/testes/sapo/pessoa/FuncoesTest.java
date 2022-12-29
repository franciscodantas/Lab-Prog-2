package sapo.pessoa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Testes de Funções.
 * 
 * @author José Arthur
 *
 */
class FuncoesTest extends PessoaBaseTeste{

	@Test
	void testCadastrarAluno() {
		this.facade.cadastrarAluno("111.111.111-11", "Arthur", "156", 1, new String[] {"Músico"});
		assertEquals("Arthur - 111.111.111-11\n"
				+ "Aluno - 156 - 1\n"
				+ "- Músico", facade.exibirPessoa("111.111.111-11"));	
	}
	
	@Test
	void testCadastrarProfessor() {
		this.facade.cadastrarProfessor("111.111.111-11", "Arthur", "156", new String[] {"Teoria Musical"}, new String[] {"Músico"});
		assertEquals("Arthur - 111.111.111-11\n"
				+ "Professor - 156 - Teoria Musical\n"
				+ "- Músico", facade.exibirPessoa("111.111.111-11"));
	}
	
	@Test
	void testCadastrarCpfInvalido() {
		try {
			this.facade.cadastrarAluno("", "Arthur", "156", 1, new String[] {"Músico"});
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		try {
			this.facade.cadastrarAluno(null, "Arthur", "156", 1, new String[] {"Músico"});
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	void testCadastrarNomeInvalido() {
		try {
			this.facade.cadastrarAluno("111.111.111-11", "", "156", 1, new String[] {"Músico"});
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		try {
			this.facade.cadastrarAluno("111.111.111-11", null, "156", 1, new String[] {"Músico"});
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	void testCadastrarMatriculaOuSiapeInvalido() {
		try {
			this.facade.cadastrarAluno("111.111.111-11", "Arthur", "", 1, new String[] {"Músico"});
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		try {
			this.facade.cadastrarAluno("111.111.111-11", "Arthur", null, 1, new String[] {"Músico"});
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
		
		try {
			this.facade.cadastrarProfessor("111.111.111-11", "Arthur", "", new String[] {"Teoria Musical"}, new String[] {"Músico"});
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		try {
			this.facade.cadastrarProfessor("111.111.111-11", "Arthur", null, new String[] {"Teoria Musical"}, new String[] {"Músico"});
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	void testAlterarFuncaoParaAluno() {
		this.facade.definirFuncaoAluno("987.654.321-00", "456", 1);
		assertEquals("José - 987.654.321-00\n"
				+ "Aluno - 456 - 1\n"
				+ "- Pianista", facade.exibirPessoa("987.654.321-00"));
	}
	
	@Test 
	void testAlterarFuncaoParaProfessor(){
		this.facade.definirFuncaoProfessor("123.456.789-10", "456", new String[] {"Teoria Musical"});
		assertEquals("Arthur - 123.456.789-10\n"
				+ "Professor - 456 - Teoria Musical\n"
				+ "- Músico", facade.exibirPessoa("123.456.789-10"));
	}
	
	@Test
	void testAlterarFuncaoComCpfInexistente() {
		try {
			this.facade.definirFuncaoProfessor("123.456.555-10", "456", new String[] {"Teoria Musical"});;
			fail("Era esperado uma excessão aqui");
		} catch (NoSuchElementException ne) {
			assertEquals("Pessoa não existe", ne.getMessage());
	}
	}

	@Test
	void testNivelProfessor() {
		//this.facade.cadastrarTarefa("SPD-0", "falar no discord", new String[] {"falar"});
		//this.facade.associarPessoaTarefa("987.654.321-00", "DTR-4");
		assertEquals(0, this.facade.pegarNivel("987.654.321-00"));
	}
	
	@Test
	void testNivelAluno() {
		//this.facade.cadastrarTarefa("SPD-0", "falar no discord", new String[] {"falar"});
		//this.facade.associarPessoaTarefa("123.456.789-10", "DTR-4");
		assertEquals(0, this.facade.pegarNivel("123.456.789-10"));
	}
}
