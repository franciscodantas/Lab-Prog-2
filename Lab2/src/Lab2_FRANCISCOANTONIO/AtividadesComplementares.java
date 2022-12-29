package Lab2_FRANCISCOANTONIO;

/**
* Representacao das atividades complementares realizadas pelo aluno durante o curso,
* essas atividades sao compostas por Estagios, Projetos e Cursos.
*
* @author Francisco Antonio Dantas de Sousa
* 
*/

public class AtividadesComplementares {
	
	/**
	 * estagios - array que contem os estagios realizados, podendo ter ate 9 estagios cadastrados;
	 * 
	 * projetos - array que contem os projetos realizados, podendo ter ate 16 projetos cadastrados;
	 * 
	 * quantEstagio - Quantidade de estagios foram realizados.
	 * 
	 * horasEstagio - Quantidade de horas de estagios feitas.
	 * 
	 * mesesEstagio - quantidade de meses de estagios feitos.
	 * 
	 * quantProjeto - Quantidade de projetos foram realizados.
	 * 
	 * mesesProjeto - Quantidade de meses de projetos feitos;
	 * 
	 * creditoEstagio - Quantidade de creditos foram obtidos pela realizacao dos estagios.
	 * 
	 * creditoProjeto - Quantidade de creditos foram obtidos pela realizacao dos projetos.
	 * 
	 * creditoCurso - Quantidade de creditos foram obtidos pela realizacao dos cursos.
	 * 
	 * horasCursos - Quantidade de horas feitas de cursos.
	 */
	
	private String[] estagios = new String[9];
	private String[] projetos = new String[16];
	private int quantEstagio;
	private int horasEstagio;
	private int mesesEstagio;
	private int quantProjeto;
	private int mesesProjeto;
	private int creditoEstagio;
	private int creditoProjeto;
	private int creditoCurso;
	private double horasCursos;
	
	/**
	 * Adiciona um estagio ao conjunto de atividades complementares realizadas, calculando quantos creditos
	 * sao ganhos por esse estagio, sendo obtidos 5 creditos a cada 300 horas de estagio e 4 meses de duracao,
	 * nesse caso e considerado que a quantidade de meses e equivalente as horas cadastradas, e cadastrando o estagio no formato "Estagio horasDeEstagio".
	 * 
	 * @param horasEstagio horas realizadas no estagio.
	 */
	
	public void adicionarEstagio(int horasEstagio) {
		if(quantEstagio <= 8) {
			this.horasEstagio += horasEstagio;
			this.mesesEstagio += 4;
			creditoEstagio = ((int) this.horasEstagio/300) * 5;
			estagios[quantEstagio] = "Estagio " + horasEstagio;
			quantEstagio ++;
		}
	}
	
	/**
	 * Adiciona um novo estagio as atividas complementares, recebendo alem da do numero de horas do estagio
	 * a quantidade de meses. Dessa forma a cada 300 horas e 4 meses de duracao do estagio o aluno recebe 5 
	 * creditos e cadastrando o estagio no formato "Estagio horasDeEstagio duracaoEmMeses".
	 * 
	 * @param horasEstagio horas realizadas no estagio
	 * @param meses meses de duracao do estagio
	 */
	
	public void adicionarEstagio(int horasEstagio, int meses) {
		if(quantEstagio <= 8) {
			this.horasEstagio += horasEstagio;
			this.mesesEstagio += meses;
			creditoEstagio = ((int)((((int) this.horasEstagio/300) + this.mesesEstagio/4)/2)) * 5;
			estagios[quantEstagio] = "Estagio " + horasEstagio + " " + meses;
			quantEstagio ++;
		}
	}
	
	/**
	 * Adiciona um projeto ao conjunto de atividades complementares realizadas, calculando quantos creditos
	 * sao ganhos por esse projeto, sendo obtidos 2 creditos a cada 3 meses de projeto e cadastrando o projeto no formato 
	 *"Projeto mesesDeProjeto".
	 * 
	 * @param mesesProjeto meses dedicados ao projeto.
	 */
	
	public void adicionarProjeto(int mesesProjeto) {
		if(quantProjeto <= 15) {
			this.mesesProjeto += mesesProjeto;
			creditoProjeto = ((int) this.mesesProjeto/3) * 2;
			projetos[quantProjeto] = "Projeto " + mesesProjeto;
			quantProjeto ++;
		}
	}
	
	/**
	 * Adiciona horas de cursos ao conjunto de atividades complementares realizadas, calculando quantos creditos
	 * sao ganhos por essas horas de curso, sendo obtidos 1 creditos a cada 30 horas de curso.
	 * 
	 * @param horasCurso horas de cursos complementares.
	 */
	
	public void adicionarCurso(double horasCurso) {
		horasCursos += horasCurso;
		creditoCurso = ((int) horasCursos/30);
		
	}
	
	/**
	 * Esta operacao retorna um array de Strings que representa todas as atividades. Os primeiros itens do array 
	 * representa estagios cadastrados e o total de horas de cada um desses estagios,
	 * seguida de cada projeto, e seu total de meses, e pelo o total de horas de cursos. Os 3 itens finais sao os totais 
	 * de creditos obtidos dos estagios, dos projetos e dos cursos, respectivamente.
	 * 
	 * @return um array de Strings das atividades complementares realizadas.
	 */
	
	public String[] pegaAtividades() {
		String[] atividadesCadastradas = new String[quantEstagio + quantProjeto + 4];
		for(int i = 0; i <= quantEstagio; i++) {
			atividadesCadastradas[i] = estagios[i];
		}
		
		for(int j = quantEstagio, i = 0; j <= quantEstagio + quantProjeto; j++, i++) {
			atividadesCadastradas[j] = projetos[i];
		}
		
		atividadesCadastradas[atividadesCadastradas.length - 4] = "Cursos " + horasCursos;
		atividadesCadastradas[atividadesCadastradas.length - 3] = "Creditos_Estagio " + creditoEstagio;
		atividadesCadastradas[atividadesCadastradas.length - 2] = "Creditos_Projeto " + creditoProjeto;
		atividadesCadastradas[atividadesCadastradas.length - 1] = "Creditos_Cursos " + creditoCurso;
		
		return atividadesCadastradas;
	}
	
	/**
	 * Calcula o total de creditos obtidos com a soma dos creditos de todas as atividades realizadas. 
	 * 
	 * @return o total dos creditos obtidos.
	 */
	
	public int contaCreditos() {
		return creditoEstagio + creditoProjeto + creditoCurso;
	}

}
