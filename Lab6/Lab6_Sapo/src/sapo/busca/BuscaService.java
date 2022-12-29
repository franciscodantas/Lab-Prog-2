package sapo.busca;

import sapo.atividades.AtividadeService;
import sapo.pessoas.PessoaService;
import sapo.tarefas.TarefaService;

/**
 * BuscaService conversa com os demais sistemas para realizar as buscas.
 * 
 * BuscaService cadastra as buscas realizadas para ser armazeno em BuscaRepositorio
 * e define qual tipo de Busca vai realizar a operação.
 * 
 * @author franciscodantas
 *
 */
public class BuscaService {
	
	/**
	 * br - armazenamento das buscas já feitas.
	 * ps - PessoaService do sistema, possibilita a pesquisa de pessoas.
	 * as - AtividadeService do sistema, possibilita a pesquisa de atividades.
	 * ts - TarefaService do sistema, possibilita a pesquisa de tarefas.
	 */
	private BuscaRepositorio br;
	private PessoaService ps;
	private AtividadeService as;
	private TarefaService ts;
	
	/**
	 * Construtor padrão de Buscaservice.
	 * 
	 * @param ps PessoaService que será usado
	 * @param as AtividadeService que sera usado
	 * @param ts TarefaService que sera usado
	 */
	public BuscaService(PessoaService ps, AtividadeService as, TarefaService ts) {
		this.br = new BuscaRepositorio();
		this.ps = ps;
		this.as = as;
		this.ts = ts;
	}
	
	/**
	 * Cria uma nova busca em pessoas, passando os termos que serão buscados em cada 
	 * pessoa.
	 * 
	 * @param consulta Termos de busca
	 * @return Nome das pessoas encontradas.
	 */
	public String[] buscaPessoas(String consulta) {
		BuscaInterface buscaPessoa = new BuscaPessoa(consulta, this.ps);
		this.br.adicionaBusca(buscaPessoa);
		return buscaPessoa.busca();
	}
	
	/**
	 * Cria uma nova busca em atividade, passando os termos que serão buscados em cada 
	 * atividade.
	 * 
	 * @param consulta Termos de busca
	 * @return Nome das atividades encontradas.
	 */
	public String[] buscaAtividade(String consulta) {
		BuscaInterface buscaAtividade = new BuscaAtividade(consulta, this.as);
		this.br.adicionaBusca(buscaAtividade);
		return buscaAtividade.busca();
	}
	
	/**
	 * Cria uma nova busca de tarefas, passando o nome que será buscados em cada 
	 * tarefa.
	 * 
	 * @param nome Nome buscado
	 * @return Nome das tarefas encontradas.
	 */
	public String[] buscaTarefas(String nome) {
		BuscaInterface buscaTarefas = new BuscaTarefa(nome, this.ts);
		this.br.adicionaBusca(buscaTarefas);
		return buscaTarefas.busca();
	}
	
	/**
	 * Cria uma nova busca de tarefas, passando o nome que será buscados em cada 
	 * tarefa que está contida na atividade passada.
	 * 
	 * @param idAtividade id da atividade que contem as tarefas.
	 * @param nome Nome buscado
	 * @return Nome das tarefas encontradas.
	 */
	public String[] buscaTarefas(String idAtividade, String nome) {
		BuscaInterface buscaTarefas = new BuscaTarefaAtividade(idAtividade, this.as.recuperarAtividade(idAtividade));
		this.br.adicionaBusca(buscaTarefas);
		return buscaTarefas.busca();
	}
	
	/**
	 * Cria uma nova busca de sugestão com base nas habilidades de uma pessoa.
	 * 
	 * @param cpf Cpf da pessoa.
	 * @return Nome das tarefas encontradas.
	 */
	public String[] sugerirTarefa(String cpf) {
		String[] habilidades = this.ps.recuperaPessoa(cpf).getHabilidades();
		BuscaInterface sugestao = new BuscaSugestao(habilidades, this.ts);
		this.br.adicionaBusca(sugestao);
		return sugestao.busca();
	}
	
	/**
	 * exibe as n ultimas buscas.
	 * 
	 * @param nBuscas Quantidade de ultimas buscas.
	 * @return As n buscas mais recentes.
	 */
	public String[] exibirRecentes(int nBuscas) {
		return this.br.exibirRecentes(nBuscas);
	}
	
	/**
	 * Exibe uma busca dentro do historico de buscas.
	 * 
	 * @param index Index da busca.
	 * @return A busca requerida.
	 */
	public String[] exibirHistorico(int index) {
		return this.br.exibirHistorico(index);
	}

}
