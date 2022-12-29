package estacionAqui;

/**
 * 
 * @author Francisco Antonio Sousa
 *
 */

public class Comentarios {
	private String[] comentario;
	private int quantComentario;
	
	public Comentarios() {
		quantComentario = 0;
		comentario = new String[5];
	}
	
	public void criaComentario(String comentario, String autor) {
		if(this.quantComentario < 5) {
			this.comentario[quantComentario] = comentario + " (" + autor + ") ";
			quantComentario += 1;
		}
		else {
			quantComentario = 0;
			this.comentario[quantComentario] = comentario + " (" + autor + ") ";
		}
		
		
	}
	
	public void criaComentario(String comentario) {
		if(this.quantComentario < 5) {
			this.comentario[quantComentario] = comentario + " () ";
			quantComentario += 1;
		}
		else {
			quantComentario = 0;
			this.comentario[quantComentario] = comentario + " () ";
		}
	}
	
	public String listaComentarios() {
		String retorno = "";
		for(int i = 0; i < this.comentario.length; i++) {
			if(this.comentario[i] != null) {
				retorno += this.comentario[i] + "\n";
			}
		}
		return retorno;
	}

}
