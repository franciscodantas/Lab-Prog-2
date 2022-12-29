package sapo.pessoas;

import java.util.Objects;

import sapo.ValidadorPadrao;

/**
 * Classe de validação de pessoas. As pessoas são válidas se todos os seus
 * termos não forem nulos. Nem deve ser possivel ter CPFs e nomes nulos ou vazios.
 * 
 * @author franciscodantas e Thayane Barros
 *
 */
public class ValidadorPessoa extends ValidadorPadrao{

	/**
	 * Validação padrão de validação de cpf e nome. O nome e o cpf não pode
	 * ser nulo ou vazio.
	 * 
	 * @param cpf Cpf a ser validado.
	 * @param nome Nome a ser validado.
	 */
	public void valida(String cpf, String nome) {
		valida(cpf);
		Objects.requireNonNull(nome, "Nome não pode ser nulo");
		if (nome.isBlank()) {
			throw new IllegalArgumentException("Nome não pode ser vazio");
		}
		
	}

	/**
	 * Validador para comentarios para uma pessoa. Os CPDs, tanto do autor quanto
	 * o da pessoa que recebe o comentario, não podem serem nulos/vazios. O comentario
	 * não pode ser nulo.
	 * 
	 * @param cpf Cpf da pessoa que recebe o comentario a ser validado.
	 * @param comentario comentario a ser validado.
	 * @param autorCpf Cpf do autor a ser validado.
	 */
	public void valida(String cpf, String comentario, String autorCpf) {
		valida(cpf);
		valida(autorCpf);
		Objects.requireNonNull(comentario, "Conteúdo não pode ser nulo");
	}
	
	/**
	 * Valida a função de uma pessoa, para um objeto não nulo.
	 * 
	 * @param funcao A função a ser validado.
	 */
	public void validaFuncao(Funcao funcao) {
		Objects.requireNonNull(funcao, "Objeto não pode ser nulo");
	}
	
	/**
	 * Valida o cpf, nome, matricula, perido e habilidades de um Aluno para objetos não nulos ou vazio.
	 * 
	 * @param cpf O cpf a ser validado.
	 * @param nome O nome a ser validado.
	 * @param matr A matricula a ser validado.
	 * @param periodo O periodo a ser validado.
	 * @param habilidades As habilidades a ser validado.
	 */
	public void valida(String cpf, String nome, String matr, int periodo, String[] habilidades) {
		if(nome == null || cpf == null || matr == null || habilidades == null) {
			throw new NullPointerException("Conteudo não pode ser nulo");
		}
		
		if(nome.isBlank() || cpf.isBlank() || matr.isBlank()) {
			throw new IllegalArgumentException("Conteudo não pode ser vazio");
		}
		
		if(periodo < 0) {
			throw new IllegalArgumentException("Periodo Invalido");
		}	
	}
	
	/**
	 * Valida o cpf, nome, siape, disciplinas e habilidades de um Professor para objetos não nulos ou vazio.
	 * 
	 * @param cpf O cpf a ser validado.
	 * @param nome O nome a ser validado.
	 * @param siape A siape a ser validado.
	 * @param disciplinas As disciplinas a ser validado.
	 * @param habilidades As habilidades a ser validado.
	 */
	public void valida(String cpf, String nome, String siape, String[] disciplinas, String[] habilidades) {
		if(nome == null || cpf == null || siape == null || disciplinas == null || habilidades == null) {
			throw new NullPointerException("Conteudo não pode ser nulo");
		}
		if(nome.isBlank() || cpf.isBlank() || siape.isBlank()) {
			throw new IllegalArgumentException("Conteudo não pode ser vazio");
		}
	}
	
	/**
	 * Valida o cpf, siape e as disciplinas de um Professor para objetos não nulos ou vazio.
	 * 
	 * @param cpf O cpf a ser validado.
	 * @param siape A siape a ser validado.
	 * @param disciplinas As disciplinas a ser validado.
	 */
	public void valida(String cpf, String siape, String[] disciplinas) {
		valida(cpf);
		valida(siape);
		Objects.requireNonNull(disciplinas, "Disciplinas não pode ser nulo");	
	}
	
	/**
	 * Valida o cpf, matricula e perido de um Aluno para objetos não nulos ou vazio.
	 * 
	 * @param cpf O cpf a ser validado.
	 * @param matr A matricula a ser validado.
	 * @param periodo O periodo a ser validado.
	 */
	public void valida(String cpf, String matr, int periodo) {
		valida(cpf);
		valida(matr);
		if(periodo < 0) {
			throw new IllegalArgumentException("Periodo Invalido");
		}	
	}
}
