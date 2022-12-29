package sapo.pessoas;

import java.util.Date;
import java.util.Objects;

/**
 * Comentario representa um comentario sobre uma pessoa no sistema.
 * 
 * É possivel criar um comentario e ter uma representação textual desse comentario.
 * 
 * @author franciscodantas
 *
 */
public class Comentario {
	
	/**
	 * data = a data que o comentario foi criado.
	 * comentario - texto que representa um comentario sobre uma pessoa.
	 * autor - nome e cpf de quem fez o comentario.
	 */
	private Date data;
	private String comentario;
	private String autor;
	
	/**
	 * Criador padrão de comentario.
	 * 
	 * Para construir um comentario é necessario um texto, o comentario em si,
	 * e uma representação textual do autor do comentario.
	 * 
	 * @param comentario  Um texto que representa o comentario.
	 * @param autor Autor do comentario.
	 */
	public Comentario(String comentario, String autor) {
		Objects.requireNonNull(comentario, "Comentario não pode ser nulo");
		if (comentario.isBlank()) {
			throw new IllegalArgumentException("Comentario não pode ser vazio");
		}
		this.autor = autor;
		this.comentario = comentario;
		this.data = new Date();
	}
	
	/**
	 * Retorna uma representação textual de um comentario, a representação consiste
	 * no --comentario (nome do autor).
	 * 
	 * @return Representação textual de um comentario.
	 */
	@Override
	public String toString() {
		return String.format("--%s (%s)", this.comentario, this.autor);
	}
	
	/**
	 * retorna a data de criação do comentario.
	 * 
	 * @return data de criação.
	 */
	public Date getData() {
		return this.data;
	}
}
