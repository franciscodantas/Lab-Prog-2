package sapo.pessoas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import sapo.tarefas.TarefaInterface;

/**
 * Classe responsável por representar um aluno dentro do Sistema SAPO.
 * 
 * @author Thayane Barros - 121110604
 *
 */
public class Aluno implements Funcao {

	/**
	 * matricula - A matricula do Aluno.
	 * periodo - O periodo do Aluno.
	 * tarefas - Tarefas em que o aluno está associada.
	 */
	private String matricula;
	private int periodo;
	private Map<String, TarefaInterface> tarefas;

	/**
	 * Construtor padrão de Aluno, nele é definido a matricula e o periodo do aluno, que não são nulos nem vazios.
	 * 
	 * @param matr A matricula do aluno.
	 * @param periodo O periodo do Aluno.
	 */
	public Aluno(String matr, int periodo) {
		if(matr.isBlank() || periodo <= 0) {
			throw new IllegalArgumentException("Conteudo Invalido");
		} 
		Objects.requireNonNull(matr, "Matricula não pode ser nulo");
		
		this.matricula = matr;
		this.periodo = periodo;	
		this.tarefas = new HashMap<>();
	}
	
	/**
	 * Retorna uma representação textual dos detalhes de um Aluno, na forma "Aluno - MATRICULA - PERIODO".
	 */
	@Override
	public String gerarDetalhes() {
		return "Aluno - " + this.matricula + " - " + this.periodo + "\n";
	}
	
	/**
	 * Calcula o nivel do aluno.
	 * 
	 * @param habilidades As habilidades da pessoa.
	 */
	@Override
	public int calculaNivel(String[] habilidades) {
		int tarefaEmAndamento = 0;
		ArrayList<TarefaInterface> tarefaFinalizada = new ArrayList<>();
		
		for(TarefaInterface tarefa: this.tarefas.values()) {
			if(tarefa.isEstadoTarefa()) {
				tarefaEmAndamento++;
			} else {
				tarefaFinalizada.add(tarefa);
			}
		}
		int tarefaEmComum = tarefasEmComum(tarefaFinalizada, habilidades);
		return ((int)(tarefaEmAndamento / 2)) + ((int)Math.ceil((1.5 * tarefaEmComum))) + (tarefaFinalizada.size() - tarefaEmComum);
	}
	
	/**
	 * Calcula o número de tarefas onde o aluno têm pelo menos uma habilidade que bate com a habilidade da tarefa. 
	 * 
	 * @param tarefas As tarefas finalizadas do aluno.
	 * @param habilidades As habilidades da pessoa.
	 * @return O número de tarefas em comum.
	 */
	private int tarefasEmComum(List<TarefaInterface> tarefas, String[] habilidades) {
		int habilidadesEmComum = 0;
		
		for(TarefaInterface tarefa: tarefas) {
			String[] habilidadesTarefa = tarefa.getHabilidades();
			boolean contem = false;
			for(int i = 0; i < habilidadesTarefa.length; i++) {
				if(!contem) {
					for(int j = 0; j < habilidades.length; j++) {
						if(habilidadesTarefa[i].equals(habilidades[j])) {
							habilidadesEmComum++;
							contem = true;
							break;
						}
					}
				}
			}
		}
		return habilidadesEmComum;
	}

	/**
	 * Adiciona uma tarefa ao aluno.
	 * 
	 * @param id O id da tarefa.
	 * @param tarefa A tarefa a ser adicionada.
	 */
	@Override
	public void adicionaTarefa(String id, TarefaInterface tarefa) {
		this.tarefas.put(tarefa.getId(), tarefa);
		
	}

}