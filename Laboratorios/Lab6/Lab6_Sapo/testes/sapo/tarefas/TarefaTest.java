package sapo.tarefas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Testes de tarefa
 * 
 * @author francisco
 */
class TarefaTest extends BaseTest{
	
	@Test
	void testCriaTarefa() {
		super.facade.cadastrarTarefa("SPD-0", "falar no discord", new String[] {"falar"});
		assertEquals("falar no discord - SPD-0-3\n" +
					 "- SPD\n"
					 + "falar\n"
					 + "(0 hora(s) executada(s))\n"
					 + "===\n"
					 + "Equipe:\n",
					 facade.exibirTarefa("SPD-0-3"));
	}
	
	@Test
	void testCriaTarefaComIdInvalido() {
		try {
			this.facade.cadastrarTarefa("", "Estudar OO", new String[] {"Professor", "Objetos"});
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		try {
			this.facade.cadastrarTarefa(null,"Estudar OO", new String[] {"Professor", "Objetos"});
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
		}
	
	@Test
	void testCriaTarefaComNomeInvalido() {
		try {
			this.facade.cadastrarTarefa("SPD-0", "", new String[] {"Professor", "Objetos"});
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		try {
			this.facade.cadastrarTarefa("STD-0-0", null, new String[] {"Professor", "Objetos"});
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	void testAlteraNome(){
		this.facade.alterarNomeTarefa("SPD-0-0", "clash");
		assertEquals("clash - SPD-0-0\n"
				   + "- SPD\n"
				   + "jg, mid, top, sup, adc\n"
				   + "(0 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   , this.facade.exibirTarefa("SPD-0-0"));
	}
	
	@Test
	void testAlteraNomeIdInvalido() {
		try {
			this.facade.alterarNomeTarefa("", "clash");
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		try {
			this.facade.alterarNomeTarefa(null, "clash");
			fail("Era esperado uma excessão aqui");
		}catch(NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	void testAlteraNomeInvalido() {
		try {
			this.facade.alterarNomeTarefa("SPD-0-0", "");
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		try {
			this.facade.alterarNomeTarefa("SPD-0-0", null);
			fail("Era esperado uma excessão aqui");
		}catch(NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	void AlteraHabilidades() {
		this.facade.alterarHabilidadesTarefa("SPD-0-0", new String[] {"jg"});
		assertEquals("jogar - SPD-0-0\n"
				   + "- SPD\n"
				   + "jg\n"
				   + "(0 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   , this.facade.exibirTarefa("SPD-0-0"));
	}
	
	@Test
	void testAlteraHabilidadeIdInvalido() {
		try {
			this.facade.alterarHabilidadesTarefa("", new String[] {"jg"});
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		try {
			this.facade.alterarHabilidadesTarefa(null, new String[] {"jg"});
			fail("Era esperado uma excessão aqui");
		}catch(NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	void testAdicionarHorasTarefa() {
		this.facade.adicionarHorasTarefa("SPD-0-0", 10);
		assertEquals("jogar - SPD-0-0\n"
				   + "- SPD\n"
				   + "jg, mid, top, sup, adc\n"
				   + "(10 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   , this.facade.exibirTarefa("SPD-0-0"));
	}
	
	@Test
	void testAdicionarHorasTarefaHorasInvalidas() {
		try {
			this.facade.adicionarHorasTarefa("SPD-0-0", -1);
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Horas Invalidas", e.getMessage());
		}
	}
	
	@Test
	void testAdicionarHorasTarefaConcluida() {
		this.facade.concluirTarefa("SPD-0-0");
		try {
			this.facade.adicionarHorasTarefa("SPD-0-0", 1);
			fail("Era esperado uma excessão aqui");
		}catch(IllegalStateException e) {
			assertEquals("A tarefa está concluida. Suas horas não podem ser alteradas", e.getMessage());
		}
	}
	
	@Test
	void testRemoveHoras() {
		this.facade.adicionarHorasTarefa("SPD-0-0", 3);
		this.facade.removerHorasTarefa("SPD-0-0", 1);
		assertEquals("jogar - SPD-0-0\n"
				   + "- SPD\n"
				   + "jg, mid, top, sup, adc\n"
				   + "(2 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   , this.facade.exibirTarefa("SPD-0-0"));
	}
	
	@Test
	void testRemoveHorasZeradas() {
		try {
			this.facade.removerHorasTarefa("SPD-0-0", 1);
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Quantidades de Horas Invalidas", e.getMessage());
		}
	}
	
	@Test
	void testRemoveHorasInvalido() {
		try {
			this.facade.removerHorasTarefa("SPD-0-0", -1);
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Horas Invalidas", e.getMessage());
		}
	}
	
	@Test
	void testRemoverHorasTarefaConcluida() {
		this.facade.adicionarHorasTarefa("SPD-0-0", 3);
		this.facade.concluirTarefa("SPD-0-0");
		try {
			this.facade.removerHorasTarefa("SPD-0-0", 1);
			fail("Era esperado uma excessão aqui");
		}catch(IllegalStateException e) {
			assertEquals("A tarefa está concluida. Suas horas não podem ser alteradas", e.getMessage());
		}
	}
	
	@Test
	void testAssociarPessoa() {
		this.facade.associarPessoaTarefa("1111111", "SPD-0-0");
		assertEquals("jogar - SPD-0-0\n"
				   + "- SPD\n"
				   + "jg, mid, top, sup, adc\n"
				   + "(0 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   + "Francisco - 1111111"
				   , this.facade.exibirTarefa("SPD-0-0"));
	}
	
	@Test
	void testAssociarPessoaInvalido() {
		try {
			this.facade.associarPessoaTarefa("1111", "SPD-0-0");
			fail("Era esperado uma excessão aqui");
		}catch(NoSuchElementException e) {
			assertEquals("Pessoa não existe", e.getMessage());
		}
		try {
			this.facade.associarPessoaTarefa("", "SPD-0-0");
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException  e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		try {
			this.facade.associarPessoaTarefa(null, "SPD-0-0");
			fail("Era esperado uma excessão aqui");
		}catch(NullPointerException  e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	void testRemoverPessoa() {
		this.facade.associarPessoaTarefa("1111111", "SPD-0-0");
		assertEquals("jogar - SPD-0-0\n"
				   + "- SPD\n"
				   + "jg, mid, top, sup, adc\n"
				   + "(0 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   + "Francisco - 1111111"
				   , this.facade.exibirTarefa("SPD-0-0"));
		this.facade.removerPessoaTarefa("1111111", "SPD-0-0");
		assertEquals("jogar - SPD-0-0\n"
				   + "- SPD\n"
				   + "jg, mid, top, sup, adc\n"
				   + "(0 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   , this.facade.exibirTarefa("SPD-0-0"));
	}
	
	@Test
	void testRemovePessoaNaoAdicionada() {
		try {
			this.facade.removerPessoaTarefa("1111111", "SPD-0-0");
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Não pode remover uma pessoa não associada", e.getMessage());
		}
	}
	
	@Test
	void testRemovePessoaInvalido() {
		try {
			this.facade.removerPessoaTarefa("", "SPD-0-0");
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		try {
			this.facade.removerPessoaTarefa("11121", "SPD-0-0");
			fail("Era esperado uma excessão aqui");
		}catch(NoSuchElementException e) {
			assertEquals("Pessoa não existe", e.getMessage());
		}
		try {
			this.facade.removerPessoaTarefa(null , "SPD-0-0");
			fail("Era esperado uma excessão aqui");
		}catch(NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	void testRemoveTarefa() {
		this.facade.removerTarefa("SPD-0-0");
		try {
			this.facade.exibirTarefa("SPD-0-0");
		}catch(IllegalArgumentException e) {
			assertEquals("Tarefa não existe", e.getMessage());
		}
	}
	
	@Test
	void testRemoveTarefaNaoExiste() {
		try {
			this.facade.removerTarefa("SPD-0-100");
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Tarefa não existe", e.getMessage());
		}
	}
	
	@Test
	void exibiTarefa() {
		assertEquals("jogar - SPD-0-0\n"
				   + "- SPD\n"
				   + "jg, mid, top, sup, adc\n"
				   + "(0 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   , this.facade.exibirTarefa("SPD-0-0"));
	}
	
	@Test
	void exibeTarefaNaoExistente() {
		try {
			assertEquals("jogar - SPD-0-0\n"
					   + "- jogar lol\n"
					   + "jg, mid, top, sup, adc\n"
					   + "(0 hora(s) executada(s))\n"
					   + "===\n"
					   + "Equipe:\n"
					   , this.facade.exibirTarefa("SPD-0-3"));
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Tarefa não existe", e.getMessage());
		}
	}
}
