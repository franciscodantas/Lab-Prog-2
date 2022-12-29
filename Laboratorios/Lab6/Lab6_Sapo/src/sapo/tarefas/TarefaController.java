package sapo.tarefas;

/**
 * TarefaController é responsável por receber requisições do usuário e do
 * sistema validar tais requisições e converter em operações lógicas (TarefaService).
 * 
 * @author Thayane Barros - 121110604
 *
 */
public class TarefaController {
	
	/**
	 * ts - A tarefa service que será controlado.
	 * validador - O validador que será usado.
	 */
	private TarefaService ts;
    private ValidadorTarefa validador;

    /**
     * Construtor padrão de TarefaController. Configura que AtividadeService será usado e inicializa a
     * classe de validacao das tarefas.
     * 
     * @param ts A tarefa service que será controlado.
     */
    public TarefaController(TarefaService ts) {
    	this.ts = ts;
    	this.validador = new ValidadorTarefa();
    }

    /**
     * Cadastra uma tarefa, validando o id da atividade e o nome para objetos não nulos e vazios.
     * E validando as habilidades para um objeto não nulo.
     * 
     * @param atividadeId O id da atividade.
     * @param nome O nome da tarefa a ser cadastrada.
     * @param habilidades As habilidades recomendadas de cada tarefa a ser cadastrada.
     * @return O código da tarefa.
     */
    public String cadastrarTarefa(String atividadeId, String nome, String[] habilidades) {
    	this.validador.valida(atividadeId, nome, habilidades);
    	return this.ts.cadastrarTarefa(atividadeId, nome, habilidades);
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
    	this.validador.valida(atividadeId, nome, habilidades, idTarefas);
    	return this.ts.cadastrarTarefaGerencial(atividadeId, nome, habilidades, idTarefas);
    }

    /**
     * Altera o nome de um tarefa, validando o ID da tarefa e o novo nome para objetos não nulos
	 * e vazios.
	 * 
     * @param idTarefa O id da tarefa.
     * @param novoNome O novo nome da tarefa a ser alterada.
     */
    public void alterarNomeTarefa(String idTarefa, String novoNome) {
    	this.validador.valida(idTarefa, novoNome);
    	this.ts.alterarNomeTarefa(idTarefa, novoNome);
    }

    /**
     * Altera as habilidades de um tarefa, validando o ID da tarefa para objeto não nulos
	 * e vazio. E validando as habilidades para um objeto não nulo.
	 * 
     * @param idTarefa O id da tarefa.
     * @param habilidades As habilidades da tarefa a ser alterada.
     */
    public void alterarHabilidadesTarefa(String idTarefa, String[] habilidades) {
    	this.validador.valida(idTarefa, habilidades);
    	this.ts.alterarHabilidadesTarefa(idTarefa, habilidades);
    }

    /**
     * Adiciona uma quantidade de horas a uma tarefa, validando o ID da tarefa para um objeto não nulo
	 * e vazio. E validando a quantidade de horas a ser adicionada para verificar se são numeros maiores que 0.
	 * 
     * @param idTarefa O id da tarefa.
     * @param horas A quantidade de horas a ser adicionada na tarefa.
     */
    public void adicionarHorasTarefa(String idTarefa, int horas) {
    	this.validador.valida(idTarefa);
    	this.validador.validaHoras(horas);
    	this.ts.adicionarHorasTarefa(idTarefa, horas);
    }

    /**
     * Remove uma quantidade de horas de uma tarefa, validando o ID da tarefa para um objeto não nulo
	 * e vazio. E validando a quantidade de horas a ser removida para verificar se são numeros maiores que 0.
	 * 
     * @param idTarefa O id da tarefa.
     * @param horas A quantidade de horas a ser removida na tarefa.
     */
    public void removerHorasTarefa(String idTarefa, int horas) {
    	this.validador.valida(idTarefa);
    	this.validador.validaHoras(horas);
    	this.ts.removerHorasTarefa(idTarefa, horas);
    }

    /**
     * Conclui uma tarefa, validando o ID da tarefa para um objeto não nulo e vazio.
     * 
     * @param idTarefa O id da tarefa.
     */
    public void concluirTarefa(String idTarefa) {
    	this.validador.valida(idTarefa);
    	this.ts.concluirTarefa(idTarefa);
    }

    /**
     * Remove uma tarefa, validando o ID da tarefa para objeto não nulos e vazio.
     * 
     * @param idTarefa O id da tarefa.
     */
    public void removerTarefa(String idTarefa) {
    	this.validador.valida(idTarefa);
    	this.ts.removerTarefa(idTarefa);
    }

    /**
     * Exibi uma tarefa, validando o ID da tarefa para objeto não nulos e vazio.
     * 
     * @param idTarefa O id da tarefa.
     * @return uma representação em string da tarefa com todos os dados a ela associada,
     * incluindo a atividade à qual ela pertence, a descrição, habilidades e a equipe de
     * pessoas responsáveis.
     */
    public String exibirTarefa(String idTarefa) {
    	this.validador.valida(idTarefa);
    	return this.ts.exibirTarefa(idTarefa);
    }

    /**
     * Associa uma pessoa a uma tarefa, validando o ID da tarefa e o cpf para objetos não nulos
     * e vazios.
     * 
     * @param cpf O cpf da pessoa.
     * @param idTarefa O id da tarefa.
     */
    public void associarPessoaTarefa(String cpf, String idTarefa) {
    	this.validador.valida(idTarefa, cpf);
    	this.ts.associarPessoaTarefa(cpf, idTarefa);
    }

    /**
     * Remove uma associação de uma pessoa a tarefa, validando o ID da tarefa e o cpf para objetos não nulos
     * e vazios.
     * 
     * @param cpf O cpf da pessoa a ser removida.
     * @param idTarefa O id da tarefa.
     */
    public void removerPessoaTarefa(String cpf, String idTarefa) {
    	this.validador.valida(idTarefa, cpf);
    	this.ts.removerPessoaTarefa(cpf, idTarefa);
    }
    
    /**
     * Adiciona uma tarefa a uma tarefa gerencial, que pode ser outra tarefa gerencial.
     * 
     * @param idTarefaGerencial id da tarefa gerencial que irá receber.
     * @param idTarefa Tarefa que será adicionada.
     */
    public void adicionarNaTarefaGerencial(String idTarefaGerencial, String idTarefa) {
    	this.validador.valida(idTarefaGerencial, idTarefa);
    	this.ts.adicionarNaTarefaGerencial(idTarefaGerencial, idTarefa);
    }
    
    /**
     * Remove uma tarefa de uma tarefa gerencial.
     * 
     * @param idTarefaGerencial id da tarefa gerencial.
     * @param idTarefa Tarefa que será adicionada.
     */
    public void removerNaTarefaGerencial(String idTarefaGerencial, String idTarefa) {
    	this.validador.valida(idTarefaGerencial, idTarefa);
    	this.ts.removeNaTarefaGerencial(idTarefaGerencial, idTarefa);
    }
    
    /**
     * Conta todas as tarefas que não são gerencias direta ou indiretamente em uma
     * tarefa gerencial.
     * 
     * @param idTarefaGerencial id da tarefa gerencial.
     * @return Quantidade de tarefas não gerenciais.
     */
    public int contarTodasTarefasNaTarefaGerencial(String idTarefaGerencial) {
    	this.validador.valida(idTarefaGerencial);
    	return this.ts.contarTodasTarefasNaTarefaGerencial(idTarefaGerencial);
    }

}
