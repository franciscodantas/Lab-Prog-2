package sapo.atividades;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Classe responsavel por testar as funcionalidades de atividades.
 * 
 * @author Thayane Barros - 121110604
 *
 */
class AtividadeControllerTest extends BaseTestAtividade {
	
	@Test
	void testCadastraAtividade() {
		String resul = this.atividade1.cadastrarAtividade("Estudar OO", "Atividade de estudo de OO", "111.111.111-11");
		String saida = "STD-0";
		assertEquals(resul, saida);	
	}
	
	@Test
	void testCadastraSegundaAtividade() {
		testCadastraAtividade();
		String resul = this.atividade1.cadastrarAtividade("Revisar encapsulamento", "Revisando encapsulamento", 
				"111.111.000-01");
		String saida = "RVS-1";
		assertEquals(resul, saida);	
	}
	
	@Test
	void testCadastraAtividadeComNomeRepetido() {
		testCadastraAtividade();
		String resul = this.atividade1.cadastrarAtividade("Estudar OO", "Atividade de estudo de OO, "
				+ "considerando alunos com experiência de programação e uso da linguagem Java.", 
				"111.111.111-11");
		String saida = "STD-1";
		assertEquals(resul, saida);
	}
	
	@Test
	void testCadastraAtividadeComNomeComAusenciaDeConsoante() {
		String resul = this.atividade1.cadastrarAtividade("Code AOE", "Estudar Code", 
				"111.111.000-02");
		String saida = "CDX-0";
		assertEquals(resul, saida);	
	}
	
	@Test
	void testCadastraAtividadeComNomeVazio() {
		try {
			this.atividade1.cadastrarAtividade("", "Estudar Code", "111.111.000-02");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
			
		}
	}
	
	@Test
	void testCadastraAtividadeComNomeNulo() {
		try {
			this.atividade1.cadastrarAtividade(null, "Estudar Code", "111.111.000-02");
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}
	}
	
	@Test
	void testCadastraAtividadeComDescricaoVazia() {
		try {
			this.atividade1.cadastrarAtividade("Code AOE", "", "111.111.000-02");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
			
		}
	}
	
	@Test
	void testCadastraAtividadeComDescricaoNulo() {
		try {
			this.atividade1.cadastrarAtividade("Code AOE", null, "111.111.000-02");
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}
	}
	
	@Test
	void testCadastraAtividadeComResponsavelVazio() {
		try {
			this.atividade1.cadastrarAtividade("Code AOE", "Estudar Code", "");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
			
		}
	}
	
	@Test
	void testCadastraAtividadeComResponsavelNulo() {
		try {
			this.atividade1.cadastrarAtividade("Code AOE", "Estudar Code", null);
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}
	}
	
	@Test
	void testCadastraAtividadeComResponsavelInvalido() {
		try {
			this.atividade1.cadastrarAtividade("Code AOE", "Estudar Code", "000.000.000-00");
			fail("Era esperado uma excessão aqui");
		} catch (NoSuchElementException ae) {
			
		}
	}
	
	@Test
	void testEncerrarAtividade() {
		this.atividade2.encerrarAtividade("STD-0");
	}
	
	@Test
	void testEncerrarAtividadeFechada() {
		testEncerrarAtividade();
		try {
			this.atividade2.encerrarAtividade("STD-0");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalStateException se) {
			
		}	
	}
	
	@Test
	void testEncerrarAtividadeComIdVazio() {
		try {
			this.atividade2.encerrarAtividade("");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
			
		}	
	}
	
	@Test
	void testEncerrarAtividadeComIdInvalido() {
		try {
			this.atividade2.encerrarAtividade("TST-0");
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}	
	}
	
	@Test
	void testEncerrarAtividadeComIdNulo() {
		try {
			this.atividade2.encerrarAtividade(null);
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}	
	}
	
	@Test
	void testEncerrarAtividadeComTarefasPendentes() {
		this.atividade2.cadastrarTarefa("STD-0", "Preparar material de estudo", new String[] {"Java"});
		try {
			this.atividade2.encerrarAtividade("STD-0");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalStateException se) {
			
		}	
	}
	
	@Test
	void testDesativarAtividade() {
		this.atividade2.desativarAtividade("STD-0");
	}
	
	@Test
	void testDesativarAtividadeFechada() {
		testDesativarAtividade();
		try {
			this.atividade2.desativarAtividade("STD-0");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalStateException se) {
			
		}	
	}
	
	@Test
	void testDesativarAtividadeComIdVazio() {
		try {
			this.atividade2.desativarAtividade("");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
			
		}	
	}
	
	@Test
	void testDesativarAtividadeComIdInvalido() {
		try {
			this.atividade2.desativarAtividade("TST-0");
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}	
	}
	
	@Test
	void testDesativarAtividadeComIdNulo() {
		try {
			this.atividade2.desativarAtividade(null);
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}	
	}
	
	@Test
	void testDesativarAtividadeComTarefasPendentes() {
		this.atividade2.cadastrarTarefa("STD-0", "Preparar material de estudo", new String[] {"Java"});
		try {
			this.atividade2.desativarAtividade("STD-0");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalStateException se) {
			
		}	
	}
	
	@Test
	void testReabrirAtividadeEncerrada() {
		testEncerrarAtividade();
		this.atividade2.reabrirAtividade("STD-0");
	}
	
	@Test
	void testReabrirAtividadeDesativada() {
		testDesativarAtividade();
		this.atividade2.reabrirAtividade("STD-0");
	}
	
