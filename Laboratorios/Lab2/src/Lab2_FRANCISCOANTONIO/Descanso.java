package Lab2_FRANCISCOANTONIO;

/**
 * Classe que controla o descanso do aluno cadastrado no COISA, essa classe e responsavel pro conferir se o aluno esta descansando
 * corretamente de acordo com o numero de semanas e registrar por meio de emojis como esta o seu humor no momento.
 * 
 * @author Francisco Antonio Dantas de Sousa
 *
 */

public class Descanso {
	
	/**
	 * estado - armazena o estado atual do aluno.
	 * horasDescanso - armazena as horas de descanso tiradas, sem contar o tempo de sono.
	 * semanas - conta quantas em quantas semanas foram realizadas as horas de descanso, comecando inicialmente por 1.
	 * emoji - armazena o emoji escolhido pelo aluno pra representa o momento. 
	 */
	
	private String estado;
	private int horasDescanso;
	private int semanas = 1;
	private String emoji;
	
	/**
	 * Construtor padrao da classe Descanso(), ele assume que o aluno comeca sempre cansado e sem nenhum emoji registrado.
	 */
	public Descanso() {
		this.estado = "cansado";
		this.emoji = "";
	}
	
	/**
	 * Pega o valor do estado do aluno.
	 * 
	 * @return o estado de descanso do aluno.
	 */
	
	public String getStatusGeral() {
		mudarEstado();
		return estado;
	}
	
	/**
	 * Muda o estado de descanso do aluno, conferindo se o aluno esta descansado ou nao.
	 * E considerado cansado quando se tem menos de 26 horas/semana de descanso.
	 */
	
	private void mudarEstado() {
		if((this.horasDescanso/this.semanas) >= 26) {
			this.estado = "descansado" + " " + this.emoji;
		}else {
			this.estado = "cansado" + " " + this.emoji;
		}
	}
	
	/**
	 * Define quantas horas de descanso foram feitas durante x semanas.
	 * 
	 * @param horas horas de descanso registradas.
	 */
	
	public void defineHorasDescanso(int horas) {
		this.horasDescanso = horas;
		this.emoji = "";
	}

	/**
	 * Define a quantidade de semanas que se passaram.
	 * 
	 * @param semanas quantidade de semanas registradas.
	 */
	
	public void defineNumeroSemanas(int semanas) {
		this.semanas = semanas;
		this.emoji = "";
	}
	
	/**
	 * Define o emoji que representa o aluno no momento.
	 * 
	 * @param emoji emoji que representa o aluno
	 */
	
	public void definirEmoji(String emoji) {
		this.emoji = emoji;
	}
}
