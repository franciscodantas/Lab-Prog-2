package Lab2_FRANCISCOANTONIO;

/**
 * Essa classe e responsavel por controla a utilizacao de internet pelo aluno dedicados a uma disciplina remota.
 * Nela e possivel registrar o tempo online para cada materia e conferir se a meta da mesma foi atinginda
 * 
 * @author Francisco Antonio Dantas de Sousa
 *
 */

public class RegistroTempoOnline {
	
	/**
	 * cadeira - armazena o nome da disciplina.
	 * horasDedicadas - registra o numero de horas dedicadas online.
	 * horasEsperadas - registra o numero de horas de dedicacao esperadas para cada disciplina.
	 */
	
	private String cadeira;
	private int horasDedicadas;
	private int horasEsperadas;
	
	/**
	 * Esse construtor cria um novo registro de tempo online, onde recebe o nome da cadeira e as horas de dedicacao
	 * esperada pelos alunos.
	 * 
	 * @param cadeira nome da disciplina.
	 * @param horasEsperadas horas de dedicacao esperadas.
	 */
	
	public RegistroTempoOnline(String cadeira, int horasEsperadas) {
		this.cadeira = cadeira;
		this.horasEsperadas = horasEsperadas;
	}
	
	/**
	 * Esse construtor registra apenas o nome da disciplina, adotando como horas de dedicacao esperadas o padrao de 120 horas.
	 * 
	 * @param cadeira nome da disciplina.
	 */
	
	public RegistroTempoOnline(String cadeira) {
		this.horasEsperadas = 120;
		this.cadeira = cadeira;
	}
	
	/**
	 * Adiciona horas dedicadas ao estudo da materia online.
	 * 
	 * @param horasDedicadas horas dedicadas ao estudo da materia.
	 */
	
	public void adicionaTempoOnline(int horasDedicadas) {
		this.horasDedicadas += horasDedicadas;
	}
	
	/**
	 * Confere se a meta de horas de dedicacao esperadas foram atigidas, retornando true se sim e false se nao.
	 * 
	 * @return se a meta foi atingida ou nao.
	 */
	
	public boolean atingiuMetaTempoOnline() {
		if(this.horasDedicadas >= this.horasEsperadas) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Representacao em string da classe, retorna os valores da classe no formato: "NomedaCadeira horasDedicadas/horasEsperadas".
	 *  
	 * @return a representacao em String das horas de uma disciplina.
	 */
	
	public String toString() {
		return String.format("%s %s/%s", this.cadeira,this.horasDedicadas, this.horasEsperadas);
	}
}
