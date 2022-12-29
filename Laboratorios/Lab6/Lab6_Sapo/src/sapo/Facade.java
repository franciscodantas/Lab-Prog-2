package sapo;

import sapo.atividades.AtividadeController;
import sapo.atividades.AtividadeService;
import sapo.busca.BuscaController;
import sapo.busca.BuscaService;
import sapo.pessoas.PessoaController;
import sapo.pessoas.PessoaService;
import sapo.tarefas.TarefaController;
import sapo.tarefas.TarefaService;

public class Facade {
	private PessoaController pessoaController;
	private TarefaController tarefaController;
	private AtividadeController atividadeController;
	private BuscaController buscaController;
	
	public Facade() {
		var pessoasService = new PessoaService();
		var atividadeService = new AtividadeService(pessoasService);
		var tarefaService = new TarefaService(pessoasService, atividadeService);
		var buscaService = new BuscaService(pessoasService, atividadeService, tarefaService);
		
		this.pessoaController = new PessoaController(pessoasService);
		this.atividadeController = new AtividadeController(atividadeService);
		this.tarefaController = new TarefaController(tarefaService);
		this.buscaController = new BuscaController(buscaService);
	}
	
	public void cadastrarPessoa(String cpf, String nome,String[] habilidades) {
		this.pessoaController.cadastrarPessoa(cpf, nome, habilidades);
	}
	
	public String exibirPessoa(String cpf) {
		return this.pessoaController.exibirPessoa(cpf);
	}
	
	public void alterarNomePessoa(String cpf, String novoNome) {
		this.pessoaController.alterarNomePessoa(cpf, novoNome);
	}
	
	public void alterarHabilidadesPessoa(String cpf, String[] novasHabilidades) {
    	this.pessoaController.alterarHabilidadesPessoa(cpf, novasHabilidades);
    }
	
	public void removerPessoa(String cpf) {
    	this.pessoaController.removerPessoa(cpf);
    }
	
	public void adicionarComentarioPessoa(String cpf, String comentario, String autorCpf) {
    	this.pessoaController.adicionarComentarioPessoa(cpf, comentario, autorCpf);
    }
	
	public String listarComentariosPessoa(String cpf) {
		return this.pessoaController.listarComentariosPessoa(cpf);
    }
	
	public String cadastrarAtividade(String nome, String descricao, String cpf){
        return this.atividadeController.cadastrarAtividade(nome, descricao, cpf);
    }

    public void encerrarAtividade(String atividadeId){
        this.atividadeController.encerrarAtividade(atividadeId);
    }

    public void desativarAtividade(String atividadeId){
        this.atividadeController.desativarAtividade(atividadeId);
    }

    public void reabrirAtividade(String atividadeId){
        this.atividadeController.reabrirAtividade(atividadeId);
    }
    
    public String exibirAtividade(String atividadeId){
        return this.atividadeController.exibirAtividade(atividadeId);
    }

    public void alterarDescricaoAtividade(String atividadeId, String descricao){
        this.atividadeController.alterarDescricaoAtividade(atividadeId, descricao);
    }
    
    public void alterarResponsavelAtividade(String atividadeId, String cpf){
        this.atividadeController.alterarResponsavelAtividade(atividadeId, cpf);
    }
    
	public void adicionarAtividade(String nome, String descricao, String cpf) {
		this.atividadeController.cadastrarAtividade(nome, descricao, cpf);
	}
	
	public String cadastrarTarefa(String atividadeId, String nome, String[] habilidades) {
		return this.tarefaController.cadastrarTarefa(atividadeId, nome, habilidades);
	}
	
	public void alterarNomeTarefa(String idTarefa, String novoNome) {
		this.tarefaController.alterarNomeTarefa(idTarefa, novoNome);
	}
	
	public void alterarHabilidadesTarefa(String idTarefa, String[] habilidades) {
		this.tarefaController.alterarHabilidadesTarefa(idTarefa, habilidades);
	}
	
