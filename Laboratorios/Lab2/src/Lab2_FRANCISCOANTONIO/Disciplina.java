package Lab2_FRANCISCOANTONIO;


import java.util.Arrays;

/**
 * A classe Disciplina controla o desempenho academico do aluno nas disciplinas do LCCs, nela e possivel cadastrar disciplinas,
 * bem como quantas notas essa disciplina tem, adotando como padrao 4 notas, se a disciplina tem peso sobre cada nota, se o aluno
 * esta passando por media e registrar as horas de estudo.
 * 
 * @author Francisco Antonio Dantas de Sousa
 *
 */

public class Disciplina {
	
	/**
	 * disciplina - nome da disciplina.
	 * horasEstudo - horas de estudo realizadas.
	 * notas - array com as notas registradas, por padrao cada nota comeca com 0.
	 * pesos - peso de cada nota da materia, quando vazio o peso de cada nota e 1.
	 */
	
	private String disciplina;
	private int horasEstudo;
	private double[] notas;
	private int[] pesos;
	
	/**
	 * Construtor usado para casos de disciplinas que tenham pesos, recebe o nome da disciplina que vai ser criada, quantas notas
	 * essa disciplina tem e os pesos de cada uma das notas.
	 * 
	 * @param disciplina nome da disciplina.
	 * @param quantNotas quantidade de notas da disciplina.
	 * @param pesos peso de cada nota da disciplina.
	 */
	
	public Disciplina(String disciplina, int quantNotas, int[] pesos) {
		this.disciplina = disciplina;
		notas = new double[quantNotas];
		this.pesos = pesos;
	}
	
	/**
	 * Construtor usado para casos onde a disciplina tem x notas mas sem peso entre elas,logo peso de cada nota
	 * e 1, recebe o nome da disciplina que vai ser criada e a quantidade de notas.
	 * 
	 * @param disciplina nome da disciplina.
	 * @param quantNotas quantidade de notas da disciplina.
	 */
	
	public Disciplina(String disciplina, int quantNotas) {
		this.disciplina = disciplina;
		notas = new double[quantNotas];
	}
	
	/**
	 * Construtor usado para disciplinas padroes de 4 notas e sem peso entre elas, logo peso de cada nota e 1,
	 * rece o nome da disciplina que vai ser criada.
	 * 
	 * @param disciplina nome da disciplina.
	 */
	
	public Disciplina(String disciplina) {
		this.disciplina = disciplina;
		notas = new double[4];
	}
	
	/**
	 * Cadastra as horas de estudo dedicadas a disciplina.
	 * 
	 * @param horas horas de estudo dedicadas a disciplina.
	 */
	
	public void cadastraHoras(int horas) {
		this.horasEstudo += horas;
	}
	
	/**
	 * Cadastra uma das n notas da disciplina, para isso recebe qual nota vai ser modificada e o valor
	 * novo que vai ser escrito.
	 * 
	 * @param nota indice da nota.
	 * @param valorNota valor da nota.
	 */
	
	public void cadastraNota(int nota, double valorNota) {
		notas[nota - 1] = valorNota;
	}
	
	/**
	 * Calcula a media das notas, essa media pode ser aritmetica ou ponderada a depender se algum peso
	 * foi cadastrado ou nao.
	 * 
	 * @return retorna a media das notas.
	 */
	
	private double media() {
		if(pesos == null ) {
			double soma = 0;
			for(int i = 0; i < notas.length; i++) {
				soma += notas[i];
			}
			return soma/notas.length;
		}else {
			double somaDivisor = 0;
			for(int i = 0; i < pesos.length; i++) {
				somaDivisor += pesos[i];
			}
			double somaPonderada = 0;
			for(int i = 0; i < pesos.length; i++) {
				somaPonderada += notas[i] * pesos[i];
			}
			return somaPonderada/somaDivisor;
		}
	}
	
	/**
	 * Verifica se a media e maior ou igual a 7 ou nao, sendo aprovado caso a primeira opcao seja verdadeira ou reprovado caso
	 * a segunda seja.
	 * 
	 * @return retorna se o aluno esta aprovado ou nao, enviando true ou false respectivamente para cada caso. 
	 */
	
	public boolean aprovado() { 
		if(media() >= 7.0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Retorna a representacao do desempenho geral da disciplina e o qual disciplina no formato: "Nomedadisciplina horasdeestudo
	 * Media ConjutodeNotas". 
	 * 
	 * @return a representacao da disciplina.
	 */
	
	public String toString() {
		return String.format("%s %s %s %s", disciplina, horasEstudo, media(), Arrays.toString(notas));
	}

}
