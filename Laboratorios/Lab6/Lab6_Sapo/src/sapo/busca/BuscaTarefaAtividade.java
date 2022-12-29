package sapo.busca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import sapo.atividades.Atividade;
import sapo.tarefas.Tarefa;
import sapo.tarefas.TarefaInterface;

/**
 * BuscaTarefaAtividade representa uma busca de tarefas em uma atividade, pegando os
 * termos buscados e realizando a busca em uma atividade.
 * 
 * @author franciscodantas
 *
 */
public class BuscaTarefaAtividade implements BuscaInterface {
	
	/**
	 * termos - termos a serem procurados na tarefa.
	 * atv - Atividade que será usado.
	 * resultado - resultado encontrado depois da busca.
	 */
	private String[] termos;
	private Atividade atv;
	private String[] resultado;
	
	/**
	 * Cria uma nova busca de tarefas limitada a uma atividade.
	 * 
	 * @param nome Nome de tarefas a serem procurados.
	 * @param atividade Atividade que será usado
	 */
	public BuscaTarefaAtividade(String nome, Atividade atividade) {
		this.termos = nome.split(" ");
		this.atv = atividade;
	}

	@Override
	/**
	 * Realiza a busca a partir da consulta a uma Atividade.
	 * 
	 * Na atividade é realizado a busca e retorna os resultados sem tratamento.
	 * 
	 * @return Array com nomes das tarefas.
	 */
	public String[] busca() {
		Set<TarefaInterface> tarefasEncontras = this.atv.buscar(termos);
		this.resultado = ordena(tarefasEncontras);
		return this.resultado;
	}
	
	/**
	 * Ordena as tarefas encontradas pela sua ordem de inserção e pega seus nomes para
	 * ser o resultado da busca.
	 * 
	 * @param tarefasEncontras Lista com as tarefasEncontradas.
	 * @return Array com nome ordenados.
	 */
	private String[] ordena(Set<TarefaInterface> tarefasEncontras) {
		Tarefa[] tarefas = tarefasEncontras.toArray(new Tarefa[tarefasEncontras.size()]);
		Arrays.sort(tarefas);
		String[] nomes = new String[tarefas.length];
		for(int i = 0; i < tarefas.length; i++) {
			nomes[i] = tarefas[i].toString();
		}
		return nomes;
	}
	
	@Override
	/**
	 * Cria uma exibição com o primeiro elemento do array sendo o tipo e os demais
	 * elementos sendo os resultados.
	 * 
	 * [Tipo], [resultado1], ...
	 * 
	 * @return Array que representa a busca.
	 */
	public String[] exibeBusca(){
		List<String> exibicao = new ArrayList<>();
		exibicao.add("TAREFA");
		for(String resultado: this.resultado) {
			exibicao.add(resultado);
		}
		return exibicao.toArray(new String[exibicao.size()]);
	}

}
