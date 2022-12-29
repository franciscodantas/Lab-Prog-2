package sapo.pessoas;

import sapo.tarefas.TarefaInterface;

/**
 * Interface para representar todos os tipos de funcao que uma pessoa pode exercer.
 * 
 * @author Thayane Barros - 121110604
 *
 */
public interface Funcao {
	
	/**
	 * Metodo que retorna uma representação textual dos detalhes de uma função.
	 * 
	 * @return Uma representação textual dos detalhes de uma função.
	 */
	String gerarDetalhes();

	/**
	 * Metodo que calcula o nivel de uma função atraves das tarefas que estão associadas a essa funcao
	 * e as habilidades da pessoa.
	 * 
	 * @param habilidades As habilidades da pessoa.
	 * @return O calculo do nivel.
	 */
	int calculaNivel(String[] habilidades);

	/**
	 * Adiciona uma tarefa a uma função.
	 * 
	 * @param id O id da tarefa.
	 * @param tarefa A tarefa a ser adicionada.
	 */
	void adicionaTarefa(String id, TarefaInterface tarefa);

}