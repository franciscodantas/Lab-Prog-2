package sapo.busca;

/**
 * Representa uma busca generica do sistema.
 * 
 * @author franciscodantas
 *
 */
public interface BuscaInterface {
	
	/**
	 * Realiza uma busca com base nos dados passados e os elementos armazenados
	 * no sistena.
	 * 
	 * Uma busca pode ser realizada com base nas pessoas, nas atividades ou das tarefas
	 * do sistema.
	 * 
	 * @return Sequencia de pessoas ou atividades ou tarefas encontradas.
	 */
	public String[] busca();
	
	/**
	 * Exibe uma forma de representação de uma busca, sendo o primeiro elemento
	 * o tipo da busca e os demais resultados encontrados.
	 * 
	 * @return uma representação da busca.
	 */
	public String[] exibeBusca();
}
