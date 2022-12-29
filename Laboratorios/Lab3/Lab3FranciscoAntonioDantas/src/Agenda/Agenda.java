package agenda;

/**
 * Uma agenda que mantem uma lista de contatos com posicoes. Podem existir 100 contatos. 
 * 
 * @author Francisco Antonio Dantas
 *
 */
public class Agenda {
	
	private static final int TAMANHO_AGENDA = 101;
	
	private Contato[] contatos;

	/**
	 * Cria uma agenda.
	 */
	public Agenda() {
		this.contatos = new Contato[TAMANHO_AGENDA];
	}
	
	/**
	 * Acessa a lista de contatos mantida.
	 * @return O array de contatos.
	 */
	public Contato[] getContatos() {
		return this.contatos.clone();
	}

	/**
	 * Acessa os dados de um contato especifico.
	 * @param posicao Posicao do contato na agenda.
	 * @return Dados do contato. Null se nao ha� contato na posicao.
	 */
	public String getContato(int posicao) {
		if(posicao > 100 || posicao < 1 || contatos[posicao] == null) {
			return "POSIÇÃO INVALIDA";
		}
		else {
			return "\nDados do contato:\n" + contatos[posicao].toString();
		}
	}
	
	/**
	 * Retorna o tamanho total da agenda.
	 * @return Retorna o tamanho total da agenda.
	 */
	public int getTamanhoAgenda() {
		return TAMANHO_AGENDA;
	}

	/**
	 * Cadastra um contato em uma posicao. Um cadastro em uma posicao que ja� existe sobrescreve o anterior. 
	 * 
	 * @param posicao Posicao do contato.
	 * @param nome Nome do contato.
	 * @param sobrenome Sobrenome do contato.
	 * @param telefone Telefone do contato.
	 * @return retorna se o cadastro foi realizado ou se houve algum erro.
	 */
	public String cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
		if(validaContato(posicao, nome, sobrenome, telefone).equals("CJC")) {
			return "CONTATO JA CADASTRADO";
		}else if(validaContato(posicao, nome, sobrenome, telefone).equals("PI")){
			return "POSIÇÃO INVALIDA";
		}else if(validaContato(posicao, nome, sobrenome, telefone).equals("CI")) {
			return "CONTATO INVALIDO";
		}else {
			this.contatos[posicao] = new Contato(nome, sobrenome, telefone);
			return "CADASTRO REALIZADO";
		}
	}
	
	/**
	 * Valida o contato criado e o cadastra na agenda.
	 * 
	 * @param posicao A posicao do contato na agenda.
	 * @param nome O nome do contato.
	 * @param sobrenome O sobrenome do contato.
	 * @param telefone o telefone do contato.
	 * @return retorna se o cadastro foi feito ou n�o em caso de erro.
	 */
	private String validaContato(int posicao, String nome, String sobrenome, String telefone) {
		if(posicao >= this.getTamanhoAgenda()) {
			return "PI";
		}
		else if(posicao == 0){
			return "PI";
		}
		else if(nome == null || telefone == null) {
			return "CI";
		}
		else if(nome.length() == 0 || telefone.length() == 0) {
			return "CI";
		}
		else if(this.existeNaAgenda(new Contato(nome, sobrenome, telefone))) {
			return "CJC";
		}
		else {
			return "valido";
		}
	}
	
	
	
	/**
	 * Confere se o contato j� existe na agenda.
	 * 
	 * @param contato que ira ser checado
	 * @return true se o contato existe na agenda e false caso contrario.
	 */
	public boolean existeNaAgenda(Contato contato) {
		for(int i = 1; i < this.getTamanhoAgenda(); i++) {
			if(contatos[i] == null) {
				
			}
			else if(contatos[i].equals(contato)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adiciona o marcador de favorito em contato.
	 * 
	 * @param posicao posicao do contato na agenda.
	 * @return retorna se foi adicionado com sucesso ou n�o.
	 */
	public String adicionafavorito(int posicao) {
		if (posicao > TAMANHO_AGENDA || posicao == 0) {
			return "POSIÇÃO INVALIDA";
		}
		else{
			contatos[posicao].favoritar();
			return "CONTATO FAVORITADO NA POSIÇÃO " + posicao + "!";
		}
		
	}
	
	/**
	 * Encontra todos os contatos favoritos na agenda.
	 * 
	 * @return um array com todos os favoritos.
	 */
	public Contato[] getFavoritos() {
		Contato[] favoritos = new Contato[TAMANHO_AGENDA];
		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] == null) {
				
			}
			else if(contatos[i].getfavorito()){
				favoritos[i] = contatos[i];
			}
		}
		return favoritos.clone();
	} 
	
	/**
	 * Adiciona o marcador de tag ao contato e cria uma nova tag na agenda.
	 * 
	 * @param posicoes as posicoes dos contados pertencentes a essa tag.
	 * @param tag a tag a ser colocada
	 * @param posicaoTag a posicao da tag no array de tags da agenda.
	 */
	public void adicionaTags(String[] posicoes, String tag, String posicaoTag) {
		for(int i = 0; i < posicoes.length; i++) {
			this.contatos[Integer.parseInt(posicoes[i])].adicionarTag(tag,posicaoTag);
		}
	}
	
	/**
	 * Remove um contato da agenda.
	 * 
	 * @param posicoes posicoes a serem removidas.
	 */
	public void removeContato(String[] posicoes) {
		for(int i = 0; i < posicoes.length; i++) {
			this.contatos[Integer.parseInt(posicoes[i])] = null;
		}
	}

}
