package sapo.tarefas;

import java.util.ArrayList;

import sapo.pessoas.Pessoa;

public interface TarefaInterface {
	
	public void setNome(String nome);
	
	public void adicionarHorasTarefa(int horas);
	
	public void removerHorasTarefa(int horas);
	
	public String getId();

	public boolean isEstadoTarefa();

	public String getNome();

	public void removerPessoa(String cpf);

	public int getQtdHoras();
	
	public String[] getMetadados();

	public void associarPessoa(String cpf, Pessoa pessoa);

	public void concluirTarefa();

	public ArrayList<Pessoa> getPessoasResponsaveis();

	public String[] getHabilidades();

	public int quantDePessoas();
	
	public String toString();
}