	@Test
	void testReabrirAtividadeAberta() {
		try {
			this.atividade2.reabrirAtividade("STD-0");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalStateException se) {
			
		}	
	}
	
	@Test
	void testReabrirAtividadeInvalida() {
		try {
			this.atividade2.reabrirAtividade("CDX-0");
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}	
	}
	
	@Test
	void testReabrirAtividadeComIdNulo() {
		try {
			this.atividade2.reabrirAtividade(null);
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}	
	}
	
	@Test
	void testCadastraTarefaEmEncerrada() {
		testEncerrarAtividade();
		try {
			this.atividade2.cadastrarTarefa("STD-0", "Preparar material de estudo", new String[] {"Java"});
			fail("Era esperado uma excessão aqui");
		} catch (IllegalStateException se) {
			
		}	
	}
	
	@Test
	void testCadastraTarefaEmDesativada() {
		testDesativarAtividade();
		try {
			this.atividade2.cadastrarTarefa("STD-0", "Preparar material de estudo", new String[] {"Java"});
			fail("Era esperado uma excessão aqui");
		} catch (IllegalStateException se) {
			
		}	
	}
	
	@Test
	void testExibirAtividade() {
		this.atividade2.cadastrarTarefa("STD-0", "Preparar material de estudo", new String[] {"Java"});
		String resul = this.atividade2.exibirAtividade("STD-0");
		String saida = "STD-0:Estudar OO\nResponsável: Francisco-111.111.111-11"
				+ "\n===\nEstudar Code\n===\nTarefas: 0/1\n"
				+ "- Preparar material de estudo - STD-0-0";
		assertEquals(resul, saida);
	}
	
	@Test
	void testExibirAtividadeComIdVazio() {
		try {
			this.atividade2.exibirAtividade("");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
			
		}	
	}
	
	@Test
	void testExibirAtividadeComIdNulo() {
		try {
			this.atividade2.exibirAtividade(null);
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}	
	}
	
	@Test
	void testExibirAtividadeComIdInvalido() {
		try {
			this.atividade2.exibirAtividade("TST-0");
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}	
	}
	
	@Test
	void testExibirAtividadeComTarefasPendentes() {
		String resul = this.atividade3.exibirAtividade("STD-0");
		String saida = "STD-0:Estudar OO\nResponsável: Matheus-111.111.111-00"
				+ "\n===\nEstudar Code\n===\nTarefas: 0/5\n"
				+ "- NOME5 - STD-0-4\n- NOME4 - STD-0-3\n- NOME3 - STD-0-2";
		assertEquals(resul, saida);
	}
	
	@Test
	void testExibirAtividadeComTarefasPendentesEConcluidas() {
		this.atividade3.concluirTarefa("STD-0-3");
		String resul = this.atividade3.exibirAtividade("STD-0");
		String saida = "STD-0:Estudar OO\nResponsável: Matheus-111.111.111-00"
				+ "\n===\nEstudar Code\n===\nTarefas: 1/5\n"
				+ "- NOME5 - STD-0-4\n- NOME3 - STD-0-2\n- NOME2 - STD-0-1";
		assertEquals(resul, saida);
	}
	
	@Test
	void testAlterarDescricaoDaAtividade() {
		this.atividade2.alterarDescricaoAtividade("STD-0", "Atividade de estudo de OO");
		
		String resul = this.atividade2.exibirAtividade("STD-0");
		String saida = "STD-0:Estudar OO\nResponsável: Francisco-111.111.111-11"
				+ "\n===\nAtividade de estudo de OO\n===\nTarefas: 0/0\n";
		assertEquals(resul, saida);
	}
	
	@Test
	void testAlterarDescricaoDaAtividadeComIdVazio() {
		try {
			this.atividade2.alterarDescricaoAtividade("", "Atividade de estudo de OO");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
			
		}	
	}
	
	@Test
	void testAlterarDescricaoDaAtividadeComIdNulo() {
		try {
			this.atividade2.alterarDescricaoAtividade(null, "Atividade de estudo de OO");
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}	
	}
	
	@Test
	void testAlterarDescricaoDaAtividadeComIdInvalido() {
		try {
			this.atividade2.alterarDescricaoAtividade("TST-0", "Atividade de estudo de OO");
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}	
	}
	
	@Test
	void testAlterarDescricaoDaAtividadeComDescricaoVazio() {
		try {
			this.atividade2.alterarDescricaoAtividade("CDX-0", "");
			fail("Era esperado uma excessão aqui");
		} catch (IllegalArgumentException ae) {
			
		}	
	}
	
	@Test
	void testAlterarDescricaoDaAtividadeComDescricaoNulo() {
		try {
			this.atividade2.alterarDescricaoAtividade("CDX-0", null);
			fail("Era esperado uma excessão aqui");
		} catch (NullPointerException npe) {
			
		}	
	}
	
	@Test
	void testAlterarResponsavelDaAtividade() {
		this.atividade2.alterarResponsavelAtividade("STD-0", "111.111.111-12");
		String resul = this.atividade2.exibirAtividade("STD-0");
		String saida = "STD-0:Estudar OO\nResponsável: Marcelo-111.111.111-12"
				+ "\n===\nEstudar Code\n===\nTarefas: 0/0\n";
		assertEquals(resul, saida);
	}
	
	@Test
	void testAlterarResponsavelComCpfInvalido() {
		try {
			this.atividade2.alterarResponsavelAtividade("STD-0", "111.111.111-99");
			fail("Era esperado uma excessão aqui");
		} catch (NoSuchElementException ae) {
			
		}		
	}
}