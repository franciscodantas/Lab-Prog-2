package sapo.pessoas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import sapo.atividades.Atividade;
import sapo.tarefas.TarefaInterface;

/**
 * A classe pessoa representa uma pessoa dentro do Sistema SAPO.
 * 
 * Uma pessoa é representada unicamente pelo seu CPF, possue um nome e habilidades.
 * 
 * Uma pessoa pode ser associada a tarefas e responsavel por atividades, além de receber comentarios de outras pessoas.
 * 
 * @author franciscodantas e Thayane Barros
 *
 */
public class Pessoa implements Comparable<Pessoa>{
	
	/**
	 * cpf - Identificador unico de uma pessoa.
	 * nome - nome da pessoa.
	 * habilidades - Lista de habilidades que uma pessoa possue.
	 * comentarios - Lista de comentarios feitos sobre uma pessoa
	 * tarefas - tarefas que aquela pessoa está associada.
	 * atividadeResponsavel - atividade que a pessoa esta associada.
	 * nivel - nivel da pessoa.
	 * funcao - funcao que a pessoa pode exercer.
	 */
	 private String cpf;
	 private String nome;
	 private String[] habilidades;
	 private List<Comentario> comentarios;
	 private Map<String, TarefaInterface> tarefas;
	 private Atividade atividadeResponsavel;
	 private int nivel;
	 private Funcao funcao;

	 /**
	  * Criador padrão de pessoa, nele é definido o cpf da pessoa e seu nome, que não são nulos nem vazios.
	  * Além disso é passado as habilidades da pessoa, se a tiver.
	  * 
	  * @param cpf Cpf da pessoa
	  * @param nome Nome de uma pessoa
	  * @param habilidades Lista de habilidades
	  */
	 public Pessoa(String cpf, String nome, String[] habilidades) {
		 this.cpf = cpf;
		 this.nome = nome;
		 this.habilidades = habilidades;
		 Arrays.sort(habilidades);
		 this.funcao = new SemFuncao();
		 this.nivel = 0;
		 this.tarefas = new HashMap<>();
		 this.comentarios = new ArrayList<>();
	 }
	 
	 /**
	  * Cria uma pessoa com uma função.
	  * 
	  * @param cpf O Cpf da pessoa.
	  * @param nome O Nome de uma pessoa.
	  * @param habilidades A Lista de habilidades.
	  * @param funcao A funcao que a pessoa exerce.
	  */
	 public Pessoa(String cpf, String nome, String[] habilidades, Funcao funcao) {
		 this(cpf, nome, habilidades);
		 this.funcao = funcao;
		 this.nivel = 0;
		 this.tarefas = new HashMap<>();
		 this.comentarios = new ArrayList<>();
	 }
	 
	 /**
	  * Retorna apenas o nome da pessoa.
	  * 
	  * @return Retorna o nome da pessoa.
	  */
	 public String getNome() {
		return this.nome;
		}
	 
	 
	 /**
	  * Retorna uma identidade de uma pessoa.
	  * 
	  * Uma identidade é composta pelo nome da pessoa e seu cpf da forma: nome - cpf.
	  * 
	  * @return A identidade da pessoa.
	  */
	 public String getIdentidade() {
		 return this.nome + " - " + cpf;
	 }
	 
	 /**
	  * Modifica o nome da pessoa
	  * 
	  * @param novoNome O novo nome da pessoa.
	  */
	 public void setNome(String novoNome) {
		 this.nome = novoNome;
	 }
	 
	 /**
	  * Modifica a lista de habilidades de uma pessoa.
	  * 
	  * @param novasHabilidades novas habilidades.
	  */
	 public void setHabilidade(String[] novasHabilidades) {
		 this.habilidades = novasHabilidades;
	 }
	 
	 /**
	  * Cria um novo comentario sobre a pessoa.
	  * 
	  * @param comentario texto do comentario.
	  * @param autorCpf pessoa que fez o comentario.
	  */
	 public void adicionarComentario(String comentario, String autorCpf) {
		 this.comentarios.add(new Comentario(comentario, autorCpf));
	 }

	 /**
	  * Lista os comentarios que a pessoa possui.
	  * 
	  * @return uma lista com representações textuais de cada comentario.
	  */
	 public String listarComentarios() {
		 String listagem = this.getIdentidade() + "\n" + "Comentários:\n";
		 for(int i = 0; i < this.comentarios.size(); i++) {
			 listagem += this.comentarios.get(i).toString();
		 }
		 return listagem;
	 }
	 
	@Override
	/**
	 * Define um identificador unico para a pessoa baseado no seu cpf.
	 * 
	 * @return hascode do objeto.
	 */
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	/**
	 * Compara um objeto a pessoa.
	 * 
	 * Uma pessoa é considerada igual se o cpf dela for igual a da pessoa comparada.
	 * 
	 * @return se é igual ou não.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf);
	}
	
	@Override
	/**
	 * Cria uma representação textual de uma pessoa.
	 * 
	 * Essa representação textual é composta do seu nome, cpf e sua lista de habilidades.
	 * 
	 * @return a representação de uma pessoa.
	 */
	public String toString() {
		return (this.getIdentidade() + "\n" + this.funcao.gerarDetalhes() + this.listaHabilidades()).trim();
	 }
	
