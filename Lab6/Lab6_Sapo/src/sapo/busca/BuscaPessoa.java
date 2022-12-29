package sapo.busca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import sapo.pessoas.Pessoa;
import sapo.pessoas.PessoaService;

/**
 * BuscaPessoa representa uma busca em pessoas, ela pega os elementos que são
 * passados e realiza a busca dentro das pesoas cadastradas.
 * 
 * 
 * @author franciscodantas
 *
 */
public class BuscaPessoa implements BuscaInterface{
	
	/**
	 * ps - PessoaService que será usado.
	 * termos - Termos buscados
	 * resultado - Resultado encontrado.
	 */
	private PessoaService ps;
	private String[] termos;
	private String[] resultado;
	
	/**
	 * Cria uma nova busca de pessoas.
	 * 
	 * @param consulta termos a serem pesquisados.
	 * @param ps Pessoaservice que será usado.
	 */
	public BuscaPessoa(String consulta, PessoaService ps) {
		this.ps = ps;
		this.termos = consulta.split(" ");
	}

	@Override
	/**
	 * Realiza a busca a partir da consulta ao PessoaService.
	 * 
	 * Em PessoaService é realizado a busca e retorna os resultados sem tratamento.
	 * 
	 * @return Um array com o nome das pessoas.
	 */
	public String[] busca() {
		Set<Pessoa> pessoasEncontradas = this.ps.buscaPessoa(this.termos);
		this.resultado= ordena(pessoasEncontradas);
		return this.resultado;
	}
	
	/**
	 * Ordena as pessoas pelo seu nome.e pega o seu nome para ser o resultado da busca.
	 * 
	 * @param pessoasEncontradas Pessoas que foram encontradas.
	 * @return Array com nomes ordenados.
	 */
	private String[] ordena(Set<Pessoa> pessoasEncontradas) {
		Pessoa[] pessoas = pessoasEncontradas.toArray(new Pessoa[pessoasEncontradas.size()]);
		Arrays.sort(pessoas);
		String[] nomes = new String[pessoas.length];
		for(int i = 0; i < pessoas.length; i++) {
			nomes[i] = pessoas[i].getNome();
		}
		return nomes;
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
		exibicao.add("PESSOA");
		for(String resultado: this.resultado) {
			exibicao.add(resultado);
		}
		return exibicao.toArray(new String[exibicao.size()]);
	}

}
