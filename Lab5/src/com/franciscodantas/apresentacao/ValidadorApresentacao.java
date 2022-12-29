package com.franciscodantas.apresentacao;

import java.util.Objects;

import com.franciscodantas.lunr.ValidadorPadrao;

/**
 * Classe para validação da apresentação, suas entidades e parâmetros.
 * 
 * @author francisco antonio dantas
 */
public class ValidadorApresentacao extends ValidadorPadrao {
	
	/**
	 * Valida o tipo de apresentação e o id do documento. O tipo de apresentação 
	 * e o id não pode ser nulo ou vazio, além do id existir nos documentos guardados.
	 * 
	 * @param termos Termos a serem validados.
	 */
	public void valida(String id, String tipo) {
		validacao(id);
		Objects.requireNonNull(tipo, "Tipo de apresentação não pode ser nula");
		if (tipo.isBlank()) {
			throw new IllegalArgumentException("Tipo de apresentação não pode ser vazio");
		}
	}
}
