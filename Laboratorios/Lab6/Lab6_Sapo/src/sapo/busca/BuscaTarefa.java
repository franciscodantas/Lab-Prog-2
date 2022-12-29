package sapo.busca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import sapo.tarefas.TarefaInterface;
import sapo.tarefas.TarefaService;

/**
 * BuscaTarefa representa uma busca em tarefas, ela pega os elementos que são
 * passados e realiza a busca dentro das tarefas cadastradas.
 * 
 * @author franciscodantas
 *
 */
public class BuscaTarefa implements BuscaInterface {
	
	/**
	 * termos - termos a serem procurados na tarefa.
	 * ts - TarefaService que será usado.
	 * resultado - resultado encontrado depois da busca.
	 */
	private String[] termos;
	private TarefaService ts;
	private String[] resultado;

	/**
	 * Cria uma nova busca de tarefas.
	 * 
	 * @param nome nomes de tarefas que serão pesquisados.
	 * @param ts TarefaService que será usado.
	 */
	public BuscaTarefa(String nome, TarefaService ts) {
		this.termos = nome.split(" ");
		this.ts = ts;
	}

	@Override
	/**
	 * Realiza a busca a partir da consulta ao TarefaService.
	 * 
	 * Em TarefaService é realizado a busca e retorna os resultados sem tratamento.
	 * 
	 * @return Array com nomes das tarefas.
	 */
	public String[] busca() {
		Set<TarefaInterface> tarefasEncontras = this.ts.busca(this.termos);
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
		TarefaInterface[] tarefas = tarefasEncontras.toArray(new TarefaInterface[tarefasEncontras.size()]);
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
