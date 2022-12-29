package com.franciscodantas.lunr.documento;

import java.util.Objects;

import com.franciscodantas.lunr.ValidadorPadrao;

/**
 * Classe de validação de documento. Os documentos são válidos se todos os seus
 * termos não forem nulos. Nem deve ser possível ter IDs nulos ou vazios.
 */
class ValidadorDocumentos extends ValidadorPadrao {

	/**
	 * Operação padrão de validação de ID e de conteúdo (texto a ser processado). O
	 * conteúdo não pode ser nulo ou vazio, bem como o ID deve ser válido.
	 * 
	 * @param id       ID a ser validado.
	 * @param conteudo Conteúdo a ser validado.
	 */
	public void validacao(String id, String conteudo) {
		validacao(id);
		Objects.requireNonNull(conteudo, "Conteúdo não pode ser nulo");
		if (conteudo.isBlank()) {
			throw new IllegalArgumentException("Conteúdo não pode ser vazio");
		}
	}

	/**
	 * Valida o ID e os termos de um documento. Nenhum termo pode ser vazio/nulo.
	 * 
	 * @param id    ID do documento a ser validado.
	 * @param texto Termos do documento.
	 */
	public void validacao(String id, String[] texto) {
		validacao(id);
		Objects.requireNonNull(texto, "Conteúdo não pode ser nulo");
		int i = 0;
		for (String t : texto) {
			i += 1;
			if (t.isBlank()) {
				throw new IllegalArgumentException("Termo não pode ser vazio: termo " + i + " de " + texto.length);
			}
		}
	}

}
