package sapo.busca;

import java.util.Objects;

import sapo.ValidadorPadrao;

/**
 * Valida as entradas de busca
 * 
 * @author franciscodantas
 *
 */
public class ValidadorBusca extends ValidadorPadrao{

	/**
	 * Valida se uma consulta é valida em caso de não ser nula nem vazia.
	 * 
	 * @param consulta Termos de consulta
	 */
	public void valida(String consulta) {
		Objects.requireNonNull(consulta, "Termos de pesquisa não podem ser nulos");
		if (consulta.isBlank()) {
			throw new IllegalArgumentException("Termos de pesquisa não podem ser vazios");
		}
	}

	/**
	 * Valida o nome e o id da atividade, ambos são validos se não for nula nem vazia.
	 * 
	 * @param idAtividade Id da atividade
	 * @param nome Nome de consulta.
	 */
	public void valida(String idAtividade, String nome) {
		super.valida(nome);
		Objects.requireNonNull(idAtividade, "Id não pode ser nulo");
		if (idAtividade.isBlank()) {
			throw new IllegalArgumentException("Id não pode ser vazio");
		}
	}

	/**
	 * Confere se as n buscas são positivas e maior que 0.
	 * 
	 * @param nBuscas quantidade de buscas recentes.
	 */
	public void valida(int nBuscas) {
		if(nBuscas <= 0) {
			throw new IllegalArgumentException("Quantidade de buscas não pode ser negativa ou zero");
		}
	}
	
	/**
	 * Valida se o index de busca é valido, vai ser valido se for maior ou igual a 0.
	 * 
	 * @param idx index de busca
	 */
	public void validaIdx(int idx) {
		if(idx < 0) {
			throw new IllegalArgumentException("Quantidade de buscas não pode ser negativa ou zero");
		}
	}

}
