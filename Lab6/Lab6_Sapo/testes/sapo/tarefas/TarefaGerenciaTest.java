package sapo.tarefas;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Teste de TarefaGerencial
 * @author francisco
 *
 */
public class TarefaGerenciaTest extends BaseTest{
	
	@Test
	void testCriaTarefaGerencia() {
		super.facade.cadastrarTarefaGerencial("SPD-0", "Campeonato interno", new String[] {"Financeiro"}, new String[] {"SPD-0-0"});
		assertEquals("Campeonato interno - SPD-0-3\n"
				   + "Gestão, Financeiro\n"
				   + "(0 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   + "\n"
				   + "===\n"
				   + "Tarefas:\n", super.facade.exibirTarefa("SPD-0-3"));
	}
	
	@Test
	void testCriarTarefaComErro() {
		try {
			super.facade.cadastrarTarefaGerencial("SPD-0", "", new String[] {"Financeiro"}, new String[] {"SPD-0-0"});
	
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		
		try {
			super.facade.cadastrarTarefaGerencial("", "Campeonato interno", new String[] {"Financeiro"}, new String[] {"SPD-0-0"});
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		
		try {
			super.facade.cadastrarTarefaGerencial("SPD-0", "Campeonato interno", new String[] {"Financeiro"}, new String[] {""});
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		
		try {
			super.facade.cadastrarTarefaGerencial(null, "Campeonato interno", new String[] {"Financeiro"}, new String[] {"SPD-0-0"});
			fail("Era esperado uma excessão aqui");
		} catch(NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
		try {
			super.facade.cadastrarTarefaGerencial("SPD-0", null, new String[] {"Financeiro"}, new String[] {"SPD-0-0"});
			fail("Era esperado uma excessão aqui");
		} catch(NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
		try {
			super.facade.cadastrarTarefaGerencial("SPD-0", "Campeonato interno", new String[] {"Financeiro"}, null);
			fail("Era esperado uma excessão aqui");
		} catch(NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	void testCadastraTarefaComTarefaInexistente() {
		try {
			super.facade.cadastrarTarefaGerencial("SPD-0", "Campeonato interno", new String[] {"Financeiro"}, new String[] {"SPD-0-6"});
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException e) {
			assertEquals("Tarefa não existe", e.getMessage());
		}
	}
	
	@Test
	void testCadastraTarefaComAtividadeErrada() {
		try {
			super.facade.cadastrarTarefaGerencial("SPD-1", "Campeonato interno", new String[] {"Financeiro"}, new String[] {"SPD-0-0"});
			fail("Era esperado uma excessão aqui");
		} catch(NullPointerException e) {
			assertEquals("Atividade não existe", e.getMessage());
		}
	}
	
	@Test
	void testAdicionaTarefaAGerencial() {
		super.facade.cadastrarTarefa("SPD-0", "captar patrocinador", new String[] {"coragem"});
		super.facade.adicionarNaTarefaGerencial("SPD-0-2", "SPD-0-3");
		assertEquals("Campeonato interno - SPD-0-2\n"
				   + "Gestão, Financeiro\n"
				   + "(0 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   + "\n"
				   + "===\n"
				   + "Tarefas:\n"
				   + "captar patrocinador - SPD-0-3", super.facade.exibirTarefa("SPD-0-2"));
	}
	
	@Test
	void testAdicionaTarefaConcluida() {
		super.facade.concluirTarefa("SPD-0-0");
		try {
			super.facade.adicionarNaTarefaGerencial("SPD-0-2", "SPD-0-0");
			fail("Era esperado uma excessão aqui");
		} catch(IllegalStateException e) {
			assertEquals("Tarefa já concluída, não pode ser adicionada", e.getMessage());
		}
	}
	
	@Test
	void testAdicionaTarefaGerencialIdErro() {
		try {
			super.facade.adicionarNaTarefaGerencial("SPD-0-2", "SPD-0-10");
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException e) {
			assertEquals("Tarefa não existe", e.getMessage());
		}
		
		try {
			super.facade.adicionarNaTarefaGerencial("SPD-0-2", "");
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		
		try {
			super.facade.adicionarNaTarefaGerencial("SPD-0-2", "SPD-0-10");
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException e) {
			assertEquals("Tarefa não existe", e.getMessage());
		}
	}
	
	@Test
	void testAdicionaTarefaGerencialAUmaGerenciaigual() {
		try{
			super.facade.adicionarNaTarefaGerencial("SPD-0-2", "SPD-0-2");
			fail("Era esperado uma excessão aqui");
		}catch(IllegalStateException e) {
			assertEquals("Não se pode adicionar a mesma tarefa", e.getMessage());
		}
	}
	
	@Test
	void testAdicionaTarefaGerencialLoop() {
		try{
			super.facade.cadastrarTarefaGerencial("SPD-0", "Campeonato interno", new String[] {"Financeiro"}, new String[] {"SPD-0-0"});
			super.facade.adicionarNaTarefaGerencial("SPD-0-2", "SPD-0-3");
			super.facade.adicionarNaTarefaGerencial("SPD-0-3", "SPD-0-2");
			fail("Era esperado uma excessão aqui");
		}catch(IllegalStateException e) {
			assertEquals("Não se pode adicionar a mesma tarefa", e.getMessage());
		}
	}
	
	@Test
	void testRemoveTarefa() {
		super.facade.cadastrarTarefa("SPD-0", "captar patrocinador", new String[] {"coragem"});
		super.facade.adicionarNaTarefaGerencial("SPD-0-2", "SPD-0-3");
		assertEquals("Campeonato interno - SPD-0-2\n"
				   + "Gestão, Financeiro\n"
				   + "(0 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   + "\n"
				   + "===\n"
				   + "Tarefas:\n"
				   + "captar patrocinador - SPD-0-3", super.facade.exibirTarefa("SPD-0-2"));
		super.facade.removerDaTarefaGerencial("SPD-0-2", "SPD-0-3");
		assertEquals("Campeonato interno - SPD-0-2\n"
				   + "Gestão, Financeiro\n"
				   + "(0 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   + "\n"
				   + "===\n"
				   + "Tarefas:\n", super.facade.exibirTarefa("SPD-0-2"));
	}
	
	@Test
	void testAlteraNome(){
		this.facade.alterarNomeTarefa("SPD-0-2", "clash");
		assertEquals("clash - SPD-0-2\n"
				   + "Gestão, Financeiro\n"
				   + "(0 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   + "\n"
				   + "===\n"
				   + "Tarefas:\n", this.facade.exibirTarefa("SPD-0-2"));
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
	void testAdicionarHorasTarefa() {
		this.facade.adicionarHorasTarefa("SPD-0-2", 10);
		assertEquals("Campeonato interno - SPD-0-2\n"
				+ "Gestão, Financeiro\n"
				+ "(10 hora(s) executada(s))\n"
				+ "===\n"
				+ "Equipe:\n"
				+ "\n"
				+ "===\n"
				+ "Tarefas:\n", this.facade.exibirTarefa("SPD-0-2"));
	}
	
	@Test
	void testAdicionarHorasTarefaHorasInvalidas() {
		try {
			this.facade.adicionarHorasTarefa("SPD-0-2", -1);
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Horas Invalidas", e.getMessage());
		}
	}
	
	@Test
	void testAdicionarHorasTarefaConcluida() {
		this.facade.concluirTarefa("SPD-0-2");
		try {
			this.facade.adicionarHorasTarefa("SPD-0-2", 1);
			fail("Era esperado uma excessão aqui");
		}catch(IllegalStateException e) {
			assertEquals("A tarefa está concluida. Suas horas não podem ser alteradas", e.getMessage());
		}
	}
	
	@Test
	void testRemoveHoras() {
		this.facade.adicionarHorasTarefa("SPD-0-2", 3);
		this.facade.removerHorasTarefa("SPD-0-2", 1);
		assertEquals("Campeonato interno - SPD-0-2\n"
				+ "Gestão, Financeiro\n"
				+ "(2 hora(s) executada(s))\n"
				+ "===\n"
				+ "Equipe:\n"
				+ "\n"
				+ "===\n"
				+ "Tarefas:\n", this.facade.exibirTarefa("SPD-0-2"));
	}
	
	@Test
	void testRemoveHorasZeradas() {
		try {
			this.facade.removerHorasTarefa("SPD-0-2", 1);
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Quantidades de Horas Invalidas", e.getMessage());
		}
	}
	
	@Test
	void testRemoveHorasInvalido() {
		try {
			this.facade.removerHorasTarefa("SPD-0-2", -1);
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Horas Invalidas", e.getMessage());
		}
	}
	
	@Test
	void testRemoverHorasTarefaConcluida() {
		this.facade.adicionarHorasTarefa("SPD-0-2", 3);
		this.facade.concluirTarefa("SPD-0-2");
		try {
			this.facade.removerHorasTarefa("SPD-0-2", 1);
			fail("Era esperado uma excessão aqui");
		}catch(IllegalStateException e) {
			assertEquals("A tarefa está concluida. Suas horas não podem ser alteradas", e.getMessage());
		}
	}
	
	@Test
	void testAssociarPessoa() {
		this.facade.associarPessoaTarefa("1111111", "SPD-0-2");
		assertEquals("Campeonato interno - SPD-0-2\n"
				+ "Gestão, Financeiro\n"
				+ "(0 hora(s) executada(s))\n"
				+ "===\n"
				+ "Equipe:\n"
				+ "Francisco - 1111111\n"
				+ "===\n"
				+ "Tarefas:\n", this.facade.exibirTarefa("SPD-0-2"));
	}
	
	@Test
	void testAssociarPessoaInvalido() {
		try {
			this.facade.associarPessoaTarefa("1111", "SPD-0-2");
			fail("Era esperado uma excessão aqui");
		}catch(NoSuchElementException e) {
			assertEquals("Pessoa não existe", e.getMessage());
		}
		try {
			this.facade.associarPessoaTarefa("", "SPD-0-2");
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException  e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		try {
			this.facade.associarPessoaTarefa(null, "SPD-0-2");
			fail("Era esperado uma excessão aqui");
		}catch(NullPointerException  e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	void testRemoverPessoa() {
		this.facade.associarPessoaTarefa("1111111", "SPD-0-2");
		assertEquals("Campeonato interno - SPD-0-2\n"
				+ "Gestão, Financeiro\n"
				+ "(0 hora(s) executada(s))\n"
				+ "===\n"
				+ "Equipe:\n"
				+ "Francisco - 1111111\n"
				+ "===\n"
				+ "Tarefas:\n", this.facade.exibirTarefa("SPD-0-2"));
		this.facade.removerPessoaTarefa("1111111", "SPD-0-2");
		assertEquals("Campeonato interno - SPD-0-2\n"
				+ "Gestão, Financeiro\n"
				+ "(0 hora(s) executada(s))\n"
				+ "===\n"
				+ "Equipe:\n"
				+ "\n"
				+ "===\n"
				+ "Tarefas:\n", this.facade.exibirTarefa("SPD-0-2"));
	}
	
	@Test
	void testRemovePessoaNaoAdicionada() {
		try {
			this.facade.removerPessoaTarefa("1111111", "SPD-0-2");
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Não pode remover uma pessoa não associada", e.getMessage());
		}
	}
	
	@Test
	void testRemovePessoaInvalido() {
		try {
			this.facade.removerPessoaTarefa("", "SPD-0-2");
			fail("Era esperado uma excessão aqui");
		}catch(IllegalArgumentException e) {
			assertEquals("Conteudo não pode ser vazio", e.getMessage());
		}
		try {
			this.facade.removerPessoaTarefa("11121", "SPD-0-2");
			fail("Era esperado uma excessão aqui");
		}catch(NoSuchElementException e) {
			assertEquals("Pessoa não existe", e.getMessage());
		}
		try {
			this.facade.removerPessoaTarefa(null , "SPD-0-2");
			fail("Era esperado uma excessão aqui");
		}catch(NullPointerException e) {
			assertEquals("Conteudo não pode ser nulo", e.getMessage());
		}
	}
	
	@Test
	void testRemoveTarefaInexistente() {
		try {
			super.facade.removerDaTarefaGerencial("SPD-0-2", "SPD-0-20");
			fail("Era esperado uma excessão aqui");
		} catch(NullPointerException e) {
			assertEquals("Tarefa não existe", e.getMessage());}
	}
	
	@Test
	void testExibe() {
		super.facade.adicionarNaTarefaGerencial("SPD-0-2", "SPD-0-1");
		super.facade.adicionarNaTarefaGerencial("SPD-0-2", "SPD-0-0");
		assertEquals("Campeonato interno - SPD-0-2\n"
				   + "Gestão, Financeiro\n"
				   + "(0 hora(s) executada(s))\n"
				   + "===\n"
				   + "Equipe:\n"
				   + "\n"
				   + "===\n"
				   + "Tarefas:\n"
				   + "jogar - SPD-0-0\n"
				   + "Cblol - SPD-0-1", super.facade.exibirTarefa("SPD-0-2"));
	}
	
	@Test
	void testContarTodasTarefas() {
		super.facade.cadastrarTarefa("SPD-0", "falar no discord", new String[] {"falar"});
		super.facade.adicionarNaTarefaGerencial("SPD-0-2", "SPD-0-3");
		super.facade.adicionarNaTarefaGerencial("SPD-0-2", "SPD-0-1");
		super.facade.adicionarNaTarefaGerencial("SPD-0-1", "SPD-0-0");
		super.facade.adicionarNaTarefaGerencial("SPD-0-1", "SPD-0-3");
		assertEquals(3, super.facade.contarTodasTarefasNaTarefaGerencial("SPD-0-2"));
	}
	
	@Test
	void testConcluirTarefa() {
		super.facade.adicionarNaTarefaGerencial("SPD-0-2", "SPD-0-0");
		super.facade.concluirTarefa("SPD-0-2");
		try {
			this.facade.adicionarHorasTarefa("SPD-0-2", 1);
			fail("Era esperado uma excessão aqui");
		}catch(IllegalStateException e) {
			assertEquals("A tarefa está concluida. Suas horas não podem ser alteradas", e.getMessage());
		}
		try {
			this.facade.adicionarHorasTarefa("SPD-0-0", 1);
			fail("Era esperado uma excessão aqui");
		}catch(IllegalStateException e) {
			assertEquals("A tarefa está concluida. Suas horas não podem ser alteradas", e.getMessage());
		}
	}
	
	@Test
	void testRemoverGerencial() {
		super.facade.adicionarNaTarefaGerencial("SPD-0-2", "SPD-0-0");
		super.facade.removerTarefa("SPD-0-2");
		assertEquals("jogar - SPD-0-0\n"
				+ "- SPD\n"
				+ "jg, mid, top, sup, adc\n"
				+ "(0 hora(s) executada(s))\n"
				+ "===\n"
				+ "Equipe:\n", super.facade.exibirTarefa("SPD-0-0"));
	}
	
	@Test
	void testNaoEGerencial() {
		try {
			super.facade.adicionarNaTarefaGerencial("SPD-0-0", "SPD-0-2");
			fail("Era esperado uma excessão aqui");
		} catch(IllegalStateException e) {
			assertEquals("Não pode fazer porque não é gerencial", e.getMessage());
		}
		try {
			super.facade.removerDaTarefaGerencial("SPD-0-0", "SPD-0-2");
			fail("Era esperado uma excessão aqui");
		} catch(IllegalStateException e) {
			assertEquals("Não pode fazer porque não é gerencial", e.getMessage());
		}
		try {
			super.facade.contarTodasTarefasNaTarefaGerencial("SPD-0-0");
			fail("Era esperado uma excessão aqui");
		} catch(IllegalStateException e) {
			assertEquals("Não pode fazer porque não é gerencial", e.getMessage());
		}
	}
}
