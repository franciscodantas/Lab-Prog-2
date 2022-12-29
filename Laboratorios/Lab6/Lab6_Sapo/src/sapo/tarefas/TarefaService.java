package sapo.tarefas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import sapo.atividades.Atividade;
import sapo.atividades.AtividadeService;
import sapo.pessoas.Pessoa;
import sapo.pessoas.PessoaService;

/**
 * TarefaService é responsável pela lógica de operações sobre a tarefa. Demais
 * classes devem fazer uso desse serviço para acessar e operar sobre as tarefas.
 * 
 * @author Thayane Barros e Jose Arthur
 *
 */
public class TarefaService {
	
	/**
	 * as - A atividade service que será controlado.
	 * ps - A pessoa service que será controlado.
	 * tr - O tarefa repositorio que será controlado.
	 * validador - O validador que será usado.
	 * countTarefas - Atributo para controlar a ordem em que uma tarefa é cadastrada, criando seu codigo.
	 */
	private AtividadeService as;
    private PessoaService ps;
    private TarefaRepositorio tr;
    private ValidadorTarefa validador;
    private int countTarefas;

    /**
     * Construtor padrão de TarefaService. Configura que AtividadeService e PessoaService será usado
     * e inicializa o tarefa repositorio e a classe de validacao das tarefas.
     * 
     * @param ps A pessoa service que será controlado.
     * @param as A atividade service que será controlado.
     */
    public TarefaService(PessoaService ps, AtividadeService as) {
    	this.as = as;
    	this.ps = ps;
    	this.validador = new ValidadorTarefa();
    	this.tr = new TarefaRepositorio();
    	this.countTarefas = 0;
    	
    }
    
    /**
     * Cadastra uma tarefa no sistema, cada tarefa possui um nome e uma lista de habilidades. A tarefa também será
     * cadastrada em atividade e pessoa.
     * 
     * @param atividadeId O Id da atividade a ser cadastrada.
     * @param nome O nome da tarefa a ser cadastrada.
     * @param habilidades As habilidades recomendadas de cada tarefa a ser cadastrada.
     * @return O código da tarefa.
     */
    public String cadastrarTarefa(String atividadeId, String nome, String[] habilidades) {
    	this.validador.valida(atividadeId, nome, habilidades);
    	Atividade atividade = this.as.recuperarAtividade(atividadeId);
    	Tarefa tarefa = new Tarefa(atividadeId + "-" + countTarefas++, nome, habilidades, atividade.getNome());
    	
    	atividade.adicionaTarefa(tarefa.getId(), tarefa);
    	this.ps.adicionaTarefa(atividade.getCpfResponsavel(), tarefa);
    	this.tr.adicionaTarefa(tarefa.getId(), tarefa);
    	return tarefa.getId();
    }
    
    /**
     * Cadastra uma nova tarefa gerencial, recebendo as habilidades recomendadas e a atividade que
     * irá receber, bem como as tarefas que serão armazenadas e o nome da tarefa gerencial.
     * 
     * @param atividadeId atividade que irá receber
     * @param nome Nome da tarefa.
     * @param habilidades Habilidade requeridas.
     * @param idTarefas Id das tarefas que serão gerenciadas.
     * @return Id da tarefa gerencial.
     */
    public String cadastrarTarefaGerencial(String atividadeId, String nome, String[] habilidades, String[] idTarefas) {
    	this.validador.valida(atividadeId, nome, habilidades);
    	
    	Map<String, TarefaInterface> tarefas = new HashMap<String, TarefaInterface>();
    	for(String id: idTarefas) {
    		tarefas.put(tr.recuperaTarefa(id).getId(), tr.recuperaTarefa(id));
    	}

    	TarefaInterface tarefaGerencial = new TarefaGerencial(atividadeId + "-" + countTarefas++, nome, habilidades, tarefas);
    	Objects.requireNonNull(this.as.recuperarAtividade(atividadeId), "Atividade não existe");
    	this.as.recuperarAtividade(atividadeId).adicionaTarefa(tarefaGerencial.getId(),tarefaGerencial);
    	String cpfPessoaResponsavel = this.as.recuperarAtividade(atividadeId).getCpfResponsavel();
    	this.ps.adicionaTarefa(cpfPessoaResponsavel, tarefaGerencial);
    	this.tr.adicionaTarefa(tarefaGerencial.getId(), tarefaGerencial);
    	return tarefaGerencial.getId();
    }

