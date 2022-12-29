package sapo.pessoa;



import org.junit.jupiter.api.BeforeEach;

import sapo.Facade;

/**
 * Base para os testes de pessoa
 * 
 * @author José Arthur.
 */
class PessoaBaseTeste {

	protected Facade facade;

	
	@BeforeEach
	public void inicial() {
		this.facade = new Facade();
		
		this.facade.cadastrarPessoa("111.111.111-12", "Marcelo", new String[] {"java"});
		this.facade.cadastrarPessoa("111.111.111-10", "Thomas", new String[] {""});
		this.facade.cadastrarAluno("123.456.789-10", "Arthur", "777", 2, new String[] {"Músico"});
		this.facade.cadastrarProfessor("987.654.321-00", "José", "666", new String[] {"Teoria musical"}, new String[] {"Pianista"});
	}

}
