package sapo.atividades;

/**
 * AtividadeController é responsável por receber requisições do usuário e do
 * sistema e converter em operações lógicas (AtividadeService).
 *  
 * @author - José Arthur
 */
public class AtividadeController {
    
    /**
	 * as - A atividade service que será controlado.
	 * validador - O validador que será usado.
	 */
    AtividadeService as;
    ValidadorAtividade va;

    /**
     * Cria o AtividadeController, configurando que atividade service será usado.
     * @param as Atividade service que será controlado
     */
    public AtividadeController(AtividadeService as){
        this.as = as;
        va = new ValidadorAtividade();
    }

    /**
     * Cadastra uma atividade, validando o id da atividade, descrição e cpf para objetos não nulos e vazios.
     * 
     * @param nome Nome da atividade.
     * @param descricao Descricao da Atividade
     * @param cpf Cpf da pessoa em que a atividade será relacionada.
     * @return Id da atividade.
     */
    public String cadastrarAtividade(String nome, String descricao, String cpf){
        va.valida(descricao, cpf, nome);
        return this.as.cadastrarAtividade(nome, descricao, cpf);
    }

    /**
     * Encerra uma atividade, validando o id da atividade para objeto não nulo e vazio.
     * 
     * @param atividadeId Id da atividade a ser encerrada.
     */
    public void encerrarAtividade(String atividadeId){
        va.valida(atividadeId);
        this.as.encerrarAtividade(atividadeId);
    }

    /**
     * Desativa uma atividade, validando o id da atividade para objeto não nulo e vazio.
     * 
     * @param atividadeId Id da atividade a ser desativada.
     */
    public void desativarAtividade(String atividadeId){
        va.valida(atividadeId);
        this.as.desativarAtividade(atividadeId);
    }

    /**
     * Reabre uma atividade, validando o id da atividade para objeto não nulo e vazio.
     * 
     * @param atividadeId Id da atividade a ser reaberta.
     */
    public void reabrirAtividade(String atividadeId){
        va.valida(atividadeId);
        this.as.reabrirAtividade(atividadeId);
    }

    /**
     * Exibe uma atividade, validando o id da atividade para objeto não nulo e vazio.
     * 
     * @param atividadeId Id da atividade a ser exibida.
     * @return Retorna a representação textual da atividade.
     */
    public String exibirAtividade(String atividadeId){
        va.valida(atividadeId);
        return this.as.exibirAtividade(atividadeId);
    }

    /**
     * Altera a descrição de uma atividade, validando o id da atividade e a descrição para objetos não nulos e vazios.
     * 
     * @param atividadeId Id da atividade.
     * @param descricao Nova descrição.
     */
    public void alterarDescricaoAtividade(String atividadeId, String descricao){
        this.va.valida(atividadeId, descricao);
        this.as.alterarDescricaoAtividade(atividadeId, descricao);
    }

    /**
     * Altera o responsável de uma atividade, validando o id da atividade e o cpf para objetos não nulos e vazios.
     * @param atividadeId Id da atividade.
     * @param cpf Cpf do novo responsavel.
     */
    public void alterarResponsavelAtividade(String atividadeId, String cpf){
        va.valida(atividadeId);
        va.valida(cpf);
        this.as.alterarResponsavelAtividade(atividadeId, cpf);
    }

}

