package sapo.busca;

import java.util.ArrayList;
import java.util.List;

/**
 * BuscaRepositorio é o historico de buscas já feitas no sistema, gerencia
 * o armazenamento das buscas e as formas de pegar um historico.
 * 
 * @author franciscodantas
 *
 */
public class BuscaRepositorio {
	
	/**
	 * buscas - historico de buscas.
	 */
	private List<BuscaInterface> buscas;
	
	/**
	 * Construtor padrão de BuscaRepositorio.
	 */
	public BuscaRepositorio() {
		this.buscas = new ArrayList<>();
	}
	
	/**
	 * Adiciona uma busca ao historico.
	 * 
	 * @param busca Busca que será adicionada.
	 */
	public void adicionaBusca(BuscaInterface busca) {
		this.buscas.add(busca);
	}
	
	/**
	 * exibe as buscas mais recentes do historico da mais recente até a quantidade
	 * pedida. Conferindo se a quantidade que se pede é valido.
	 * 
	 * @param nBuscas Quantidade de buscas pedidas.
	 * @return Um array com a exibição das buscas.
	 */
	public String[] exibirRecentes(int nBuscas) {
		if(nBuscas > this.buscas.size()) {
			throw new IllegalArgumentException("VOCÊ NÃO PODE VER MAIS BUSCAS DO QUE TEM NO SISTEMA");
		}
		
		ArrayList<String> retorno = new ArrayList<String>();
		for(int i = this.buscas.size() - 1; i >= this.buscas.size() - nBuscas; i--) {
			String comp = "";
			for(String nome :  this.buscas.get(i).exibeBusca()) {
				comp += nome + "\n";
			}
			retorno.add(comp.trim());
		}
		return retorno.toArray(new String[retorno.size()]);
	}
	
	/**
	 * Exibe uma busca especifica por meio do seu index de armazenamento.
	 * 
	 * @param index Index de armazenamento.
	 * @return Uma representação de Busca.
	 */
	public String[] exibirHistorico(int index) {
		if(index > this.buscas.size()) {
			throw new IllegalArgumentException("VOCÊ NÃO PODE VER MAIS BUSCAS DO QUE TEM NO SISTEMA");
		}
		
		return this.buscas.get(index).exibeBusca();
	}
}
