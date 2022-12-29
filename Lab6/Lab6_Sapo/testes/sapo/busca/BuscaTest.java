package sapo.busca;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

/**
 * Classe responsavel por testar as funcionalidades de busca.
 * 
 * @author Thayane Barros - 121110604
 *
 */
class BuscaTest extends BaseBuscaTest{
	
	private String formataParaString(String[] resul){
		String resulEmString = "";
		for(int i = 0; i < resul.length; i ++) {
			resulEmString += resul[i] + "\n";
		}
		return resulEmString;
	}
	
	@Test
	void testBuscaPessoaPorNomeEHabilidade() {
		String[] resul = this.busca1.exibirPessoas("Matheus Java");
		String[] saida = {"Matheus"};
		assertEquals(resul[0], saida[0]);
		}
	
	@Test
	void testBuscaPessoaPorNome() {
		String[] resul = this.busca1.exibirPessoas("João");
		String[] saida = {"João"};
		assertEquals(resul[0], saida[0]);
		}
	
	@Test
	void testBuscaPessoaPorHabilidade() {
		String[] resul = this.busca1.exibirPessoas("Java");
		String resulEmString = formataParaString(resul);
		String saida = "Livia Miranda\nMatheus\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testBuscaPessoaPorCpf() {
		String[] resul = this.busca1.exibirPessoas("111.111.111-11");
		String[] saida = {"Matheus"};
		assertEquals(resul[0], saida[0]);
		}
	
	@Test
	void testBuscaPessoaComTermosNulo() {
		try {
			this.busca1.exibirPessoas(null);
			fail("Era esperado uma excessão aqui");
		} catch(NullPointerException npe) {
			
		}
	}
	
	@Test
	void testBuscaPessoaComTermosVazio() {
		try {
			this.busca1.exibirPessoas("");
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException ae) {
			
		}
	}
	