	/**
	 * Organiza a listagem de habilidades em uma string.
	 * 
	 * @return uma string com as habilidades da pessoa.
	 */
	private String listaHabilidades() {
		String listagem = "";
		 for(int i = 0; i < this.habilidades.length; i++) {
			 if(!this.habilidades[i].isBlank()) {
				 listagem += "- " + this.habilidades[i] + "\n";
			 }
		 }
		return listagem;
	}
	
	/**
	 * Pega os dados mais importantes de uma pessoa: cpf, nome e habilidades.
	 * 
	 * @return Lista com os dados mais importantes de uma pessoa.
	 */
	public ArrayList<String> getMetadados() {
		ArrayList<String> dados = new ArrayList<String>();
		String[] nomePartes = nome.split(" ");
		dados.add(cpf);
		for(int i = 0; i < nomePartes.length; i++) {
			dados.add(nomePartes[i].toLowerCase());
		}
		for(int i = 0; i < this.habilidades.length; i++) {
			dados.add(this.habilidades[i].toLowerCase());
		}
		
		return dados;
	}
	
	/**
	 * Retorna a lista de habilidades que uma pessoa possue.
	 * 
	 * @return lista de habilidades.
	 */
	public String[] getHabilidades() {
		return this.habilidades;
	}

	/**
	 * Retorna o cpf da pessoa.
	 * 
	 * @return cpf da pessoa.
	 */
	public String getCpf() {
		return this.cpf;
	}
	
	/**
	 * Adiciona uma tarefa a ser feita pela pessoa.
	 * 
	 * @param tarefa tarefa a ser adicionada
	 */
	public void adicionaTarefa(TarefaInterface tarefa) {
		this.tarefas.put(tarefa.getId(), tarefa);
		this.funcao.adicionaTarefa(tarefa.getId(), tarefa);
	}
	
	/**
	 * Remove uma tarefa de uma pessoa
	 * 
	 * @param id identificação da tarefa a ser removida
	 */
	public void removeTarefa(String id) {
		if(this.tarefas.containsKey(id)) {
			this.tarefas.remove(id);
		}
		else {
			throw new IllegalArgumentException("Você não pode remover uma tarefa que não está associada a essa pessoa!");
		}
	}
	
	
	/**
	 * Define uma nova função para essa pessoa.
	 * 
	 * @param novaFuncao Nova função da pessoa.
	 */
	public void definiFuncao(Funcao novaFuncao) {
		if(novaFuncao.getClass() == this.funcao.getClass()) {
			throw new IllegalStateException("A pessoa já possui essa função.");
		}
		this.nivel += calculaNivel();
		this.funcao = novaFuncao;		
	}
	
	/**
	 * Calcula o nivel da pessoa.
	 * 
	 * @return Novo nivel da pessoa
	 */
	private int calculaNivel() {
		return this.funcao.calculaNivel(this.habilidades);
	}
	
	/**
	 * Retorna o nivel de uma pessoa.
	 * 
	 * @return Nivel da pessoa
	 */
	public int getNivel() {
		return calculaNivel() + this.nivel;
	}

	/**
	 * Remove uma pessoa de outras partes do sistema que ela esteja
	 */
	public void remove() {
		Set<String> chaves = this.tarefas.keySet();
		for(String chave: chaves) {
			this.tarefas.get(chave).removerPessoa(this.cpf);
		}
		if(!(this.atividadeResponsavel == null)) {
			this.atividadeResponsavel.alterarResponsavelAtividade(null);
		}
	}
	
	/**
	 * Adiciona essa pessoa como responsavel por uma atividade
	 * 
	 * @param atividade atividade atribuida
	 */
	public void adiconaComoResponsavel(Atividade atividade) {
		this.atividadeResponsavel = atividade;
	}
	
	/**
	 * Remove essa pessoa da responsabilidade pela tarefa
	 */
	public void removeComoResponsavel() {
		this.atividadeResponsavel = null;
	}
	
	/**
	 * Remove a função da pessoa.
	 */
	public void removeFuncao() {
		this.nivel += calculaNivel();
		this.funcao = new SemFuncao();
	}

	@Override
	/**
	 * Compara uma pessoa e olha se seu nome vem antes ou depos de outra.
	 * 
	 * @param o pessoa comparada
	 */
	public int compareTo(Pessoa o) {
		if(this.nome.toLowerCase().compareTo(o.getNome().toLowerCase()) > 0) {
			return 1;
		}
		else if(this.nome.toLowerCase().compareTo(o.getNome().toLowerCase()) == 0) {
			int cpf1 = Integer.parseInt(cpf);
			int cpf2 = Integer.parseInt(o.getCpf());
			if(cpf1 > cpf2) {
				return 1;
			}
			else {
				return -1;
			}
		}
		else {
			return -1;
		}
	}
}
