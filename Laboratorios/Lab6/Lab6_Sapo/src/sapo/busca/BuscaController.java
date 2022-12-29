package sapo.busca;

/**
 * BuscaController comanda as operações de buscas, validando as entradas e mandando
 * a realização das buscas.
 * 
 * @author franciscodantas
 *
 */
public class BuscaController {
	
	/**
	 * bs - BuscaService responsavel pelas buscas.
	 * vc - Validador das Buscas.
	 */
	private BuscaService bs;
	private ValidadorBusca vb;
	
	/**
	 * Criador padrão de BuscaController.
	 * 
	 * @param bs busca service do sistema.
	 */
	public BuscaController(BuscaService bs) {
		this.bs = bs;
		this.vb = new ValidadorBusca();
	}
	
	/**
	 * Exibe as pessoas que tem todos os termos de pesquisa.
	 * 
	 * @param consulta termos a serem pesquisados.
	 * @return Um array com os nomes das pessoas encontradas.
	 */
	public String[] exibirPessoas(String consulta) {
		vb.valida(consulta);
		return bs.buscaPessoas(consulta);
	}
	
	/**
	 * Busca atividades no sistema e retorna uma lista com as atividades que contenham
	 * os termos de consulta.
	 * 
	 * @param consulta termos a serem pesquisados.
	 * @return uma lista com as atividades encontradas.
	 */
	public String[] buscarAtividades(String consulta) {
		vb.valida(consulta);
		return bs.buscaAtividade(consulta);
	}
	
	/**
	 * Busca tarefas no sistema e retorna uma lista com as tarefas que contenham os 
	 * nomes passados..
	 * 
	 * @param nome Nome de tarefa a ser buscado.
	 * @return Lista de tarefas encontradas.
	 */
	public String[] buscarTarefas(String nome) {
		vb.valida(nome);
		return bs.buscaTarefas(nome);
	}
	
	/**
	 * Busca uma tarefa a partir de uma atividade do sistema, retornando aquelas que
	 * possuam o nome juscado.
	 * 
	 * @param idAtividade Id da atividade que contem a tarefa.
	 * @param nome nome a ser buscado.
	 * @return Lista de tarefas encontradas.
	 */
	public String[] buscarTarefas(String idAtividade, String nome) {
		vb.valida(idAtividade, nome);
		return bs.buscaTarefas(idAtividade, nome);
	}
	
	/**
	 * Sugere uma tarefa a uma pessoa pela similaridade de habilidade.
	 * 
	 * @param cpf Cpf da pessoa.
	 * @return Lista de tarefas.
	 */
	public String[] sugerirTarefas(String cpf) {
		vb.valida(cpf);
		return bs.sugerirTarefa(cpf);
	}
	
	/**
	 * Retorna as n buscas mais recentes.
	 * 
	 * @param nBuscas N buscas a serem pesquisados.
	 * @return Buscas mais recentes.
	 */
	public String[] buscarMaisRecentes(int nBuscas) {
		this.vb.valida(nBuscas);
		return bs.exibirRecentes(nBuscas);
	}
	
	/**
	 * Exibe uma busca no historico.
	 * 
	 * @param index index da busca.
	 * @return Uma representação de busca.
	 */
	public String[] exibirHistoricoBusca(int index) {
		this.vb.validaIdx(index);
		return bs.exibirHistorico(index);
	}
}
