package sapo.atividades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sapo.pessoas.Pessoa;
import sapo.tarefas.TarefaInterface;

/**
 * Classe que representa uma atividade.
 * 
 * @author José Arthur
 *
 */
public class Atividade implements Comparable<Atividade>{
	
	/**
	 * nome - nome da tarefa
	 * descricao - descrição da atividade a ser realizada
	 * responsavel - o responsavel por essa atividade
	 * estadoAtividade - o estado atual da atividade, podendo ser: encerrada, aberta ou desativada
	 * id - id da atividade
	 * tarefas - tarefas que estão dentro da atividade
	 */
    private String nome;
    private String descricao;
    private Pessoa responsavel;
    private String estadoAtividade;
    private String id;
    private Map<String, TarefaInterface> tarefas;

    /**
     * Cria uma atividade a partir de um código(id), nome e descrição da atividade. E uma pessoa responsável pela atividade.
     * O estado da atividade se incicia como aberta.
     * 
     * @param id O id da atividade.
     * @param nome Nome da atividade.
     * @param descricao Descrição da atividade
     * @param pessoa Pessoa que irá ser responsável pela atividade.
     */
    public Atividade(String id, String nome, String descricao, Pessoa pessoa){
        this.nome = nome;
        this.descricao = descricao;
        this.id = id;
        this.estadoAtividade = "aberta";
        this.responsavel = pessoa;
        this.tarefas = new HashMap<>();
    }

    /**
     * Altera o estado da tarefa.
     * Não é possível abrir uma atividade já aberta ou encerrada. 
     * Não é possível desativar uma atividade encerrada e vice versa.
     * Não é possível desativar ou encerrar uma atividade com tarefas pendentes.
     * 
     * @param estado Estado que irá ser atribuido a atividade.
     */
    public void alterarEstado(String estado){
    	switch(estado) {
    		case "aberta":
    			if(estadoAtividade.equals("aberta")){
                    throw new IllegalStateException("Atividade já aberta ou encerrada");
                }else{
                    this.estadoAtividade = estado;
                }
    			break;
    		case "desativada":
    			if(tarefasPendentes() == true) {
    				throw new IllegalStateException("Atividade tem tarefas pendentes");
    				}
    			else if(estadoAtividade.equals("desativada") || estadoAtividade.equals("encerrada") ){
                    throw new IllegalStateException("Atividade já desativada ou encerrada");
                }else{
                    this.estadoAtividade = estado;
                } 
    			break;
    		case "encerrada":
    			if(tarefasPendentes() == true) {
    				throw new IllegalStateException("Atividade tem tarefas pendentes");
    				}
    			else if(estadoAtividade.equals("encerrada") ){
                    throw new IllegalStateException("Atividade já encerrada");
                }else{
                    this.estadoAtividade = estado;
                }
    			break;
    	}
    }
    
