package sapo;

import java.util.Objects;

/**
 * Validador padrão para os ids do SAPO.
 * 
 * @author franciscodantas
 *
 */
public class ValidadorPadrao {
	
	/**
	 * Cria um validador padrão para um conteudo que não pode ser vazio nem nulo.
	 * 
	 * @param conteudo Conteudo a ser analizado.
	 */
	public void valida(String conteudo) {
		Objects.requireNonNull(conteudo, "Conteudo não pode ser nulo");
		if (conteudo.isBlank()) {
			throw new IllegalArgumentException("Conteudo não pode ser vazio");
		}
	}
}