	@Test
	void testBuscaPessoaPorNomeIdentico() {
		String[] resul = this.busca1.exibirPessoas("Livia");
		String resulEmString = formataParaString(resul);
		String saida = "Livia\nLivia Miranda\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testBuscaPessoaSemResultado() {
		String[] resul = this.busca1.exibirPessoas("Livia 111.111.111-00");
		String resulEmString = formataParaString(resul);
		String saida = "";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testBuscaPessoaCom2Termos() {
		String[] resul = this.busca1.exibirPessoas("Livia Java");
		String resulEmString = formataParaString(resul);
		String saida = "Livia Miranda\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testBuscaPessoaComHabilidadesEmComum() {
		String[] resul = this.busca1.exibirPessoas("Java POO");
		String resulEmString = formataParaString(resul);
		String saida = "Livia Miranda\nMatheus\n";
		assertEquals(resulEmString, saida);
		}
		

	@Test
	void testBuscaAtividadeCom1Resultado() {
		String[] resul = this.busca1.buscarAtividades("RVS encapsulamento");
		String resulEmString = formataParaString(resul);
		String saida = "Revisar o assunto de encapsulamento\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testBuscaAtividadeSemResultado() {
		String[] resul = this.busca1.buscarAtividades("Teste");
		String resulEmString = formataParaString(resul);
		String saida = "";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testBuscaAtividadeComTermosNulo() {
		try {
			this.busca1.buscarAtividades(null);
			fail("Era esperado uma excessão aqui");
		} catch(NullPointerException npe) {
			
		}
	}
	
	@Test
	void testBuscaAtividadeComTermosVazio() {
		try {
			this.busca1.buscarAtividades("");
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException ae) {
			
		}
	}
	
	@Test
	void testBuscaAtividadeComTermoComum() {
		String[] resul = this.busca1.buscarAtividades("Assunto");
		String resulEmString = formataParaString(resul);
		String saida = "Revisar o assunto de encapsulamento\nAtividade de estudo de OO. Revisar Assunto\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testBuscaAtividadeComIdDaAtividade() {
		String[] resul = this.busca1.buscarAtividades("STD-1");
		String resulEmString = formataParaString(resul);
		String saida = "Atividade de estudo de OO. Revisar Assunto\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testBuscaAtividadeCom2TermosMasSo1TermoPresente() {
		String[] resul = this.busca1.buscarAtividades("Revisar prova");
		String resulEmString = formataParaString(resul);
		String saida = "";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testBuscaTarefa() {
		String[] resul = this.busca1.buscarTarefas("Estudar encapsulamento");
		String resulEmString = formataParaString(resul);
		String saida = "Estudar Encapsulamento - RVS-0-0\n- RVS encapsulamento\nPOO"
				+ "\n(0 hora(s) executada(s))\n===\n" + 
				"Equipe:\n\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testBuscaTarefaSemResultado() {
		String[] resul = this.busca1.buscarTarefas("Teste");
		String resulEmString = formataParaString(resul);
		String saida = "";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testBuscaTarefaComNomeVazio() {
		try {
			this.busca1.buscarTarefas("");
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException ae) {
			
		}
		
	}
	
	@Test
	void testBuscaTarefaComNomeNulo() {
		try {
			this.busca1.buscarTarefas(null);
			fail("Era esperado uma excessão aqui");
		} catch(NullPointerException npe) {
			
		}
	}
	
	@Test
	void testBuscaTarefaComIdVazio() {
		try {
			this.busca1.buscarTarefas("", "RVS encapsulamento");
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException ae) {
			
		}
		
	}
	
	@Test
	void testBuscaTarefaComIdNulo() {
		try {
			this.busca1.buscarTarefas(null, "RVS encapsulamento");
			fail("Era esperado uma excessão aqui");
		} catch(NullPointerException npe) {
			
		}
	}
	
	@Test
	void testSugerirTarefas() {
		String[] resul = this.busca1.sugerirTarefas("111.111.111-13");
		String resulEmString = formataParaString(resul);
		String saida = "Estudar Encapsulamento - RVS-0-0\n- RVS encapsulamento\nPOO\n(0 hora(s) executada(s))"
				+ "\n===\nEquipe:\n\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testSugerirTarefasCom2resultados() {
		String[] resul = this.busca1.sugerirTarefas("111.111.111-12");
		String resulEmString = formataParaString(resul);
		String saida = "Fazer atividade - RVS-0-2\n- RVS encapsulamento\nPython\n(0 hora(s) executada(s))"
				+ "\n===\nEquipe:\n\n"
				+ "Revisar Prova - RVS-0-1\n- RVS encapsulamento\nPython\n(0 hora(s) executada(s))"
				+ "\n===\nEquipe:\n\n"
				+ "Estudar Encapsulamento - RVS-0-0\n- RVS encapsulamento\nPOO\n(0 hora(s) executada(s))"
				+ "\n===\nEquipe:\n\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testSugerirTarefasCom1TarefaConcluida() {
		this.busca1.concluirTarefa("RVS-0-1");
		String[] resul = this.busca1.sugerirTarefas("111.111.111-12");
		String resulEmString = formataParaString(resul);
		String saida = "Fazer atividade - RVS-0-2\n- RVS encapsulamento\nPython\n(0 hora(s) executada(s))"
				+ "\n===\nEquipe:\n\n"
				+ "Estudar Encapsulamento - RVS-0-0\n- RVS encapsulamento\nPOO\n(0 hora(s) executada(s))"
				+ "\n===\nEquipe:\n\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testSugerirTarefaSemResultado() {
		String[] resul = this.busca1.sugerirTarefas("111.111.111-14");
		String resulEmString = formataParaString(resul);
		String saida = "";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testSugerirTarefasComCpfVazia() {
		try {
			this.busca1.sugerirTarefas("");
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException ae) {
			
		}	
	}
	
	@Test
	void testSugerirTarefasComCpfInvalido() {
		try {
			this.busca1.sugerirTarefas("000.000.000.99");
			fail("Era esperado uma excessão aqui");
		} catch(NoSuchElementException ae) {
			
		}	
	}
	
	@Test
	void testSugerirTarefasComCpfNulo() {
		try {
			this.busca1.sugerirTarefas(null);
			fail("Era esperado uma excessão aqui");
		} catch(NullPointerException npe) {
			
		}	
	}
	
	@Test
	void testSugerirTarefasComCpfInexistente() {
		try {
			this.busca1.sugerirTarefas("000.000.000-00");
			fail("Era esperado uma excessão aqui");
		} catch(NoSuchElementException ae) {
			
		}	
	}
	
	@Test
	void testBuscasMaisRecentesLimiteInferior() {
		String[] resul = this.busca2.buscarMaisRecentes(1);
		String resulEmString = formataParaString(resul);
		String saida = "TAREFA\n"
				+ "Estudar Encapsulamento - RVS-0-0\n"
				+ "- RVS encapsulamento\n"
				+ "POO\n"
				+ "(0 hora(s) executada(s))\n"
				+ "===\n"
				+ "Equipe:\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testBuscasMaisRecentesLimiteSuperior() {
		String[] resul = this.busca2.buscarMaisRecentes(6);
		String resulEmString = formataParaString(resul);
		String saida = "TAREFA\n"
				+ "Estudar Encapsulamento - RVS-0-0\n"
				+ "- RVS encapsulamento\n"
				+ "POO\n"
				+ "(0 hora(s) executada(s))\n"
				+ "===\n"
				+ "Equipe:\n"
				+ "TAREFA\n"
				+ "Estudar Encapsulamento - RVS-0-0\n"
				+ "- RVS encapsulamento\n"
				+ "POO\n"
				+ "(0 hora(s) executada(s))\n"
				+ "===\n"
				+ "Equipe:\n"
				+ "ATIVIDADE\n"
				+ "Atividade de estudo de OO. Revisar Assunto\n"
				+ "PESSOA\n"
				+ "Livia\n"
				+ "PESSOA\n"
				+ "Livia\n"
				+ "PESSOA\n"
				+ "Livia\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testBuscasMaisRecentesComNumeroNegativo() {
		try {
			this.busca2.buscarMaisRecentes(-1);
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException ae) {
			
		}	
	}
	
	@Test
	void testBuscasMaisRecentesComNumeroZero() {
		try {
			this.busca2.buscarMaisRecentes(0);
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException ae) {
			
		}	
	}
	
	@Test
	void testBuscasMaisRecentesComNumeroAcimaDeLimiteSuperior() {
		try {
			this.busca2.buscarMaisRecentes(7);
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException ae) {
			
		}	
	}
	
	@Test
	void testHistoricoDeBuscaComLimiteInferior() {
		String[] resul = this.busca2.exibirHistoricoBusca(0);
		String resulEmString = formataParaString(resul);
		String saida = "PESSOA\n"
				+ "Livia\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testHistoricoDeBuscaComLimiteSuperior() {
		String[] resul = this.busca2.exibirHistoricoBusca(5);
		String resulEmString = formataParaString(resul);
		String saida = "TAREFA\n"
				+ "Estudar Encapsulamento - RVS-0-0\n- RVS encapsulamento\nPOO\n"
				+ "(0 hora(s) executada(s))\n===\n" 
				+ "Equipe:\n\n";
		assertEquals(resulEmString, saida);
		}
	
	@Test
	void testHistoricoDeBuscaComIndexMaiorLimiteSuperior() {
		try {
			this.busca2.exibirHistoricoBusca(6);
			fail("Era esperado uma excessão aqui");
		} catch(IndexOutOfBoundsException re) {
			
		}	
	}
	
	@Test
	void testHistoricoDeBuscaComIndexNegativo() {
		try {
			this.busca2.exibirHistoricoBusca(-1);
			fail("Era esperado uma excessão aqui");
		} catch(IllegalArgumentException ae) {
			
		}	
	}

}