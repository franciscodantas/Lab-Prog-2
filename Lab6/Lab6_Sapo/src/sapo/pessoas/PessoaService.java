package sapo.pessoas;

import java.util.Set;

import sapo.tarefas.TarefaInterface;

/**
 * PessoaService é a base de lógica de operações sobre a pessoa. Demais
 * classes devem fazer uso desse serviço para acessar e operar sobre pessoas.
 * 
 * Para garantir a união das operações e pessoas deve-se ter apenas um PessoaService
 * no sistema.
 * 
 * @author franciscodantas e Thayane Barros
 *
 */
public class PessoaService {
	
	/**
	 * pr - repositorio de pessoas que armazena todas as pessoas que estão no
	 * sistema para que Pessoaservice não precise gerenciar isso.
	 */
	private PessoaRepositorio pr;
	private ValidadorPessoa vp;
	
	/**
	 * Criador padrão de PessoaService, nele é criado o repositorio para armazenar
	 * as pessoas do sistema.
	 */
	public PessoaService() {
		this.pr = new PessoaRepositorio();
		vp = new ValidadorPessoa();
	}
	
	/**
	 * Cadastra uma pessoa no sistema, cada pessoa possui um cpf
	 * caso o cpf já esteja cadastrado essa pessoa será sobrescrita.
	 * 
	 * @param pessoa pessoa que será adicionada.
	 */
	public void cadastrarPessoa(Pessoa pessoa) {
		this.pr.adicionarPessoa(pessoa);
	}

	/**
	 * Exibe uma forma textual da pessoa
	 *  
	 * @param cpf cpf da pessoa a ser exibida.
	 * @return uma representação textual da pessoa.
	 */
	public String exibirPessoa(String cpf) {
		Pessoa pessoa = this.pr.recuperarPessoa(cpf);
		return pessoa.toString();
	}
	
	/**
	 * Recupera uma pessoa. Caso uma pessoa não exista, isso é retornado na
	 * forma de um Optional.
	 * 
	 * @param cpf Cpf da pessoa a ser recuperada.
	 * @return retorna a pessoa recuperada do repositorio.
	 */
	public Pessoa recuperaPessoa(String cpf) {
		return this.pr.recuperarPessoa(cpf);
	}

	/**
	 * Altera o nome de uma pessoa já cadastrada, passando um
	 * novo nome para aquela pessoa.
	 *  
	 * @param cpf Cpf da pessoa que será alterada.
	 * @param novoNome novo nome da pessoa.
	 */
	public void alterarNomePessoa(String cpf, String novoNome) {
		Pessoa pessoa = this.pr.recuperarPessoa(cpf);
	   	pessoa.setNome(novoNome);
	}

	/**
	 * Altera as habilidades de uma pessoa já cadastrada, passando a nova
	 * lista de habilidades, que pode ser vazia.
	 * 
	 * @param cpf Cpf da pessoa que será alterada.
	 * @param novasHabilidades Lista de nova habilidades.
	 */
	public void alterarHabilidadesPessoa(String cpf, String[] novasHabilidades) {
		Pessoa pessoa = this.pr.recuperarPessoa(cpf);
	   	pessoa.setHabilidade(novasHabilidades);
	}

	/**
	 * Remove totalmente uma pessoa do sistema.
	 * 
	 * @param cpf cpf da pessoa a ser removida.
	 */
	public void removerPessoa(String cpf) {
		this.pr.recuperarPessoa(cpf).remove();
	   	this.pr.removePessoa(cpf);
	}

	/**
	 * Adiciona um novo comentario para uma pessoa, esse comentario
	 * possui um autor(outra pessoa) e um texto.
	 * 
	 * @param cpf Cpf da pessoa que recebe o comentario.
	 * @param comentario Texto que representa o comentario.
	 * @param autorCpf Cpf do autor do comentario.
	 */
	public void adicionarComentarioPessoa(String cpf, String comentario, String autorCpf) {
		Pessoa pessoa = this.pr.recuperarPessoa(cpf);
		Pessoa autor = this.pr.recuperarPessoa(cpf);
	   	String nomeAutor = autor.getNome();
	   	pessoa.adicionarComentario(comentario, nomeAutor);
	}

