package sapo.pessoas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * PessoaRepositorion é reponsavel pelo armazenamento das pessoas do sistema.
 * 
 * Cabe a ele guardar, fornecer, remover e procurar pessoas dentro do seu Map de pessoas.
 * 
 * @author franciscodantas
 *
 */
public class PessoaRepositorio {
	
	/**
	 * pessoas - map que armazena as pessoas do sistema.
	 * vp - classe que valida os elementos de pessoa e suas operações.
	 */
	private Map<String, Pessoa> pessoas;
	private ValidadorPessoa vp;
	
	/**
	 * Construtor padrão do repositorio de pessoas.
	 */
	public PessoaRepositorio() {
		this.pessoas = new HashMap<>();
		this.vp = new ValidadorPessoa();
	}
	
	/**
	 * Recupera uma pessoa, com base no cpf.
	 * 
	 * @param cpf Cpf da pessoa.
	 * @return Um pessoa, caso exista.
	 */
	public Pessoa recuperarPessoa(String cpf) {
		vp.valida(cpf);
		if(this.pessoas.containsKey(cpf)) {
			return this.pessoas.get(cpf);
		}
		else {
			throw new NoSuchElementException("Pessoa não existe");
		}
	}
	
	/**
	 * Adiciona uma pessoa ao repositorio, cada pessoa terá como chave
	 * o seu cpf.
	 * 
	 * @param pessoa Pessoa que será adicionada.
	 */
	public void adicionarPessoa(Pessoa pessoa) {
		this.pessoas.put(pessoa.getCpf(), pessoa);
	}
	
	/**
	 * Remove do sistema uma pessoa.
	 * 
	 * @param cpf Cpf da pessoa removida.
	 */
	public void removePessoa(String cpf) {
		this.vp.valida(cpf);
		if(this.pessoas.containsKey(cpf)) {
			this.pessoas.remove(cpf);
		}
		else{
			throw new NoSuchElementException("Você não pode remover uma pessoa que não existe!");
		}
		
	}
	
	/**
	 * Busca uma pessoa dentro do repositorio de pessoas. Faça uma varedura em todas as pessoas do repositorio,
	 * verificando o cpf, nome e habilidades da pessoa e conferindo se é igual a o de algum dos termos de busca.
	 * Os termos de Busca são palavras chaves que podem ser o cpf, o nome todo ou parte do mesmo ou uma habilidade
	 * de uma pessoa.
	 * 
	 * Quando uma pessoa tem todos os termos da pesquisa ela é adicionada a lista de busca.
	 * 
	 * @param termos Palavras chaves a serem buscadas.
	 * @return Uma lista de pessoas que possuam todos os termos buscados.
	 */
	public Set<Pessoa> buscaPessoa(String[] termos) {
		Set<String> chaves = this.pessoas.keySet();
		Set<Pessoa> retorno;
		retorno =  new HashSet<>();
		for(String chave: chaves) {
			ArrayList<String> metadados= this.pessoas.get(chave).getMetadados();
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
				retorno.add(this.pessoas.get(chave));
			}
			
		}
		return retorno;
	}
	
	/**
	 * Metodo que lista todas as pessoas cadastradas no sistema, ordenadas pelo nome,
	 * e por CPF em caso de empate.
	 * 
	 * @return Uma lista de todas as pessoas do sistema.
	 */
	public String[] listaPessoas() {
		ArrayList<Pessoa> pessoasList = new ArrayList<>();
		for(Pessoa pessoa: this.pessoas.values()) {
			pessoasList.add(pessoa);
		}
		Collections.sort(pessoasList);
		
		String[] todasAsPessoas = new String[this.pessoas.size()];
		int i = 0;
		for(Pessoa pessoa: pessoasList) {
			todasAsPessoas[i++] = pessoa.toString();
		}
		return todasAsPessoas;
	}

}
