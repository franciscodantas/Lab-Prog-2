package agenda;

import java.util.Objects;

/**
 * Essa classe representa um contato dentro da agenda
 * 
 * @author Francisco Antonio Dantas
 *
 */
public class Contato {
    private String nome;
	private String telefone;
	private boolean favorito;
	private String[] tags;
	
	/**
	 * cria um novo contato.
	 * 
	 * @param nome nome do contato.
	 * @param sobrenome sobrenome do contato.
	 * @param telefone telefone do contato.
	 */
	public Contato(String nome, String sobrenome, String telefone) {
		this.nome = nome + " " + sobrenome;
		this.telefone = telefone;
		this.favorito = false;
		this.tags = new String[5];
	}
	
	@Override
	/**
	 * representacao textual do contato, com nome, sobrenome, tag, favorito e telefone
	 */
	public String toString() {
		
		if (favorito) {
			String tag = "";
			for(int i = 0; i < tags.length; i++) {
				if(tags[i] == null) {
					
				}else {
					tag += " " + tags[i];
				}
			}
			return "\n<3"+ " " + nome + "\n" + telefone + "\n" + tag.trim();
		} 
		else {
			String tag = "";
			for(int i = 0; i < tags.length; i++) {
				if(tags[i] == null) {
					
				}else {
					tag += " " + tags[i];
				}
			}
			return "\n" + nome + "\n" + telefone + "\n" + tag.trim();
		}
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		return Objects.equals(nome, other.nome);
	}

	/**
	 * Retorna o nome completo do contato
	 * @return Retorna o nome completo do contato
	 */
	public String getNomeCompleto(){
		return this.nome;
	}
	
	/**
	 * favorita o contato
	 */
	public void favoritar() {
		this.favorito = true;
	}
	
	/**
	 * Retorna o status de favorito do contato
	 * @return Retorna o status de favorito do contato
	 */
	public boolean getfavorito(){
		return this.favorito;
	}
	
	/**
	 * Adiciona uma tag ao contato
	 * @param tag a tag a ser adicionada.
	 * @param posicaoTag posicao da tag a ser adicionada no array de tags.
	 */
	public void adicionarTag(String tag, String posicaoTag) {
		this.tags[Integer.parseInt(posicaoTag)] = tag;
	}
}
