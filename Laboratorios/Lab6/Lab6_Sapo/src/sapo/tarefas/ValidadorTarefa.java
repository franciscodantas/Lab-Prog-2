package sapo.tarefas;

import java.util.Objects;

import sapo.ValidadorPadrao;

/**
 * Classe de validação de tarefas. As tarefas são válidas se todos os seus termos não forem nulos ou vazios.
 * 
 * @author Thayane Barros - 121110604
 *
 */
public class ValidadorTarefa extends ValidadorPadrao {

	/**
	 * Validação padrão de validação do id e do cpf/nome. O id e o nome/cpf não pode
	 * ser nulo ou vazio.
	 * 
	 * @param id O id a ser validado.
	 * @param nomeCpf O nome ou cpf a ser validado.
	 */
	public void valida(String id, String nomeCpf) {
		valida(id);
		valida(nomeCpf);
	}
	
	/**
	 * Validar o id e uma lista de habilidades de uma tarefa. As habilidades não podem ser objetos nulos.
	 * 
	 * @param id O id a ser validado.
	 * @param habilidades A lista de habilidades a ser validado.
	 */
	public void valida(String id, String[] habilidades) {
		valida(id);
		Objects.requireNonNull(habilidades, "Habilidades não pode ser nulo");
	}
	
	/**
	 * Validar o id, nome/cpf e as habilidades de uma tarefa. O id e o nome/cpf não podem ser
	 * objetos nulos ou vazio e as lista de habilidades não pode ser um objeto nulo.
	 * 
	 * @param id O id a ser validado.
	 * @param nomeCpf O nome ou cpf a ser validado.
	 * @param habilidades A lista de habilidades a ser validado.
	 */
	public void valida(String id, String nomeCpf, String[] habilidades) {
		valida(id);
		valida(nomeCpf);
		Objects.requireNonNull(habilidades, "Habilidades não pode ser nulo");
	}
	
	/**
	 * Validar o id, nome, as habilidades e os ids das atividades de uma tarefa.
	 * O id e o nome não podem ser objetos nulos ou vazio e as lista de habilidades e ids não
	 * pode ser um objeto nulo.
	 * 
	 * @param id O id a ser validado.
	 * @param nome O nome a ser validado.
	 * @param habilidades A lista de habilidades a ser validado.
	 * @param idAtividades A lista de ids a ser validado.
	 */
	public void valida(String id, String nome, String[] habilidades, String[]idAtividades) {
		valida(id);
		valida(nome);
		Objects.requireNonNull(habilidades, "Habilidades não pode ser nulo");
		Objects.requireNonNull(idAtividades, "Conteudo não pode ser nulo");
	}
	
	/**
	 * Validar as horas de uma tarefa. As horas não podem ser inteiros negativos.
	 * 
	 * @param horas As horas da tarefa.
	 */
	public void validaHoras(int horas) {
		if(horas < 0) {
			throw new IllegalArgumentException("Horas Invalidas");
		}
	}

	/**
	 * Valida o id e a Tarefa. O id não podem ser um objeto nulo ou vazio e a tarefa não
	 * pode ser um objeto nulo.
	 * 
	 * @param id O id a ser validado.
	 * @param tarefa A tarefa a ser validado.
	 */
	public void valida(String id, TarefaInterface tarefa) {
		valida(id);
		Objects.requireNonNull(tarefa, "Conteúdo não pode ser nulo");
	}
	
	/**
	 * Valida se uma tarefa é da classe TarefaGerencial.
	 * 
	 * @param tarefa A tarefa a ser validado.
	 */
	public void validaTarefaGerencial(TarefaInterface tarefa) {
		if(!(tarefa instanceof TarefaGerencial)) {
			throw new IllegalStateException("Não pode fazer porque não é gerencial");
    	} 
	}
	
	/**
	 * Valida se uma tarefa é da classe Tarefa.
	 * 
	 * @param tarefa A tarefa a ser validado.
	 */
	public void validaTarefa(TarefaInterface tarefa) {
		if(!(tarefa instanceof Tarefa)) {
			throw new IllegalStateException("Tarefa Invalida");
    	} 
	}
}
