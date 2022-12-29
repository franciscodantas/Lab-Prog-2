package sapo.pessoas;

import java.util.HashMap;
import java.util.Map;

import sapo.tarefas.TarefaInterface;

/**
 * Classe responsável por representar uma pessoa sem função dentro do Sistema SAPO.
 * 
 * @author Thayane Barros - 121110604
 *
 */
public class SemFuncao implements Funcao {
	
	/**
	 * tarefas - Tarefas em que a pessoa está associada.
	 */
	private Map<String, TarefaInterface> tarefas;
	
	/**
	 * Construtor padrão de SemFunção. Inicializa o mapa de tarefas da pessoa.
	 */
	public SemFuncao() {
		this.tarefas = new HashMap<>();
	}

	/**
	 * Retorna um "" que representa que a pessoa não possui função.
	 */
	@Override
	public String gerarDetalhes() {
		return "";
	}

	/**
	 * Calcula o nivel da pessoa.
	 * 
	 * @param habilidades As habilidades da pessoa.
	 */
	@Override
	public int calculaNivel(String[] habilidades) {
		int tarefaEmAndamento = 0;
		int tarefaFinalizada = 0;
		
		for(TarefaInterface tarefa: this.tarefas.values()) {
			if(tarefa.isEstadoTarefa()) {
				tarefaEmAndamento++;
			} else {
				tarefaFinalizada++;
			}
		}
		
		return (tarefaEmAndamento / 2) + tarefaFinalizada;
	}

	/**
	 * Adiciona uma tarefa a pessoa.
	 * 
	 * @param id O id da tarefa.
	 * @param tarefa A tarefa a ser adicionada.
	 */
	@Override
	public void adicionaTarefa(String id, TarefaInterface tarefa) {
		this.tarefas.put(tarefa.getId(), tarefa);
		
	}
}
