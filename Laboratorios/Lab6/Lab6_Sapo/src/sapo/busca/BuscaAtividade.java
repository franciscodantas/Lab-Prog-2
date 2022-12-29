package sapo.busca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import sapo.atividades.Atividade;
import sapo.atividades.AtividadeService;

/**
 * BuscaAtividade representa uma busca em atividades, ela pega os elementos que são
 * passados e realiza a busca dentro das atividades cadastradas.
 * 
 * @author franciscodantas
 *
 */
public class BuscaAtividade implements BuscaInterface{
	
	/**
	 * termos - termos a serem procurados na atividade.
	 * resultado - resultado encontrado depois da busca.
	 * as - Atividadeservice do sistema.
	 */
	private String[] termos;
	private String[] resultado;
	private AtividadeService as;
	
	/**
	 * Cria uma nova busca de atividades.
	 * 
	 * @param consulta Termos que serão buscados.
	 * @param as AtividadeService que será usado.
	 */
	public BuscaAtividade(String consulta, AtividadeService as) {
		this.as = as;
		this.termos = consulta.split(" ");
	}
	
	@Override
	/**
	 * Realiza a busca a partir da consulta ao AtividadeService.
	 * 
	 * Em AtividadeService é realizado a busca e retorna os resultados sem tratamento.
	 * 
	 * @return Array com as descrições de cada atividade.
	 */
	public String[] busca() {
		Set<Atividade> atividadesEncontradas = this.as.buscaAtividade(this.termos);
		this.resultado = ordena(atividadesEncontradas);
		return this.resultado;
	}
	
	/**
	 * Ordena as atividades pelo seu id e pega a sua descrição para ser retornado.
	 * 
	 * @param atividadesEncontradas As atividades que satisfazem a busca.
	 * @return Um array com as descrições ordenados pelo id da atividade.
	 */
	private String[] ordena(Set<Atividade> atividadesEncontradas) {
		Atividade[] atividades = atividadesEncontradas.toArray(new Atividade[atividadesEncontradas.size()]);
		Arrays.sort(atividades);
		String[] representacao = new String[atividades.length];
		for(int i = 0; i < atividades.length; i++) {
			representacao[i] = atividades[i].getDescricao();
		}
		return representacao;
	}
	
	@Override
	/**
	 * Cria uma exibição com o primeiro elemento do array sendo o tipo e os demais
	 * elementos sendo os resultados.
	 * 
	 * [Tipo], [resultado1], ...
	 * 
	 * @return Array que representa a busca.
	 */
	public String[] exibeBusca(){
		List<String> exibicao = new ArrayList<>();
		exibicao.add("ATIVIDADE");
		for(String resultado: this.resultado) {
			exibicao.add(resultado);
		}
		return exibicao.toArray(new String[exibicao.size()]);
	}
}
