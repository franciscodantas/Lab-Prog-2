package sapo.tarefas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import sapo.pessoas.Pessoa;

/**
 * Classe que representa uma Tarefa Gerencial. Ela pode conter varias tarefas, inclusive outras tarefas gerenciais.
 * 
 * @author José Arthur
 *
 */
public class TarefaGerencial implements TarefaInterface, Comparable<TarefaGerencial>{
	
	/**
	* id - O id da tarefa.
	* nome - O nome da tarefa.
	* habilidades - A lista de habilidades da tarefa.
	* qtdHoras - A quantidade de horas que a tarefa foi executada.
	* pessoasResponsaveis - O mapa que associa a(s) pessoa(s) a tarefa.
	* estadoTarefa - O estado da tarefa. Atributo que controla se a tarefa esta concluida ou não.
	* validador - O validador que será usado.
	* tarefas - Mapa de tarefas que irá conter todas as tarefas associada a tarefa gerencial.
	*/
	private String id;
    private String nome;
    private List<String> habilidades;
    private int qtdHoras;
    private Map<String, Pessoa> pessoasResponsaveis;
    private boolean estadoTarefa;
    private ValidadorTarefa validador;
	private Map<String, TarefaInterface> tarefas;
	
	/**
    * Cria uma tarefa Gerencial a partir de um código(id), nome, habilidades e um mapa de tarefas.
    * A quantidade de horas da tarefa se inicializa com 0 e o estado da tarefa se inicializa com
    * true representando que a tarefa está aberta.
    * 
    * @param id O id da tarefa.
    * @param nome O nome da tarefa.
    * @param habilidades A lista de habilidades da tarefa.
    * @param tarefas Mapa de tarefas.
    */
	public TarefaGerencial(String id, String nome, String[] habilidades, Map<String, TarefaInterface> tarefas) {
		if(id.isBlank() || nome.isBlank()){
    		throw new IllegalArgumentException("Id ou nome não pode ser vazio");
    	} 
		
    	if (id == null || nome == null || habilidades == null || tarefas == null) {
    		throw new NullPointerException("Id, nome, habilidades ou tarefas não pode ser nulo");
    	}
    	
		this.id = id;
		this.nome = nome;
		this.tarefas = new HashMap<String, TarefaInterface>();
		this.habilidades = adicionaHabilidades(habilidades);
		this.qtdHoras = 0;
		this.estadoTarefa = true;
		this.pessoasResponsaveis = new HashMap<>();
		this.validador = new ValidadorTarefa();
	}
	
	/**
	 * Adiciona as habildades padrões da tarefa gerencial.
	 * 
	 * @param habilidades Habilidades a serem adicionadas.
	 * @return Retorna uma lista das habiliades padrões.
	 */
	private List<String> adicionaHabilidades(String[] habilidades) {
		List<String> listaHabilidades = new ArrayList<String>();
		
		listaHabilidades.add("Gestão");
		for(String habilidade: habilidades) {
			listaHabilidades.add(habilidade);
		}
		for(TarefaInterface tarefa: this.tarefas.values()) { 
			String[] habilidadesTarefas = tarefa.getHabilidades();
			for(String habilidade: habilidadesTarefas) {
				if(!listaHabilidades.contains(habilidade)) {
					listaHabilidades.add(habilidade);
				}
			}
		}
		return listaHabilidades;
	}
    
    /**
     * Exibe uma representação textual da tarefa, contendo a atividade à qual ela pertence,
     * seu nome e código, habilidades e a equipe de pessoas responsáveis por ela.
     * 
     * @return uma representacao textal da tarefa.
     */
    public String toString() {
    	return this.nome + " - " + this.id
    			+ "\n" + exibirHabilidades()
    			+ "\n(" + this.qtdHoras + " hora(s) executada(s))"
    			+ "\n===\nEquipe:\n"
    			+ exibirPessoas()
    			+ "\n===\nTarefas:\n"
    			+ exibirTarefas();
    }
    
    /**
     * Exibe as pessoas responsaveis da tarefa gerencial.
     * 
     * @return Retorna uma string com as pessoas responsáveis.
     */
    private String exibirPessoas() {
    	String pessoas = "";
    	for(String cpf: this.pessoasResponsaveis.keySet()) {
    		pessoas += this.pessoasResponsaveis.get(cpf).getNome() + " - " + cpf + "\n";
    	}
    	return pessoas.trim();
	}
    
    /**
     * Exibe as tarefas relacionadas a tarefa gerencial.
     * 
     * @return Retorna uma String contendo essas tarefas.
     */
	private String exibirTarefas() {
    	String tarefas = "";
    	for(String key: this.tarefas.keySet()) {
    		if(this.tarefas.get(key).isEstadoTarefa()) {
    			tarefas += this.tarefas.get(key).getNome() + " - " + this.tarefas.get(key).getId() + "\n";
    		}	
    	}
    	return tarefas.trim();
    }
	
	/**
	 * Exibe as habildades requeridas da tarefa gerencial.
	 * 
	 * @return Retorna uma string contendo essas habilidades.
	 */
    private String exibirHabilidades() {
    	String habilidades = "";
    	for(int i = 0; i < this.habilidades.size(); i++) {
    		habilidades += this.habilidades.get(i);
    		if(i < this.habilidades.size() - 1) {
    			habilidades += ", ";
    		}
    	}
    	return habilidades.trim();
    }
    
