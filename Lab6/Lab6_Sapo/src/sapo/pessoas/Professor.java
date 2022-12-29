package sapo.pessoas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import sapo.tarefas.TarefaInterface;

/**
 * Classe responsável por representar um professor dentro do Sistema SAPO.
 * 
 * @author Thayane Barros - 121110604
 *
 */
public class Professor implements Funcao {

	/**
	 * siape - O siape do professor.
	 * disciplinas - As disciplinas que o professor possui.
	 * tarefas - Tarefas em que o professor está associada.
	 */
	private String siape;
	private String[] disciplinas;
	private Map<String, TarefaInterface> tarefas;

	/**
	 * Construtor padrão de Professor, nele é definido a siape e as disciplinas do professor,
	 * que não são nulos nem vazios.
	 * 
	 * @param siape A siape do professor.
	 * @param disciplinas As disciplinas que o professor possui.
	 */
	public Professor(String siape, String[] disciplinas) {
		if(siape.isBlank()) {
			throw new IllegalArgumentException("Conteudo Invalido");
		}
		Objects.requireNonNull(siape, "Siape não pode ser nulo");
		Objects.requireNonNull(disciplinas, "Disciplinas não pode ser nulo");
		
		this.siape = siape;
		this.disciplinas = disciplinas;
		this.tarefas = new HashMap<>();

	}
	
	/**
	 * Retorna uma representação textual dos detalhes de um professor.
	 */
	@Override
	public String gerarDetalhes() {
		return "Professor - " + this.siape + " - " + listaDisciplinas() + "\n";
	}

	/**
	 * Metodo que retorna uma representação textual das disciplinas do professor.
	 *  
	 * @return Uma representação textual das disciplinas do professor.
	 */
	private String listaDisciplinas() {
		String listagem = "";
		 for(int i = 0; i < this.disciplinas.length; i++) {
			 if(i == this.disciplinas.length - 1 ) {
				 listagem += this.disciplinas[i];
			 } else {
				 listagem += this.disciplinas[i] + ", ";
			 }
		 }
		return listagem.trim();
	}

	/**
	 * Calcula o nivel do professor.
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
		
		return ((int)(tarefaEmAndamento / 4)) + (tarefasEmComum(tarefaFinalizada, habilidades));
	}
	
	/**
	 * Calcula o número de tarefas onde a habilidade do professor ou a disciplina, batem com as habilidades da tarefa.
	 * 
	 * @param tarefaFinalizada As tarefas finalizadas do aluno.
	 * @param habilidades As habilidades da pessoa.
	 * @return O número de tarefas em comum.
	 */
	private int tarefasEmComum(ArrayList<TarefaInterface> tarefaFinalizada, String[] habilidades) {
		int habilidadesEmComum = 0;
		for(TarefaInterface tarefa: tarefaFinalizada) {
			String[] habilidadesTarefa = tarefa.getHabilidades();
			for(int i = 0; i < habilidadesTarefa.length; i++) {
				if(temHabilidadeEmProfessor(habilidadesTarefa[i], habilidades) || temHabilidadesEmDisciplina(habilidadesTarefa[i])) {
					habilidadesEmComum++;
					break;
				}
			}
		}
		return habilidadesEmComum;
	}

	/**
	 * Verifica se uma disciplina possui determinada habilidade.
	 * 
	 * @param habTarefa A habilidade da pessoa a ser procurada.
	 * @return Um booleano indicando se a disciplina possui determinada habilidade ou não.
	 */
	private boolean temHabilidadesEmDisciplina(String habTarefa) {
		for(int i = 0; i < this.disciplinas.length; i++) {
			if(this.disciplinas[i].equals(habTarefa)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Verifica se um professor possui determinada habilidade.
	 * 
	 * @param habTarefa A habilidade da pessoa a ser procurada.
	 * @param habilidades As habilidades da pessoa.
	 * @return Um booleano indicando se a pessoa possui determinada habilidade ou não.
	 */
	private boolean temHabilidadeEmProfessor(String habTarefa, String[] habilidades) {
		for(int i = 0; i < habilidades.length; i++) {
			if(habilidades[i].equals(habTarefa)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adiciona uma tarefa ao professor.
	 * 
	 * @param id O id da tarefa.
	 * @param tarefa A tarefa a ser adicionada.
	 */
	@Override
	public void adicionaTarefa(String id, TarefaInterface tarefa) {
		this.tarefas.put(tarefa.getId(), tarefa);
		
	}
	
}