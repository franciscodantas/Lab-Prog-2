package sapo.pessoa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
/**
 * Teste de pessoas.
 * 
 * @author José Arthur
 */
class PessoaTeste extends PessoaBaseTeste{

	@Test
	void testCadastraPessoa() {
		this.facade.cadastrarPessoa("115-256-874-45", "João", new String[] {"Java"});
		assertEquals("João - 115-256-874-45\n"
				+ "- Java", facade.exibirPessoa("115-256-874-45"));	
	}
	
	@Test
	void testCadastraPessoaCpfInvalido() {
		try {
			this.facade.cadastrarPessoa("", "João", new String[] {"Java"});
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
	}
		try {
			this.facade.cadastrarPessoa(null, "João", new String[] {"Java"});
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException pe) {
	}
	}
	
	@Test 
	void testCadastraPessoaNomeInvalido() {
		try {
			this.facade.cadastrarPessoa("115-256-874-45", "", new String[] {"Java"});
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
	}
		try {
			this.facade.cadastrarPessoa("115-256-874-45", null, new String[] {"Java"});
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException pe) {
	}
	}
	
	@Test
	void testCadastraPessoaHabilidadeInvadida() {
		try {
			this.facade.cadastrarPessoa("115-256-874-45", "João", null);
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException pe) {
	}
	}
	
	@Test
	void testExibirPessoa() {
		assertEquals("Marcelo - 111.111.111-12\n"
				+ "- java", this.facade.exibirPessoa("111.111.111-12"));
	}
	
	@Test
	void testExibirPessoaInexistente() {
		try {
			this.facade.exibirPessoa("000-000-000-00");
			fail("Era esperado uma excessão aqui");
		} catch (NoSuchElementException ae) {
	}
	}
	
	@Test
	void testRepresentacaoHabilidadeVazia() {
		assertEquals("Thomas - 111.111.111-10", this.facade.exibirPessoa("111.111.111-10"));
	}
	
	@Test
	public void testAlterarNomePessoa() {
		this.facade.alterarNomePessoa("111.111.111-12", "Gregório");
		assertEquals("Gregório - 111.111.111-12\n"
				+ "- java", this.facade.exibirPessoa("111.111.111-12"));
	}
	
	@Test 
	void testAlterarNomePessoaInvalido() {
		try {
			this.facade.alterarNomePessoa("111.111.111-12", "");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
	}
		try {
			this.facade.alterarNomePessoa("111.111.111-12", null);;
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException pe) {
	}
	}
	
	@Test 
	void alterarHabilidadesPessoa() {
		this.facade.alterarHabilidadesPessoa("111.111.111-12", new String[] {""});
		assertEquals("Marcelo - 111.111.111-12", this.facade.exibirPessoa("111.111.111-12"));
	}
	
	@Test
	void testRemoverPessoa() {
		this.facade.removerPessoa("111.111.111-10");
		try {
			this.facade.exibirPessoa("111.111.111-10");
			fail("Era esperado uma excessão aqui");
		} catch (NoSuchElementException ae) {
	}

	}
	
	@Test
	void testRemoverPessoaInexistente() {
		try {
			this.facade.exibirPessoa("999.111.111-10");
			fail("Era esperado uma excessão aqui");
		} catch (NoSuchElementException ae) {
	}
	}
	
	@Test
	void testAdicionarComentario() {
		this.facade.adicionarComentarioPessoa("111.111.111-12", "Expert", "111.111.111-10");
		assertEquals("Marcelo - 111.111.111-12\n"
				+ "Comentários:\n"
				+ "--Expert (Marcelo)", this.facade.listarComentariosPessoa("111.111.111-12"));
	}
	
	@Test
	void testAdicionarComentarioCpfInvalido(){
		try {
			this.facade.adicionarComentarioPessoa("", "Expert", "111.111.111-10");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
	}
		try {
			this.facade.adicionarComentarioPessoa("111.111.111-12", "Expert", "");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
	}
		
		try {
			this.facade.adicionarComentarioPessoa(null, "Expert", "");
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException pe) {
	}
		try {
			this.facade.adicionarComentarioPessoa(null, "Expert", null);
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException pe) {
	}
	}
	
	@Test
	void testAdicionarComentarioComentarioInvalido() {
		try {
			this.facade.adicionarComentarioPessoa("111.111.111-12", "", "111.111.111-10");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
	}
		try {
			this.facade.adicionarComentarioPessoa("111.111.111-12", null, "111.111.111-10");
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException pe) {
	}
	}
	
	@Test 
	void testListarComentarios() {
		this.facade.adicionarComentarioPessoa("111.111.111-12", "Expert", "111.111.111-10");
		this.facade.adicionarComentarioPessoa("111.111.111-12", "Expert em python", "111.111.111-10");
		assertEquals("Marcelo - 111.111.111-12\n"
				+ "Comentários:\n"
				+ "--Expert (Marcelo)--Expert em python (Marcelo)", this.facade.listarComentariosPessoa("111.111.111-12"));
	}
	
	@Test 
	void testListarComentariosCpfInvalido() {
		try {
			this.facade.listarComentariosPessoa("");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
	}
		try {
			this.facade.listarComentariosPessoa(null);
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException pe) {
	}
	}

}
