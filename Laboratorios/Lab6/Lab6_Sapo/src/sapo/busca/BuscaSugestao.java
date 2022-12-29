package sapo.busca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sapo.tarefas.TarefaInterface;
import sapo.tarefas.TarefaService;

/**
 * BuscaSugestão representa uma busca de sugestão de tarefas para uma pessoa, 
 * Pega-se as habilidades e retorna uma lista de tarefas que possuem algum tipo de habilidade
 * em comum com a pessoa.
 * 
 * @author franciscodantas
 *
 */
public class BuscaSugestao implements BuscaInterface{
	
	/**
	 * resultado - resultado da pesquisa após a busca.
	 * habilidades - habilidades procuradas.
	 * ts - Tarefaservice que será usado.
	 */
	private String[] resultado;
	private String[] habilidades;
	private TarefaService ts;
	
	/**
	 * Cria uma nova sugestão
	 * 
	 * @param habilidades Habilidades que serão buscadas.
	 * @param ts Tarefaservice que será usado.
	 */
	public BuscaSugestao(String[] habilidades, TarefaService ts) {
		this.habilidades = habilidades;
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
		Map<TarefaInterface, Integer> sugestao = this.ts.sugestao(this.habilidades);
		this.resultado = ordena(sugestao);
		return this.resultado;
	}
	
	/**
	 * Ordena uma lista de sugestões com base nos criterios de ComparatorSugestoes e 
	 * pega a representação das tarefas como resultado.
	 * 
	 * @param sugestao Map com as tarefas.
	 * @return Um array ordenado de representações.
	 */
	private String[] ordena(Map<TarefaInterface, Integer> sugestao) {
		List<Entry<TarefaInterface, Integer>> list = new LinkedList<>(sugestao.entrySet());
		Collections.sort(list, new ComparatorSugestoes());
		String[] nomes = new String[list.size()];
		for(int i = 0; i < nomes.length; i++) {
			nomes[i] = list.get(i).getKey().toString();
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
		exibicao.add("SUGESTÃO");
		for(String resultado: this.resultado) {
			exibicao.add(resultado);
		}
		return exibicao.toArray(new String[exibicao.size()]);
	}


}
