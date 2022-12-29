package sapo.atividades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import sapo.pessoas.Pessoa;
/**
 * AtividadeRepositorio é responsável pelo armazenamento das atividades no sistema.
 * Cabe a ele guardar, fornecer, remover e procurar atividades dentro do seu Map de atividades.
 * 
 * @author José Arthur
 *
 */
public class AtividadeRepositorio {
	
	/**
	 * atividades - O mapa que armazena as atividades do sistema.
	 * count - Contador da quantidades de atividades no sistema.
	 */
    private Map<String,Atividade> atividades;
    private int count;
    
    /**
     * Construtor padrão do repositório de atividades.
     */
    public AtividadeRepositorio() {
    	this.atividades = new HashMap<>();
    }

    /**
     * Adiciona uma tarefa ao repositório. 
     * Cada tarefa tem nome, descrição e uma pessoa responsável.
     * 
     * @param nome Nome da atividade.
     * @param descricao Descrição da atividade.
     * @param pessoa Pessoa responsável pela atividade.
     * @return Retorna o id da atividade.
     */
    public String cadastrarAtividade(String nome, String descricao, Pessoa pessoa){
    	Atividade atividade = new Atividade(geradorId(nome), nome, descricao, pessoa);
        pessoa.adiconaComoResponsavel(atividade);
    	this.atividades.put(atividade.getid(), atividade);
    	String idAtividade = geradorId(nome);
        count++;
        return idAtividade;
    }
    
    /**
     * Método que gera um código para atividade.
     * 
     * @param nome Nome da atividade
     * @return Retorna o id gerado.
     */
    private String geradorId(String nome){
        String vogais = "aeiouAEIOU ";
        String[] nomeArray = nome.split("");
        String retornar = "";
        int count = 0;
        
        for(int i = 0; i < nome.length(); i++){
            if(!vogais.contains(nomeArray[i]) && count < 3){
                retornar += nomeArray[i].toUpperCase();
                count += 1;
            }
        }

        while(count<3){
            retornar += "X";
            count+=1;
        }
        System.out.println(retornar + "-" + this.count);
        return retornar + "-" + this.count;
    }

    /**
     * Recupera atividade.
     * 
     * @param id Id da atividade.
     * @return retorna a atividade.
     */
    public Atividade recuperarAtividade(String id){
        return atividades.get(id);
    }
    
    /**
     * Busca uma atividade com base no metadados de cada atividade, que pode ser partes
     * ou todo do seu id ou descrição.
     * 
     * Uma atividade é adicionada ao retorno caso tenha todos os termos procurados;
     * 
     * @param termos termos procurados.
     * @return Lista de atividades encontradas.
     */
	public Set<Atividade> busca(String[] termos) {
		Set<String> chaves = this.atividades.keySet();
		Set<Atividade> retorno;
		retorno =  new HashSet<>();
		for(String chave: chaves) {
			ArrayList<String> metadados= this.atividades.get(chave).getMetadados();
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
				retorno.add(this.atividades.get(chave));
			}
			
		}
		return retorno;
	}

}
