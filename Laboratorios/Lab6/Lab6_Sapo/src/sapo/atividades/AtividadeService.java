package sapo.atividades;

import java.util.Set;

import sapo.pessoas.Pessoa;
import sapo.pessoas.PessoaService;

/**
 * AtividadeService é a base de lógica de operações sobre a atividade. Demais
 * classes devem fazer uso desse serviço para acessar e operar sobre atividade.
 * 
 * Para garantir a união das operações e pessoas deve-se ter apenas um AtividadeService.
 * no sistema.
 * 
 * @author Arthur Soares
 */
public class AtividadeService {

    /**
     * ar Repositorio de atividade que armazena todas as atividades que estão no
	 * sistema para que AtividadeService não precise gerenciar isso.
     * ps A pessoa service que será controlado.
     */
    AtividadeRepositorio ar;
    PessoaService ps;
    
    /**
     * Criador padrão de PessoaService, nele é criado o repositorio para armazenar
	 * as pessoas do sistema.
     * @param ps A pessoa service que será controlado.
     */
    public AtividadeService(PessoaService ps){
    	this.ps = ps; 
    	this.ar = new AtividadeRepositorio();
    }

    /**
     * Cadastra uma atividade no sistema.
     *
     * @param nome Nome da atividade.
     * @param descricao Descricao da atividade.
     * @param cpf Cpf da pessoa em que a atividade será relacionada.
     * @return Id da atividade.
     */
    public String cadastrarAtividade(String nome, String descricao, String cpf){
    	Pessoa pessoa = ps.recuperaPessoa(cpf);
        return this.ar.cadastrarAtividade(nome, descricao, pessoa);
    }
    
    /**
     * Encerra uma atividade. Não é permitido encerrar uma atividade já encerrada ou desativada.
     * 
     * @param atividadeId Id da atividade a ser encerrada.
     */
    public void encerrarAtividade(String atividadeId){
        this.ar.recuperarAtividade(atividadeId).alterarEstado("encerrada");
    }

    /**
     * Desativa uma atividade. Não é permitido desativar uma atividade já encerrada ou desativada.
     * 
     * @param atividadeId Id da atividade a ser desativada.
     */
    public void desativarAtividade(String atividadeId) {
        this.ar.recuperarAtividade(atividadeId).alterarEstado("desativada");
    }

    /**
     * Reabre uma atividade. Não é permitido reabrir uma atividade já aberta.
     * 
     * @param atividadeId Id da atividade a ser reaberta.
     */
    public void reabrirAtividade(String atividadeId) {
        this.ar.recuperarAtividade(atividadeId).alterarEstado("aberta");
    }

    /**
     * Exibe uma forma textual da atividade.
     * 
     * @param atividadeId Id da atividade a ser exibida.
     * @return Retorna a representação textual da atividade.
     */
    public String exibirAtividade(String atividadeId) {
        return this.ar.recuperarAtividade(atividadeId).toString();
    }

    /**
     * Altera a descrição da atividade.
     * 
     * @param atividadeId Id da atividade a ser alterada a descrição.
     * @param descricao Descrição que irá sobrescrever a existente.
     */
    public void alterarDescricaoAtividade(String atividadeId, String descricao) {
        this.ar.recuperarAtividade(atividadeId).alterarDescricao(descricao);
    }

    /**
     * Altera o responsável da atividade.
     * 
     * @param atividadeId Id da atividade a ser alterada o responsável.
     * @param cpf Cpf da pessoa que irá ser o novo responsável pela atividade.
     */
    public void alterarResponsavelAtividade(String atividadeId, String cpf) {
    	Pessoa pessoa = ps.recuperaPessoa(cpf);
    	this.ar.recuperarAtividade(atividadeId).alterarResponsavelAtividade(pessoa);
    }

    /**
     * Recupera uma atividade.
     * 
     * @param idAtividade Id da atividade a ser recuperada.
     * @return Retorna a atividade.
     */
    public Atividade recuperarAtividade(String idAtividade){
        return this.ar.recuperarAtividade(idAtividade);
    }
    /**
     * Faz uma busca por atividades dentro do repositorio de atividades com base
     * nos termos passados.
     * 
     * @param termos Termos que são buscados.
     * @return Atividades encontradas.
     */
	public Set<Atividade> buscaAtividade(String[] termos) {
		return this.ar.busca(termos);
	}
}
