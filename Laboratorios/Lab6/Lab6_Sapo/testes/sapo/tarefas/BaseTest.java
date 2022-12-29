package sapo.tarefas;

import org.junit.jupiter.api.BeforeEach;

import sapo.Facade;

/**
 * Base para os testes
 * 
 * @author francisco
 */
public class BaseTest {
	protected Facade facade;
	
	@BeforeEach
	public void inicial() {
		facade = new Facade();
		facade.cadastrarPessoa("1111111", "Francisco", new String[] {"sup"});
		facade.adicionarAtividade("SPD", "jogar lol", "1111111");
		facade.cadastrarTarefa("SPD-0", "jogar", new String[] {"jg", "mid", "top", "sup", "adc"});
		facade.cadastrarTarefaGerencial("SPD-0", "Cblol", new String[] {"coach"} , new String[] {"SPD-0-0"});
		facade.cadastrarTarefaGerencial("SPD-0", "Campeonato interno", new String[] {"Financeiro"}, new String[] {"SPD-0-0"});
	}
}
