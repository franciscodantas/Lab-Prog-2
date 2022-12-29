package sapo.busca;

import java.util.Comparator;
import java.util.Map.Entry;

import sapo.tarefas.TarefaInterface;

/**
 * Classe de comparação das sugestões.
 * 
 * @author franciscodantas
 *
 */
public class ComparatorSugestoes implements Comparator<Entry<TarefaInterface, Integer>>{

	@Override
	/**
	 * Compara do maior para o menor numero de habilidades conhecidentes e em caso de
	 * empate da prioridade aquelas que tem menos pessoas e por ultimo ordena pelo id.
	 * 
	 * @return 1 (maior) ou -1 (menor);
	 */
	public int compare(Entry<TarefaInterface, Integer> o1, Entry<TarefaInterface, Integer> o2) {
		Integer comumO1 = o1.getValue();
		Integer comumO2 = o2.getValue();
		if(comumO1 < comumO2) {
			return 1;
		}
		else if(comumO1 == comumO2) {
			if(o1.getKey().quantDePessoas() < o2.getKey().quantDePessoas()) {
				return 1;
			}
			else {
				return -1 * o1.getKey().getId().compareTo(o2.getKey().getId());
			}
		}
		else {
			return -1;
		}
	}

}