    /**
     * Altera o nome de um tarefa já cadastrada, passando um novo nome para aquela tarefa.
     * 
     * @param idTarefa O id da tarefa.
     * @param novoNome O novo nome da tarefa a ser alterada.
     */
    public void alterarNomeTarefa(String idTarefa, String novoNome) {
    	this.validador.valida(idTarefa, novoNome);
    	this.tr.recuperaTarefa(idTarefa).setNome(novoNome);
    }

    /**
     * Altera as habilidades de uma tarefa já cadastrada, passando a nova lista de habilidades,
     * que pode ser vazia.
	 * 
     * @param idTarefa O id da tarefa.
     * @param habilidades As novas habilidades.
     */
    public void alterarHabilidadesTarefa(String idTarefa, String[] habilidades) {
    	this.validador.valida(idTarefa, habilidades);
    	TarefaInterface tarefaRecuperada = this.tr.recuperaTarefa(idTarefa);
    	this.validador.validaTarefa(tarefaRecuperada);
    	((Tarefa) tarefaRecuperada).setHabilidades(habilidades);
    }

    /**
     * Adiciona uma quantidade de horas para uma tarefa. As horas devem ser inteiros positivos.
	 * 
     * @param idTarefa O id da tarefa.
     * @param horas A quantidade de horas a ser adicionada na tarefa.
     */
    public void adicionarHorasTarefa(String idTarefa, int horas) {
    	this.validador.valida(idTarefa);
    	this.validador.validaHoras(horas);
    	this.tr.recuperaTarefa(idTarefa).adicionarHorasTarefa(horas);
    }

    /**
     * Remove uma quantidade de horas de uma tarefa. As horas devem ser inteiros positivos.
     * 
     * @param idTarefa O id da tarefa.
     * @param horas A quantidade de horas a ser removida na tarefa.
     */
    public void removerHorasTarefa(String idTarefa, int horas) {
    	this.validador.valida(idTarefa);
    	this.validador.validaHoras(horas);
    	this.tr.recuperaTarefa(idTarefa).removerHorasTarefa(horas);
    }

    /**
     * Conclui uma tarefa.
     * 
     * @param idTarefa O id da tarefa.
     */
    public void concluirTarefa(String idTarefa) {
    	this.validador.valida(idTarefa);
    	this.tr.recuperaTarefa(idTarefa).concluirTarefa();
    }

    /**
     * Remove totalmente uma tarefa de uma atividade.
     * 
     * @param idTarefa O id da tarefa.
     */
    public void removerTarefa(String idTarefa) {
    	this.validador.valida(idTarefa);
    	
    	ArrayList<Pessoa> pessoasResponsaveis = this.tr.recuperaTarefa(idTarefa).getPessoasResponsaveis();
    	for(Pessoa pessoa: pessoasResponsaveis) {
    		pessoa.removeTarefa(idTarefa);
    	}
   
    	String idAtividade = idTarefa.substring(0, 5);
    	this.as.recuperarAtividade(idAtividade).removeTarefa(idTarefa);
    	this.tr.removeTarefa(idTarefa);
    }

    /**
     * Exibe uma representação textual da tarefa.
     * 
     * @param idTarefa O id da tarefa.
     * @return uma representação textual da tarefa.
     */
    public String exibirTarefa(String idTarefa) {
    	this.validador.valida(idTarefa);
    	return this.tr.recuperaTarefa(idTarefa).toString();
    }