	public void adicionarHorasTarefa(String idTarefa, int horas) {
		this.tarefaController.adicionarHorasTarefa(idTarefa, horas);
	}
	
	public void removerHorasTarefa(String idTarefa, int horas) {
		this.tarefaController.removerHorasTarefa(idTarefa, horas);
	}
	
	public void concluirTarefa(String idTarefa) {
		this.tarefaController.concluirTarefa(idTarefa);
	}
	
	public void removerTarefa(String idTarefa) {
		this.tarefaController.removerTarefa(idTarefa);
	}
	
	public String exibirTarefa(String idTarefa) {
		return this.tarefaController.exibirTarefa(idTarefa);
	}
	
	public void associarPessoaTarefa(String cpf, String idTarefa) {
		this.tarefaController.associarPessoaTarefa(cpf, idTarefa);
	}
	
	public void removerPessoaTarefa(String cpf, String idTarefa) {
		this.tarefaController.removerPessoaTarefa(cpf, idTarefa);
	}
	
	public String[] exibirPessoas(String consulta) {
		return this.buscaController.exibirPessoas(consulta);
	}
	
	public String[] buscarAtividades(String consulta) {
		return this.buscaController.buscarAtividades(consulta);
	}
	
	public String[] buscarTarefas(String nome) {
		return this.buscaController.buscarTarefas(nome);
	}
	
	public String[] buscarTarefas(String idAtividade, String nome) {
		return this.buscaController.buscarTarefas(idAtividade, nome);
	}
	
	public String[] sugerirTarefas(String cpf) {
		return this.buscaController.sugerirTarefas(cpf);
	}
	
	public String[] buscarMaisRecentes(int nBuscas) {
		return this.buscaController.buscarMaisRecentes(nBuscas);
	}
	
	public String[] exibirHistoricoBusca(int index) {
		return this.buscaController.exibirHistoricoBusca(index);
	}

	public void cadastrarAluno(String cpf, String nome, String matr, int periodo, String[] habilidades) {
		this.pessoaController.cadastrarAluno(cpf, nome, matr, periodo, habilidades);
	}
	
	public void cadastrarProfessor(String cpf, String nome, String siape, String[] disciplinas, String[] habilidades) {
		this.pessoaController.cadastrarProfessor(cpf, nome, siape, disciplinas, habilidades);
	}
	
	public void definirFuncaoProfessor(String cpf, String siape, String[] disciplinas) {
		this.pessoaController.definirFuncaoProfessor(cpf, siape, disciplinas);
	}
	
	public void definirFuncaoAluno(String cpf, String matr, int periodo) {
		this.pessoaController.definirFuncaoAluno(cpf, matr, periodo);
	}
	
	public int pegarNivel(String cpf) {
		return this.pessoaController.pegarNivel(cpf);
	}
	
	public void removerFuncao(String cpf) {
		this.pessoaController.removerFuncao(cpf);
	}
	
	public String[] listarPessoas() {
		return this.pessoaController.listarPessoas();
	}
	
	public String cadastrarTarefaGerencial(String atividadeId, String nome, String[] habilidades, String[] idTarefas) {
		return this.tarefaController.cadastrarTarefaGerencial(atividadeId, nome, habilidades, idTarefas);
	}

	public void adicionarNaTarefaGerencial(String idTarefaGerencial, String idTarefa) {
		this.tarefaController.adicionarNaTarefaGerencial(idTarefaGerencial, idTarefa);
	}

	public void removerDaTarefaGerencial(String idTarefaGerencial, String idTarefa) {
		this.tarefaController.removerNaTarefaGerencial(idTarefaGerencial, idTarefa);
	}

	public int contarTodasTarefasNaTarefaGerencial(String idTarefaGerencial) {
		return this.tarefaController.contarTodasTarefasNaTarefaGerencial(idTarefaGerencial);
	}

}
