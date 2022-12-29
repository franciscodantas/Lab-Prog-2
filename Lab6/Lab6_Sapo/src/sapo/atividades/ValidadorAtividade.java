package sapo.atividades;

import java.util.Objects;

import sapo.ValidadorPadrao;
/**
 * valida as entradas de atividade.
 * 
 * @author Arthur Soares
 *
 */
public class ValidadorAtividade extends ValidadorPadrao{
    
	/**
	 * Valida se as entradas para a criação de atividade não são nulos nem vazio.
	 *  
	 * @param descricao Descrição da atividade.
	 * @param cpf Cpf da pessoa responsavel.
	 * @param nome Nome da atividade.
	 */
	public void valida(String descricao, String cpf, String nome) {
		valida(cpf);
		Objects.requireNonNull(nome, "Conteúdo não pode ser nulo");
		if (nome.isBlank()) {
			throw new IllegalArgumentException("Conteúdo não pode ser vazio");
		}else if (descricao.isBlank()) {
			throw new IllegalArgumentException("Conteúdo não pode ser vazio");
		}
	}
	
	/**
	 * Valida se as entradas não são vazias ou nulas.
	 * 
	 * @param atividadeId Id da atividade.
	 * @param descricao Descrição da atividade,
	 */
	public void valida(String atividadeId, String descricao){
		valida(atividadeId);
		if (descricao.isBlank()) {
			throw new IllegalArgumentException("Conteúdo não pode ser vazio");
		}
	}
}