    /**
     * Associa uma pessoa a uma tarefa, passando o cpf da pessoa a ser associado. Tarefas sera adicionado
     * em atividades e pessoas.
     * 
     * @param cpf O cpf da pessoa.
     * @param idTarefa O id da tarefa.
     */
    public void associarPessoaTarefa(String cpf, String idTarefa) {
    	this.validador.valida(idTarefa, cpf);
    	Pessoa pessoa = this.ps.recuperaPessoa(cpf);
    	TarefaInterface tarefa = this.tr.recuperaTarefa(idTarefa);
    	tarefa.associarPessoa(cpf, pessoa);
    	pessoa.adicionaTarefa(tarefa);
    }

    /**
     * Remove a associação de uma pessoa a uma tarefa.
     * 
     * @param cpf O cpf da pessoa.
     * @param idTarefa O id da tarefa.
     */
    public void removerPessoaTarefa(String cpf, String idTarefa) {
    	this.validador.valida(idTarefa, cpf);
    	Pessoa pessoa = this.ps.recuperaPessoa(cpf);
    	this.tr.recuperaTarefa(idTarefa).removerPessoa(cpf);
    	pessoa.removeTarefa(idTarefa);
    }
    
    /**
     * Adiciona uma tarefa a uma tarefa gerencial, que pode ser outra tarefa gerencial.
     * 
     * @param idTarefaGerencial id da tarefa gerencial que irá receber.
     * @param idTarefa Tarefa que será adicionada.
     */
    public void adicionarNaTarefaGerencial(String idTarefaGerencial, String idTarefa) {
    	TarefaInterface tarefaRecuperada = this.tr.recuperaTarefa(idTarefaGerencial);
    	this.validador.validaTarefaGerencial(tarefaRecuperada);
    	((TarefaGerencial)tarefaRecuperada).adicionaTarefa(idTarefa, tr.recuperaTarefa(idTarefa));
    }
    
    /**
     * Remove uma tarefa de uma tarefa gerencial.
     * 
     * @param idTarefaGerencial id da tarefa gerencial.
     * @param idTarefa Tarefa que será adicionada.
     */
    public void removeNaTarefaGerencial(String idTarefaGerencial, String idTarefa) {
    	TarefaInterface tarefaRecuperada = this.tr.recuperaTarefa(idTarefaGerencial);
    	this.validador.validaTarefaGerencial(tarefaRecuperada);
    	((TarefaGerencial)tarefaRecuperada).removerTarefa(idTarefa);
    }
    
    /**
     * Conta todas as tarefas que não são gerencias direta ou indiretamente em uma
     * tarefa gerencial.
     * 
     * @param idTarefaGerencial id da tarefa gerencial.
     * @return Quantidade de tarefas não gerenciais.
     */
    public int contarTodasTarefasNaTarefaGerencial(String idTarefaGerencial) {
    	TarefaInterface tarefaRecuperada = this.tr.recuperaTarefa(idTarefaGerencial);
    	this.validador.validaTarefaGerencial(tarefaRecuperada);
    	return ((TarefaGerencial)tarefaRecuperada).contarTarefas();
    }
    
    /**
     * Metodo que retorna o nome da tarefa de uma determinada atividade.
     * 
     * @param idAtividade O id da atividade.
     * @param idTarefa O id da tarefa.
     * @return O nome da tarefa.
     */
    public String getNome(String idAtividade, String idTarefa) {
    	this.validador.valida(idAtividade, idTarefa);
    	return this.tr.recuperaTarefa(idTarefa).getNome();
    }

    /**
     * Busca uma sugestão em TarefaRepositorio.
     * 
     * @param habilidades Habilidades procuradas.
     * @return Tarefas encontradas.
     */
	public Map<TarefaInterface, Integer> sugestao(String[] habilidades) {
		return this.tr.sugerir(habilidades);
	}
	
	/**
	 * Faz uma busca de tarefas em TarefaRepositorio.
	 * 
	 * @param termos Nome de tarefas procurado.
	 * @return Tarefas encontradas.
	 */
	public Set<TarefaInterface> busca(String[] termos) {
		return this.tr.busca(termos);
	}
}
