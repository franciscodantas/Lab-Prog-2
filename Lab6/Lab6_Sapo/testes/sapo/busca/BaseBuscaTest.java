package sapo.busca;

import org.junit.jupiter.api.BeforeEach;

import sapo.Facade;
import sapo.atividades.AtividadeService;
import sapo.pessoas.PessoaService;

class BaseBuscaTest {
	
	protected Facade busca1;
	protected Facade busca2;
	protected PessoaService ps;
	protected AtividadeService as;

	@BeforeEach
	public void inicial() {
		var ps = new PessoaService();
		this.as = new AtividadeService(ps);
		this.busca1 = new Facade();
		this.busca2 = new Facade();
		
		// Cadastra algumas pessoas
		this.busca1.cadastrarPessoa("111.111.111-11", "Matheus", new String[] {"Java", "POO"});
		this.busca1.cadastrarPessoa("111.111.111-12", "João", new String[] {"Python", "POO"});
		this.busca1.cadastrarPessoa("111.111.111-13", "Livia Miranda", new String[] {"Java", "Ensino", "POO"});
		this.busca1.cadastrarPessoa("111.111.111-14", "Livia", new String[] {"Ensino"});
		
		// Cadastra algumas atividades na 1º pessoa cadastrada
		this.busca1.cadastrarAtividade("RVS encapsulamento", "Revisar o assunto de encapsulamento", "111.111.111-11");
		this.busca1.cadastrarAtividade("Estudar OO", "Atividade de estudo de OO. Revisar Assunto", "111.111.111-11");
		
		// Cadastra algumas tarefas na 1º atividade cadastrada
		this.busca1.cadastrarTarefa("RVS-0", "Estudar Encapsulamento", new String[] {"POO"});
		this.busca1.cadastrarTarefa("RVS-0", "Revisar Prova", new String[] {"Python"});
		this.busca1.cadastrarTarefa("RVS-0", "Fazer atividade", new String[] {"Python"});
		
		// Cadastra algumas pessoas, atividades e tarefas em busca2
		this.busca2.cadastrarPessoa("111.111.111-99", "Livia", new String[] {"Java", "POO"});
		this.busca2.cadastrarAtividade("RVS encapsulamento", "Revisar o assunto de encapsulamento", "111.111.111-99");
		this.busca2.cadastrarAtividade("Estudar OO", "Atividade de estudo de OO. Revisar Assunto", "111.111.111-99");
		this.busca2.cadastrarTarefa("RVS-0", "Estudar Encapsulamento", new String[] {"POO"});
			
		// Realiza algumas buscas em busca2
		this.busca2.exibirPessoas("Livia");
		this.busca2.exibirPessoas("Java");
		this.busca2.exibirPessoas("111.111.111-99");
		this.busca2.buscarAtividades("STD-1");
		this.busca2.buscarTarefas("encapsulamento");
		this.busca2.buscarTarefas("Estudar Encapsulamento");
			
	}

}