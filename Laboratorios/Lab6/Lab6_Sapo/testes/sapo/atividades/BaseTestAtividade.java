package sapo.atividades;

import org.junit.jupiter.api.BeforeEach;

import sapo.Facade;

class BaseTestAtividade {

	protected Facade atividade1;
	protected Facade atividade2;
	protected Facade atividade3;
	
	@BeforeEach
	public void inicial() {
		this.atividade1 = new Facade();
		this.atividade1.cadastrarPessoa("111.111.111-11", "Francisco", new String[] {"sup"});
		this.atividade1.cadastrarPessoa("111.111.000-01", "Reinaldo", new String[] {"Java"});
		this.atividade1.cadastrarPessoa("111.111.000-02", "Marcelo", new String[] {"Python"});
		
	
		this.atividade2 = new Facade();
		this.atividade2.cadastrarPessoa("111.111.111-11", "Francisco", new String[] {"sup"});
		this.atividade2.cadastrarPessoa("111.111.111-12", "Marcelo", new String[] {"java"});
		this.atividade2.cadastrarAtividade("Estudar OO", "Estudar Code", "111.111.111-11");
		
		this.atividade3 = new Facade();
		this.atividade3.cadastrarPessoa("111.111.111-00", "Matheus", new String[] {"sup"});
		this.atividade3.cadastrarAtividade("Estudar OO", "Estudar Code", "111.111.111-00");
		this.atividade3.cadastrarTarefa("STD-0", "NOME1", new String[] {"Java"});
		this.atividade3.cadastrarTarefa("STD-0", "NOME2", new String[] {"HAB1"});
		this.atividade3.cadastrarTarefa("STD-0", "NOME3", new String[] {"HAB2"});
		this.atividade3.cadastrarTarefa("STD-0", "NOME4", new String[] {"HAB3"});
		this.atividade3.cadastrarTarefa("STD-0", "NOME5", new String[] {"HAB4"});
	}

}