    /**
     * Remove uma tarefa das tarefas contidas em tarefa gerencial.
     * 
     * @param idTarefa id da tarefa a ser removida.
     */
    public void removerTarefa(String idTarefa) {
    	Objects.requireNonNull(tarefas.get(idTarefa), "Tarefa não existe");
    	this.tarefas.remove(idTarefa);
    }
    
    /**
     * Conta o total de tarefas não gerenciais dentro da tarefa gerencial. Sejam elas adicionadas diretamente ou indiretamente.
     * 
     * @return Retorna a quantidade de tarefas.
     */
    public int contarTarefas() {
    	int count = 0;
    	for(TarefaInterface tarefa: tarefas.values()) {
    		if(tarefa instanceof TarefaGerencial) {
    			count += ((TarefaGerencial) tarefa).contarTarefas();
    		}else {
    			count += 1;
    		}
    	}
    	return count;
    }
    
    /**
     * Muda o nome da atividade.
     * 
     * @param nome nome a ser mudado.
     */
	@Override
	 public void setNome(String nome) {
    	this.validador.valida(nome);
    	this.nome = nome;
    }

    /**
     * Adiciona horas a tarefa. Caso seja informado uma hora invalida ou o estado da tarefa esteja
     * concluido um exceção será lançada.
     * 
     * @param horas A quantidade de horas a ser adicionada.
     */
	@Override
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
	@Override
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
	 * Pega o id da tarefa gerencial.
	 */
	@Override
	public String getId() {
		return this.id;
	}


	/**
     * Metodo que retorna o estado atual da tarefa atraves de um booleano. Se a tarefa estiver aberta
     * será retornado um true, se a tarefa estiver fechada será retornado um false.
     * 
     * @return um booleano indicando se a tarefa esta aberta ou fechada/concluida.
     */
	@Override
    public boolean isEstadoTarefa() {
		return estadoTarefa;
	}
	
	/**
	 * Pega o nome da tarefa gerencial.
	 */
	@Override
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Adiciona uma tarefa a tarefa gerencial.
	 * 
	 * @param idTarefa Id da tarefa a ser adicionada.
	 * @param tarefa a tarefa que irá ser adicionada.
	 */
	public void adicionaTarefa(String idTarefa, TarefaInterface tarefa) {
		if (tarefa.isEstadoTarefa() == false) {
			throw new IllegalStateException("Tarefa já concluída, não pode ser adicionada");
		}else if(tarefa.getId() == this.id) {
			throw new IllegalStateException("Não se pode adicionar a mesma tarefa");
		}else if(tarefa instanceof TarefaGerencial) {
			if(((TarefaGerencial) tarefa).recuperaTarefa(this.id) != null) {
				throw new IllegalStateException("Não se pode adicionar a mesma tarefa");
			}
		}
			
		this.tarefas.put(idTarefa, tarefa);
	}
	
	/**
	 * Recupera uma tarefa.
	 * 
	 * @param idTarefa Id da tarefa a ser recuperada.
	 * @return Retorna A tarefa.
	 */
	public TarefaInterface recuperaTarefa(String idTarefa) {
		this.validador.valida(idTarefa);
		return this.tarefas.get(idTarefa);
	}

	/**
	 * Remove uma pessoa responsável pela tarefa gerencial.
	 * 
	 * @param cpf Cpf da pessoa a ser removida.
	 */
	@Override
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
     * Associa uma pessoa a tarefa. Uma pessoa não pode ser um objeto nulo. Caso seja informado uma
     * pessoa invalida uma exceção será lançada.
     * Se o estado da tarefa esteja concluido uma exceção será lançada.
     * 
     * @param cpf O cpf da pessoa.
     * @param pessoa A pessoa a ser associada.
     */
	@Override
    public void associarPessoa(String cpf, Pessoa pessoa) {
    	this.validador.valida(cpf);
    	if(this.estadoTarefa) {
    		this.pessoasResponsaveis.put(cpf, pessoa);
    	} else {
    		throw new IllegalStateException("A tarefa está concluida. Seus responsaveis não podem ser alteradas");
    	}
    }
	
	/**
	 * Pega todas as pessoas responsáveis pela tarefa gerencial.
	 */
	@Override
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
	 * Pega a quantidade de horas da tarefa gerencial.
	 */
	@Override
	public int getQtdHoras() {
		return this.qtdHoras;
	}


    
    /**
     * Conclui uma tarefa. Uma tarefa concluída não pode ter suas horas ou responsáveis alterados.
     */
    @Override
    public void concluirTarefa() {
    	this.estadoTarefa = false;
    	for(TarefaInterface tarefa: this.tarefas.values()) {
    		tarefa.concluirTarefa();
    	}
    } 

	@Override
	public String[] getMetadados() {
		return this.nome.split(" ");
	}
	
	/**
	 * Pega as habilidades da tarefa gerencial.
	 */
	@Override
	public String[] getHabilidades() {
		String[] habilidades = (String[]) this.habilidades.toArray();
		return habilidades;
	}

	/**
	 * Pega a quantidade de pessoas responsáveis pela tarefa gerencial.
	 */
	@Override
	public int quantDePessoas() {
		return this.pessoasResponsaveis.size();
	}

	@Override
	public int compareTo(TarefaGerencial o) {
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
 
}
