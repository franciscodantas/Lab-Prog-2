package sapo.pessoas;

/**
 * Controller para as pessoasno sistema. Pessoas são as reponsaveis por fazer as
 * tarefas cadastradas e coordenar as atividades do SAPO.
 * 
 * Quando é necesssario manipular alguma pessoa, as entidades de pessoa realizam
 * as operações.
 * 
 * @author franciscodantas
 *
 */
public class PessoaController {
	
	/**
	 * ps - O pessoa service que será controlado.
	 * vp - O validador que será usado.
	 */
	private PessoaService ps;
	private ValidadorPessoa vp;
	
	/**
	 * Cria o PessoaController, configurando qua pessoa service será usado.
	 * @param ps O pessoa service que será controlado.
	 */
    public PessoaController(PessoaService ps) {
    	this.ps = ps;
    	vp = new ValidadorPessoa();
    }
    
    /**
     * Cadastra uma pessoa no sistema, valida o cpf e o nome da pessoa
     * para que não sejam vazios ou nulos.
     * 
     * @param cpf CPF da pessoa.
     * @param nome Nome da pessoa.
     * @param habilidades Habilidades que a pessoa possui.
     */
	public void cadastrarPessoa(String cpf, String nome, String[] habilidades) {
		vp.valida(cpf, nome);
    	this.ps.cadastrarPessoa(new Pessoa(cpf, nome, habilidades));
    }
	
	/**
	 * Pega a representação textual de uma pessoa, para isso o cpf inserido deve
	 * ser valido.
	 * 
	 * @param cpf CPF da pessoa.
	 * @return exibição de uma pessoa.
	 */
    public String exibirPessoa(String cpf) {
    	vp.valida(cpf);
		return this.ps.exibirPessoa(cpf);
    }
    
    /**
     * Operação para alterar o nome de uma pessoa, valida se o cpf e o novo nome
     * não são nulos ou vazios.
     * @param cpf CPF da pessoa.
     * @param novoNome O novo nome da pessoa.
     */
    public void alterarNomePessoa(String cpf, String novoNome) {
    	vp.valida(cpf, novoNome);
    	this.ps.alterarNomePessoa(cpf, novoNome);
    }
    
    /**
     * Altera as habilidades de uma pessoa, valida se o cpf não é nulo ou vazio.
     * @param cpf CPF da pessoa.
     * @param novasHabilidades A nova lista de habilidades.
     */
    public void alterarHabilidadesPessoa(String cpf, String[] novasHabilidades) {
    	vp.valida(cpf);
    	this.ps.alterarHabilidadesPessoa(cpf, novasHabilidades);
    }
    
    /**
     * Remove uma pessoa totalmente removida do sistema, valida se o cpf não é nulo ou vazio.
     * @param cpf CPF da pessoa.
     */
    public void removerPessoa(String cpf) {
    	vp.valida(cpf);
    	this.ps.removerPessoa(cpf);
    }
    
    /**
     * Adiciona uma mensagem/comentario de uma pessoa em relação a outra pessoa, para isso 
     * os cpfs não podem ser vazio ou nulos.  
     * @param cpf CPF da pessoa que esta recebendo o comentario.
     * @param comentario Comentario sobre a pessoa.
     * @param autorCpf Cpf do autor do comentario.
     */
    public void adicionarComentarioPessoa(String cpf, String comentario, String autorCpf) {
    	vp.valida(cpf, comentario, autorCpf);
    	this.ps.adicionarComentarioPessoa(cpf, comentario, autorCpf);
    }
    
    /**
     * Lista todos os comentarios sobre uma pessoa, valida se o cpf não é nulo ou vazio.
     * @param cpf CPF da pessoa.
     * @return Lista dos comentarios recebidos.
     */
    public String listarComentariosPessoa(String cpf) {
    	vp.valida(cpf);
		return this.ps.listarComentariosPessoa(cpf);
    }
    
    /**
     * Cadastra um aluno no sistema, validando o cpf, o nome, a matricula, o periodo e as habilidades
     * para que não sejam vazios ou nulos.
     * 
     * @param cpf O cpf do Aluno.
     * @param nome O nome do Aluno.
     * @param matr A matricula do Aluno.
     * @param periodo O periodo do Aluno.
     * @param habilidades As habilidades que o aluno possui.
     */
    public void cadastrarAluno(String cpf, String nome, String matr, int periodo, String[] habilidades) {
		this.vp.valida(cpf, nome, matr, periodo, habilidades);
		this.ps.cadastrarAluno(cpf, nome, matr, periodo, habilidades);
	}
    
    /**
     * Cadastra um professor no sistema, validando o cpf, o nome, a siape, as disciplinas e as habilidades
     * para que não sejam vazios ou nulos.
     * 
     * @param cpf O cpf do professor.
     * @param nome O nome do professor.
     * @param siape A siape do professor.
     * @param disciplinas As disciplinas que o professor possui.
     * @param habilidades As habilidades que o professor possui.
     */
    public void cadastrarProfessor(String cpf, String nome, String siape, String[] disciplinas, String[] habilidades) {
		this.vp.valida(cpf, nome, siape, disciplinas, habilidades);
		this.ps.cadastrarProfessor(cpf, nome, siape, disciplinas, habilidades);
	}
    
    /**
     * Altera a função associada a uma pessoa para a função de professor.
     * 
     * @param cpf O cpf do professor.
     * @param siape A siape do professor.
     * @param disciplinas As disciplinas que o professor possui.
     */
    public void definirFuncaoProfessor(String cpf, String siape, String[] disciplinas) {
		this.vp.valida(cpf, siape, disciplinas);
		this.ps.definirFuncaoProfessor(cpf, siape, disciplinas);
	}
    
    /**
     * Altera a função associada a uma pessoa para a função de aluno.
     * 
     * @param cpf O cpf do Aluno.
     * @param matr A matricula do Aluno.
     * @param periodo O periodo do Aluno.
     */
    public void definirFuncaoAluno(String cpf, String matr, int periodo) {
		this.vp.valida(cpf, matr, periodo);
		this.ps.definirFuncaoAluno(cpf, matr, periodo);
	}
    
    /**
     * Metodo que retorna o nivel de uma pessoa. O nível é contabilizado por cada tarefa em andamento
     * e finalizada na qual a pessoa é responsável.
     * 
     * @param cpf O cpf da pessoa.
     * @return O valor do nivel da pessoa.
     */
    public int pegarNivel(String cpf) {
		this.vp.valida(cpf);
		return this.ps.pegarNivel(cpf);	
	}
    
    /**
     * Remove uma funcao da pessoa.
     *  
     * @param cpf O cpf da pessoa.
     */
    public void removerFuncao(String cpf) {
		this.vp.valida(cpf);
		this.ps.removerFuncao(cpf);
	}

    /**
     * Listar todas as pessoas do sistema, ordenadas pelo nome, e por CPF em caso de empate.
     * 
     * @return Lista de todas as pessoas armazenadas no sistema.
     */
	public String[] listarPessoas() {
		return this.ps.listarPessoas();
	}
}
