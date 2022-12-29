package sapo.tarefas;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * TarefaRepositorio é reponsavel pelo armazenamento das tarefas no sistema. Cabe a ele guardar,
 * fornecer, remover e procurar tarefas dentro do seu Map de tarefas.
 * 
 * @author Thayane Barros e franciscodantas
 *
 */
public class TarefaRepositorio {
	
	/**
	 * tarefas - mapa que armazena as tarefas no sistema.
	 * validador - O validador que será usado.
	 */
	private Map<String, TarefaInterface> tarefas;
	private ValidadorTarefa validador;

	/**
	 * Construtor padrão do repositorio de tarefas.
	 */
	public TarefaRepositorio() {
		this.tarefas = new HashMap<>();
		this.validador = new ValidadorTarefa();
	}
	
	/**
	 * Adiciona uma tarefa ao repositorio, cada tarefa terá como chave o seu codigo(id). A tarefa
	 * é validado para garantir a consistência da tarefa (sem termos nulos ou vazios).
	 * 
	 * @param tarefaId O Id da tarefa a ser adicionado.
	 * @param tarefa A tarefa a ser adicionada.
	 */
	public void adicionaTarefa(String tarefaId, TarefaInterface tarefa) {
		this.validador.valida(tarefaId, tarefa);
		this.tarefas.put(tarefaId, tarefa);
	}
	
	/**
	 * Recupera uma tarefa do repositório.
	 * 
	 * @param Idtarefa O Id da tarefa a ser adicionado.
	 * @return A Tarefa, caso exista.
	 */
	public TarefaInterface recuperaTarefa(String Idtarefa) {
		this.validador.valida(Idtarefa);
		if(this.tarefas.containsKey(Idtarefa)) {
			return this.tarefas.get(Idtarefa);
		}
		else {
			throw new IllegalArgumentException("Tarefa não existe");
		}
	}
	
	/**
	 * Remove uma tarefa do sistema.
	 * 
	 * @param Idtarefa O id da tarefa a ser removido.
	 */
	public void removeTarefa(String Idtarefa) {
		this.validador.valida(Idtarefa);
		if(this.tarefas.containsKey(Idtarefa)) {
			this.tarefas.remove(Idtarefa);
		}
		else {
			throw new IllegalArgumentException("Tarefa não existe");
		}
		
	}

	/**
	 * Faz uma busca nos metadados de cada tarefa, procurando os termos que estão sendo
	 * passados.
	 * 
	 * uma tarefa é retornada caso possua todos os termos procurados.
	 * 
	 * @param termos termos procurados.
	 * @return Uma lista de tarefas.
	 */
	public Set<TarefaInterface> busca(String[] termos) {
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
	 * Faz uma busca por sugestões para uma lista de habilidade.
	 * 
	 * Uma tarefa é sugerida quando possue pelo menos uma habilidade em comum com as
	 * habilidade passadas.
	 * 
	 * Retorna em forma de uma map no que a chave é a tarefa e o valor é a quantidade de
	 * habilidades comuns com as que foram passadas.
	 * 
	 * @param habilidades Habilidades que são buscadas.
	 * @return Um map com as tarefas e quantidade de habilidades semelhantes.
	 */
	public Map<TarefaInterface, Integer> sugerir(String[] habilidades) {
		Set<String> chaves = this.tarefas.keySet();
		Map<TarefaInterface, Integer> resultado = new HashMap<>();
		for(String chave : chaves) {
			if(!this.tarefas.get(chave).isEstadoTarefa()) {
				continue;
			}
			int iguais = 0;
			String[] habilidadesExistentes = this.tarefas.get(chave).getHabilidades();
			if(habilidadesExistentes == null || habilidadesExistentes.length == 0) {
				resultado.put(this.tarefas.get(chave), Integer.MAX_VALUE);
				continue;
			}
			for(String hb: habilidades) {
				for(String dado: habilidadesExistentes) {
					if(hb.toLowerCase().equals(dado.toLowerCase())) {
						iguais += 1;
						break;
					}
					}
				}
			if(iguais > 0) {
				resultado.put(this.tarefas.get(chave), iguais);
			}
			
			}
		return resultado;
	}


}