    /**
     * Método que verifica de há tarefas pendentes da atividade.
     * 
     * @return Retorna falso caso não haja tarefas pendentes. Retorna true caso contrário.
     */
    private boolean tarefasPendentes() {
    	for(TarefaInterface tarefa: this.tarefas.values()) {
    		if(tarefa.isEstadoTarefa() == true) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Altera a descrição da atividade.
     * 
     * @param descricao Descrição que irá ser atribuida a atividade.
     */
    public void alterarDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Altera responsável da atividade.
     * Remove também a responsabilidade da tarefa.
     * 
     * @param pessoa Pessoa que irá ser atribuida como responsável da atividade.
     */
    public void alterarResponsavelAtividade(Pessoa pessoa){
    	this.responsavel.removeComoResponsavel();
        this.responsavel = pessoa;
    }
    
    /**
     * Recupera uma tarefa contida em atividade.
     * 
     * @param idTarefa Id da tarefa desejada para ser recuperada.
     * @return Retorna a tarefa.
     */
    public TarefaInterface recuperaTarefa(String idTarefa){
        return tarefas.get(idTarefa);
    }

    /**
     * Remove uma tarefa contida em atividade.
     * @param idTarefa Id da tarefa desejada para ser removida.
     */
    public void removeTarefa(String idTarefa){
        tarefas.remove(idTarefa);
    }
    
    /**
     * Pega o estado atual da atividade.
     * @return Retorna o estado atual da atividade.
     */
    public String getEstadoTarefa(){
        return this.estadoAtividade;
    }
	
    /**
     * Pega o responsável atual da atividade.
     * @return Retorna o nome atual do responsável da atividade
     */
	public String getCpfResponsavel() {
		return this.responsavel.getCpf();
	}
	
	/**
	 * Pega as partes mais importantes de atividade que a possa representar como parte ou
	 * todo de id ou descrição.
	 * @return partes que identificam uma atividade.
	 */
	public ArrayList<String> getMetadados(){
		ArrayList<String> dados = new ArrayList<String>();
		dados.add(this.id);
		String[] partesId = this.id.split("-");
		String[] partesDescricao = this.descricao.split(" ");
		for(String str: partesId) {
			dados.add(str.toLowerCase());
		}
		for(String str: partesDescricao) {
			dados.add(str.toLowerCase());
		}
		return dados;
	}

	/**
	 * Representação textual da tarefa.
	 */
    public String toString(){
        return 
        id + ":" + nome + "\n"
        + "Responsável: " + responsavel.getNome() + "-" + responsavel.getCpf() + "\n"
        + "===" + "\n"
        + descricao + "\n"
        + "===" + "\n"
        + "Tarefas: " + tarefasConcluidas() + "/" + this.tarefas.size() + "\n"
        + tresUltimasTarefas();
    }
    
    /**
     * Método que retorna as tarefas concluídas.
     * 
     * @return Retorno das tarefas concluídas.
     */
    private int tarefasConcluidas() {
    	int concluidas = 0;
    	for(TarefaInterface tarefa: this.tarefas.values()) {
    		if(tarefa.isEstadoTarefa() == false) {
    			concluidas += 1;
    		}
    	}
    	return concluidas;
    }

    /**
     * Método que retorna as três últimas tarefas pendentes.
     * @return Retorno das três últimas tarefas pendentes.
     */
    private String tresUltimasTarefas(){
        ArrayList<String> keysList = new ArrayList<String>();
        for(String key: tarefas.keySet()){
            keysList.add(key);
        }
        
        String tresUltimasTarefas = "";
        int count = 0;
        for(String str: keysList){
        	if(count >= 3) {break;}
            if(tarefas.get(str).isEstadoTarefa() == true){
                tresUltimasTarefas += "- " + tarefas.get(str).getNome() + " - " + tarefas.get(str).getId() + "\n";
                count++;
            }
        }
        return tresUltimasTarefas.trim();
    }
    
    /**
     * Busca uma tarefa dentro da atividade com base nos metadados da tarefa que pode ser
     * todo seu nome ou apenas partes.
     * 
     * Uma tarefa é retornada se tiver todos os termos procurados.
     * 
     * @param termos termos procurados.
     * @return Lista de tarefas.
     */
	public Set<TarefaInterface> buscar(String[] termos) {
		Set<String> chaves = this.tarefas.keySet();
		Set<TarefaInterface> retorno;
		retorno =  new HashSet<>();
		for(String chave: chaves) {
			String[] metadados= this.tarefas.get(chave).getMetadados();
			boolean flag = false;
			for(String termo: termos) {
				for(String dado: metadados) {
					if(termo.toLowerCase().equals(dado.toLowerCase())) {
						flag = true;
						break;
					}else {
						flag = false;
					}
				}
				if(!flag) {
					break;
				}
			}
			
			if(flag) {
				retorno.add(this.tarefas.get(chave));
			}
			
		}
		return retorno;
	}

	/**
	 * Pega a descrição atual da atividade.
	 * 
	 * @return Retorna a descrição da atividade.
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Pega o Id da atividade.
	 * 
	 * @return Retorna o id da atividade
	 */
	public String getid() {
		return this.id;
	}

	@Override
	/**
	 * Compara uma atividade a outra atraves do seu id.
	 */
	public int compareTo(Atividade o) {
		return this.id.compareTo(o.getid());
	}
	
	/**
	 * Adiciona uma tarefa a uma atividade.
	 * 
	 * @param idTarefa Id da tarefa adicionada
	 * @param tarefa Tarefa adicionada.
	 */
	public void adicionaTarefa(String idTarefa, TarefaInterface tarefa) {
		if(estadoAtividade.equals("desativada") || estadoAtividade.equals("encerrada") ){
            throw new IllegalStateException("Atividade desativada ou encerrada");
		}
		this.tarefas.put(idTarefa, tarefa);
		
	}

	/**
	 * Pega o nome da atividade.
	 * 
	 * @return Retorna o nome da atividade.
	 */
	public String getNome() {
		return this.nome;
	}

}
