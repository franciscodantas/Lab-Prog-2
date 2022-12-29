package sapo.tarefas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import sapo.pessoas.Pessoa;

/**
 * Classe que representa uma tarefa responsavel por ações pontuais sendo realizadas em uma atividade.
 * 
 * @author Thayane Barros e José Arthur
 *
 */
public class Tarefa implements Comparable<Tarefa>, TarefaInterface{
	
	/**
	 * id - O id da tarefa.
	 * nome - O nome da tarefa.
	 * habilidades - A lista de habilidades da tarefa.
	 * nomeAt - O nome da atividade ao qual a tarefa esta associada.
	 * qtdHoras - A quantidade de horas que a tarefa foi executada.
	 * pessoasResponsaveis - O mapa que associa a(s) pessoa(s) a tarefa.
	 * estadoTarefa - O estado da tarefa. Atributo que controla se a tarefa esta concluida ou não.
	 * validador - O validador que será usado.
	 */
	protected String id;
    protected String nome;
    protected String[] habilidades;
    private String nomeAt;
    protected int qtdHoras;
    private Map<String, Pessoa> pessoasResponsaveis;
    private boolean estadoTarefa;
    private ValidadorTarefa validador;

    /**
     * Cria uma tarefa a partir de um código(id), nome e habilidades.
     * A quantidade de horas da tarefa se inicializa com 0 e o estado da tarefa se inicializa com
     * true representando que a tarefa está aberta.
     * 
     * @param id O id da tarefa.
     * @param nome O nome da tarefa.
     * @param habilidades A lista de habilidades da tarefa.
     * @param nomeAtividade O nome da atividade na qual a tarefa esta associada.
     */
    public Tarefa(String id, String nome, String[] habilidades, String nomeAtividade) {
    	if(id.isBlank() || nome.isBlank() || nomeAtividade.isBlank()) {
    		throw new IllegalArgumentException("Id ou nome não pode ser vazio");
    	} 
    	if (id == null || nome == null || habilidades == null || nomeAtividade == null) {
    		throw new NullPointerException("Id, nome ou habilidade não pode ser nulo");
    	}
    		
    	this.id = id;
    	this.nome = nome;
    	this.habilidades = habilidades;
    	this.nomeAt = nomeAtividade;
    	this.qtdHoras = 0;
    	this.pessoasResponsaveis = new HashMap<>();
    	this.estadoTarefa = true;
    	this.validador = new ValidadorTarefa();
    }

    /**
     * Altera o nome da tarefa. Caso seja informado um nome nulo ou vazio será lançada uma exceção.
     * 
     * @param nome O novo nome da tarefa.
     */
    public void setNome(String nome) {
    	this.validador.valida(nome);
    	this.nome = nome;
    }

    /**
     * Altera as habilidades da tarefa. Caso seja informado uma lista de habilidades nulo será lançada uma exceção.
     * 
     * @param habilidades A nova lista de habilidades da tarefa.
     */
    public void setHabilidades(String[] habilidades) {
    	Objects.requireNonNull(habilidades, "Habilidades não pode ser nulo");
    	this.habilidades = habilidades;
    }
    
    /**
     * Adiciona horas a tarefa. Caso seja informado uma hora invalida ou o estado da tarefa esteja
     * concluido um exceção será lançada.
     * 
     * @param horas A quantidade de horas a ser adicionada.
     */
    public void adicionarHorasTarefa(int horas) {
    	this.validador.validaHoras(horas);
    	if(this.estadoTarefa) {
    		this.qtdHoras += horas;
    	} else {
    		throw new IllegalStateException("A tarefa está concluida. Suas horas não podem ser alteradas");
    	}
    	
    }

    /**
     * Remove horas da tarefa. Caso seja informado uma hora invalida ou a quantidade de horas a ser
     * removida for maior que a quantidade de horas da tarefa uma exceção será lançada.
     * Se o estado da tarefa esteja concluido uma exceção será lançada.
     * 
     * @param horas A quantidade de horas a ser removida.
     */
    public void removerHorasTarefa(int horas) {
    	this.validador.validaHoras(horas);
    	if(!(this.qtdHoras < horas)) {
    		if(this.estadoTarefa) {
    			this.qtdHoras -= horas;
    		} else {
    			throw new IllegalStateException("A tarefa está concluida. Suas horas não podem ser alteradas");
    		}	
    	} else {
    		throw new IllegalArgumentException("Quantidades de Horas Invalidas");
    	}
    }

    /**
     * Associa uma pessoa a tarefa. Uma pessoa não pode ser um objeto nulo. Caso seja informado uma
     * pessoa invalida uma exceção será lançada.
     * Se o estado da tarefa esteja concluido uma exceção será lançada.
     * 
     * @param cpf O cpf da pessoa.
     * @param pessoa A pessoa a ser associada.
     */
    public void associarPessoa(String cpf, Pessoa pessoa) {
    	this.validador.valida(cpf);
    	if(this.estadoTarefa) {
    		this.pessoasResponsaveis.put(cpf, pessoa);
    	} else {
    		throw new IllegalStateException("A tarefa está concluida. Seus responsaveis não podem ser alteradas");
    	}
    }

