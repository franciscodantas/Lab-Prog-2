package com.franciscodantas.lunr;

import java.util.Objects;

/**
 * Classe de validação padrão para entidades básicas do sistema.
 * 
 * A classe por si é uma base para outros validadores.
 * 
 * No Lunr apenas o ID é uma entidade padrão.
 */
public abstract class ValidadorPadrao {

	/**
	 * Operação de validação do ID.
	 * 
	 * As duas regras de validação é a de que o ID não pode ser nulo e não pode ser
	 * vazio.
	 * 
	 * É preciso ter cuidado que o sistema pode gerar alguns IDs automaticamente
	 * (MERGE) mas cabe ao usuário se quiser fazer uso (ou não) desses tipos de IDs.
	 * 
	 * @param id ID a ser validado.
	 */
	public void validacao(String id) {
		Objects.requireNonNull(id, "ID não pode ser nulo");
		if (id.isBlank()) {
			throw new IllegalArgumentException("ID não pode ser vazio");
		}
	}

}