	/**
	 * Lista os comentarios sobre uma pessoa.
	 * 
	 * @param cpf Cpf da pessoa.
	 * @return Uma listagem de representações textuais de comentarios.
	 */
	public String listarComentariosPessoa(String cpf) {
		return this.pr.recuperarPessoa(cpf).listarComentarios();
	}
	
	/**
	 * Adiciona uma tarefa em uma pessoa.
	 * 
	 * @param cpf O cpf da pessoa.
	 * @param tarefa A tarefa a ser adicionada.
	 */
	public void adicionaTarefa(String cpf, TarefaInterface tarefa) {
		this.pr.recuperarPessoa(cpf).adicionaTarefa(tarefa);
	}

	/**
	 * Cadastra um aluno no sistema, cada aluno possui um cpf, caso o cpf já esteja cadastrado
	 * esse aluno/pessoa será sobrescrita.
	 * 
	 * @param cpf O cpf do aluno.
	 * @param nome O nome do Aluno.
     * @param matr A matricula do Aluno.
     * @param periodo O periodo do Aluno.
     * @param habilidades As habilidades que o aluno possui.
	 */
	public void cadastrarAluno(String cpf, String nome, String matr, int periodo, String[] habilidades) {
		this.vp.valida(cpf, nome, matr, periodo, habilidades);
		this.pr.adicionarPessoa(new Pessoa(cpf, nome, habilidades, new Aluno(matr, periodo)));
	}

	/**
	 * Cadastra um professor no sistema, cada professor possui um cpf, caso o cpf já esteja cadastrado
	 * esse professor/pessoa será sobrescrita.
	 * 
	 * @param cpf O cpf do professor.
     * @param nome O nome do professor.
     * @param siape A siape do professor.
     * @param disciplinas As disciplinas que o professor possui.
     * @param habilidades As habilidades que o professor possui.
	 */
	public void cadastrarProfessor(String cpf, String nome, String siape, String[] disciplinas, String[] habilidades) {
		this.vp.valida(cpf, nome, siape, disciplinas, habilidades);
		this.pr.adicionarPessoa(new Pessoa(cpf, nome, habilidades, new Professor(siape, disciplinas)));	
	}

	/**
	 * Altera a função de uma pessoa para professor.
	 * 
	 * @param cpf O cpf do professor.
     * @param siape A siape do professor.
     * @param disciplinas As disciplinas que o professor possui.
	 */
	public void definirFuncaoProfessor(String cpf, String siape, String[] disciplinas) {
		this.vp.valida(cpf, siape, disciplinas);
		this.pr.recuperarPessoa(cpf).definiFuncao(new Professor(siape, disciplinas));
	}

	/**
	 * Altera a função de uma pessoa para aluno.
	 * 
	 * @param cpf O cpf do Aluno.
     * @param matr A matricula do Aluno.
     * @param periodo O periodo do Aluno.
	 */
	public void definirFuncaoAluno(String cpf, String matr, int periodo) {
		this.vp.valida(cpf, matr, periodo);
		this.pr.recuperarPessoa(cpf).definiFuncao(new Aluno(matr, periodo));
	}

	/**
	 * Metodo que retorna o nivel de uma pessoa.
	 * 
	 * @param cpf O cpf da pessoa.
	 * @return O valor do nivel da pessoa.
	 */
	public int pegarNivel(String cpf) {
		this.vp.valida(cpf);
		return this.pr.recuperarPessoa(cpf).getNivel();
	}

	/**
	 * Remove a funcao de uma pessoa.
	 * 
	 * @param cpf O cpf da pessoa.
	 */
	public void removerFuncao(String cpf) {
		this.vp.valida(cpf);
		this.pr.recuperarPessoa(cpf).removeFuncao();
	}

	/**
	 * Listar todas as pessoas do sistema.
	 * 
	 * @return Lista de todas as pessoas armazenadas no sistema.
	 */
	public String[] listarPessoas() {
		return this.pr.listaPessoas();
	}
	
	/**
	 * Faz uma busca de pessoas no repositorio do sistema.
	 * 
	 * @param termos termos a serem buscados
	 * @return Pessoas encontradas
	 */
	public Set<Pessoa> buscaPessoa(String[] termos) {
		return this.pr.buscaPessoa(termos);
	}

}