    /**
     * Remove uma pessoa da tarefa. Caso seja informado um cpf invalido ou o estado da tarefa esteja
     * concluido uma exceção será lançada.
     * 
     * @param cpf O cpf da pessoa.
     */
    public void removerPessoa(String cpf) {
    	this.validador.valida(cpf);
    	if(this.estadoTarefa) {
    		if(this.pessoasResponsaveis.containsKey(cpf)) {
    			this.pessoasResponsaveis.remove(cpf);
    		}
    		else {
    			throw new IllegalArgumentException("Não pode remover uma pessoa não associada");
    		}
    	} else {
    		throw new IllegalStateException("A tarefa está concluida. Seus responsaveis não podem ser alteradas");
    	}
    }

    /**
     * Conclui uma tarefa. Uma tarefa concluída não pode ter suas horas ou responsáveis alterados.
     */
    public void concluirTarefa() {
    	this.estadoTarefa = false;
    } 
    
    /**
     * Metodo que retorna o estado atual da tarefa atraves de um booleano. Se a tarefa estiver aberta
     * será retornado um true, se a tarefa estiver fechada será retornado um false.
     * 
     * @return um booleano indicando se a tarefa esta aberta ou fechada/concluida.
     */
    public boolean isEstadoTarefa() {
		return estadoTarefa;
	}

	/**
     * Metodo que retorna uma representação textual das habilidades.
     * 
     * @return uma representacao textal das habilidades.
     */
    private String exibirHabilidades() {
    	String habilidades = "";
    	for(int i = 0; i < this.habilidades.length; i++) {
    		habilidades += this.habilidades[i];
    		if(i < this.habilidades.length - 1) {
    			habilidades += ", ";
    		}
    	}
    	return habilidades.trim();
    }
    
    /**
     * Metodo que retorna uma representação textual das pessoas associadas a tarefa.
     * 
     * @return uma representacao textal das pessoas no formato "Nome Da Pessoa - cpf".
     */
    private String exibirPessoas() {
    	String pessoas = "";
    	for(String cpf: this.pessoasResponsaveis.keySet()) {
    		pessoas += this.pessoasResponsaveis.get(cpf).getNome() + " - " + cpf + "\n";
    	}
    	return pessoas.trim();
    }
    
    @Override
    /**
     * Exibe uma representação textual da tarefa, contendo a atividade à qual ela pertence,
     * seu nome e código, habilidades e a equipe de pessoas responsáveis por ela.
     * 
     * @return uma representacao textal da tarefa.
     */
    public String toString() {
    	return this.nome + " - " + this.id
    			+ "\n- " + this.nomeAt
    			+ "\n" + exibirHabilidades()
    			+ "\n(" + this.qtdHoras + " hora(s) executada(s))"
    			+ "\n===\nEquipe:\n"
    			+ exibirPessoas();
    }
    
    /**
     * Metodo que retorna o nome da Tarefa.
     * 
     * @return O nome da tarefa.
     */
    public String getNome() {
    	return this.nome;
    }
    
    /**
     * Metodo que retorna o id da tarefa.
     * 
     * @return o id da tarefa.
     */
    public String getId() {
    	return this.id;
    }
    
    /**
     * Retorna um arrays com as habilidades exigidas na tarefa.
     * 
     * @return array de habilidades.
     */
    public String[] getHabilidades() {
    	return this.habilidades;
    }
    
    /**
     * Retorna a quantidade de horas da tarefa.
     * 
     * @return A quantidade de horas da tarefa.
     */
    public int getQtdHoras() {
    	return this.qtdHoras;
    }
    
    /**
     * Retorna um arrays com as pessoas responsaveis pela tarefa.
     * 
     * @return array das pessoas responsaveis.
     */
    public ArrayList<Pessoa> getPessoasResponsaveis() {
    	ArrayList<Pessoa> pessoasResponsaveis = new ArrayList<>();
    	for(Pessoa pessoa: this.pessoasResponsaveis.values()) {
    		if(pessoa == null) {
    			continue;
    		}
    		else {
    			pessoasResponsaveis.add(pessoa);
    		}
    	}
    	return pessoasResponsaveis;
    }
    
    /**
     * Retornas os dados importantes para uma busca de tarefas, como: seu nome,
     * habilidades e id.
     * 
     * @return metadados de uma tarefas.
     */
    public String[] getMetadados() {
		return nome.split(" ");
	}

	@Override
	/**
	 * Compara a tarefa atual com outra tarefa, ordenando pelo seu identificador numerico. 
	 */
	public int compareTo(Tarefa o) {
		int posicaoO1 = Integer.parseInt(this.getId().substring(6));
		int posicaoO2 = Integer.parseInt(o.getId().substring(6));
		if(posicaoO1 == posicaoO2) {
			return 0;
		}
		else if (posicaoO1 > posicaoO2){
			return 1;
		}
		else {
			return -1;
		}
	}
	
	/**
	 * Retorna quantidade de pessoas cadastradas nessa tarefa.
	 * 
	 * @return quantidade de pessoas.
	 */
	public int quantDePessoas() {
		return this.pessoasResponsaveis.size();
	}
}
