package testes;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import agenda.*;

/**
 * 
 * @author Francisco Antonio
 *
 */
public class TestAgenda {

    Agenda agenda;
    
    /**
     * faz antes de cada teste
     */
    @BeforeEach
    public void before() {
    	agenda = new Agenda();
    }

    /**
     * Testa a criação de um novo contato na agenda
     */
    @Test
    public void criarContato() {
        int posicao = 1;
        String nome = "Francisco";
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        assertEquals("CADASTRO REALIZADO", agenda.cadastraContato(posicao, nome, sobrenome, telefone));
        
        assertEquals("CADASTRO REALIZADO", agenda.cadastraContato(100,"Luana", "Taynara", "8399819231221"));
    }
    
    
    /**
     * testa a sobreescrita de um contato
     */
    @Test
    public void sobreEscreveContato() {
        int posicao = 1;
        String nome = "Francisco";
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        agenda.cadastraContato(posicao, nome, sobrenome, telefone);
        assertEquals("\nDados do contato:\n" + "\n" + nome + " " + sobrenome + "\n" + telefone + "\n", agenda.getContato(posicao));
        assertEquals("CADASTRO REALIZADO", agenda.cadastraContato(posicao, "Luana", "Taynara", "8399819231221"));
        assertEquals("\nDados do contato:\n" + "\n" + "Luana" + " " + "Taynara" + "\n" + "8399819231221" + "\n", agenda.getContato(posicao));
    }
    
    /**
     * testa a criacao de um contato sem telefone 
     */
    @Test
    public void semTelefone() {
    	assertEquals("CONTATO INVALIDO", agenda.cadastraContato(1, "Luana", "Taynara", null));
    	assertEquals("CONTATO INVALIDO", agenda.cadastraContato(1, "Luana", "Taynara", ""));
    }

    /**
     * Testa os casos de posição invalida na criação do contato
     */
    @Test
    public void criarContatoPosicaoInvalida() {
        int posicao = 101;
        String nome = "Francisco";
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        assertEquals("POSIÇÃO INVALIDA", agenda.cadastraContato(posicao, nome, sobrenome, telefone));

        posicao = 0;
        nome = "Francisco";
        sobrenome = "Antonio";
        telefone = "83991000000";
        assertEquals("POSIÇÃO INVALIDA", agenda.cadastraContato(posicao, nome, sobrenome, telefone));
    }

    /**
     * Testa os casos de contato invalida na criação do contato
    */
    @Test
    public void contatoInvalido() {
        int posicao = 100;
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        assertEquals("CONTATO INVALIDO", agenda.cadastraContato(posicao, null, sobrenome, telefone));
        assertEquals("CONTATO INVALIDO", agenda.cadastraContato(posicao, "", sobrenome, telefone));
    }

    /**
     * Testa os casos de contato já cadastrado na criação do contato
     */
    @Test
    public void contatoJaCadastrado() {
        int posicao = 100;
        String nome = "Francisco";
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        agenda.cadastraContato(posicao, nome, sobrenome, telefone);
        posicao = 10;
        nome = "Francisco";
        sobrenome = "Antonio";
        telefone = "83991000000";
        assertEquals("CONTATO JA CADASTRADO", agenda.cadastraContato(posicao, nome, sobrenome, telefone));
    }

    /**
     * testa a exibição do contato
     */
    @Test
    public void exibirContato() {
        int posicao = 1;
        String nome = "Francisco";
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        agenda.cadastraContato(posicao, nome, sobrenome, telefone);
        assertEquals("\nDados do contato:\n" + "\n" + nome + " " + sobrenome + "\n" + telefone + "\n", agenda.getContato(posicao));
    }
    
    /**
     * exibi um contato vazio 
     */
    @Test
    public void exibiVazio() {
    	assertEquals("POSIÇÃO INVALIDA", agenda.getContato(1));
    }
    
    /**
     * exibi um contato acima do limite 
     */
    @Test
    public void exibiAcima() {
    	assertEquals("POSIÇÃO INVALIDA", agenda.getContato(101));
    }
    
    /**
     * exibi um contato abaixo do limite 
     */
    @Test
    public void exibiAbaixo() {
    	assertEquals("POSIÇÃO INVALIDA", agenda.getContato(0));
    }

    /**
     * testa a exibição do contato com favorito
     */
    @Test
    public void exibirContatosFavoritos() {
        int posicao = 1;
        String nome = "Francisco";
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        agenda.cadastraContato(posicao, nome, sobrenome, telefone);
        agenda.adicionafavorito(posicao);
        assertEquals("\nDados do contato:\n" + "\n<3"+ " " + nome + " " + sobrenome + "\n" + telefone + "\n", agenda.getContato(posicao));
    }
    
    /**
     * exibi um contato com tags
     */
    @Test
    public void exibirContatosTags() {
        int posicao = 1;
        String nome = "Francisco";
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        agenda.cadastraContato(posicao, nome, sobrenome, telefone);
        String tag = "Ufcg";
        String[] posicoes = {"1"};
        agenda.adicionaTags(posicoes, tag, "1");
        assertEquals("\nDados do contato:\n" + "\n" + nome + " " + sobrenome + "\n" + telefone + "\n" + tag, agenda.getContato(posicao));
    }
    
    /**
     * testa a listagem de contatos
     */
    @Test
    public void listaContatos() {
    	int posicao = 1;
        String nome = "Francisco";
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        agenda.cadastraContato(posicao, nome, sobrenome, telefone);
        Contato[] contatos = new Contato[101];
        contatos[posicao] = new Contato(nome, sobrenome, telefone);
        assertArrayEquals(contatos, agenda.getContatos());
    }

    /**
     * favorita um contato
     */
    @Test
    public void favoritarContato() {
        int posicao = 1;
        String nome = "Francisco";
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        agenda.cadastraContato(posicao, nome, sobrenome, telefone);
        assertEquals("CONTATO FAVORITADO NA POSIÇÃO 1!", agenda.adicionafavorito(1));
    }
    
    /**
     * testa a adicao de uma tag a varios contatos
     */
    @Test
    public void tagVariosContatos() {
    	agenda.cadastraContato(1, "Francisco", "Antonio", "83991000000");
    	agenda.cadastraContato(2, "Luana", "Taynara", "8398176757473");
    	String[] posicoes = {"1", "2"};
    	agenda.adicionaTags(posicoes, "Casal", "1");
    	assertEquals("\nDados do contato:\n" + "\n" + "Francisco" + " " + "Antonio" + "\n" + "83991000000" + "\n" + "Casal", agenda.getContato(1));
    	assertEquals("\nDados do contato:\n" + "\n" + "Luana" + " " + "Taynara" + "\n" + "8398176757473" + "\n" + "Casal", agenda.getContato(2));
    }
    
    /**
     * testa a adicao de varias tags a um mesmo contato
     */
    @Test
    public void contatoVariasTags() {
    	int posicao = 1;
        String nome = "Francisco";
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        agenda.cadastraContato(posicao, nome, sobrenome, telefone);
        String tag = "Ufcg";
        String tag2 = "Aluno";
        String[] posicoes = {"1"};
        agenda.adicionaTags(posicoes, tag, "1");
        agenda.adicionaTags(posicoes, tag2, "2");
        assertEquals("\nDados do contato:\n" + "\n" + nome + " " + sobrenome + "\n" + telefone + "\n" + tag + " " + tag2, agenda.getContato(posicao));
    }
    
    /**
     * Testa a listagem de favoritos 
     */
    @Test
    public void listaFavoritos() {
    	int posicao = 1;
        String nome = "Francisco";
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        agenda.cadastraContato(posicao, nome, sobrenome, telefone);
        agenda.cadastraContato(2, "Luana", "Taynara", "8398176757473");
        agenda.adicionafavorito(posicao);
        Contato[] contatos = new Contato[101];
        contatos[1] = new Contato(nome, sobrenome, telefone);
        assertArrayEquals(contatos, agenda.getFavoritos());
    }

    /**
     * adiciona uma tag ao contato
     */
    @Test
    public void adicionaTags() {
        int posicao = 1;
        String nome = "Francisco";
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        agenda.cadastraContato(posicao, nome, sobrenome, telefone);
        String tag = "Ufcg";
        String[] posicoes = {"1"};
        agenda.adicionaTags(posicoes, tag, "1");
        assertEquals("\nDados do contato:\n" + "\n" + nome + " " + sobrenome + "\n" + telefone + "\n" + tag, agenda.getContato(posicao));
    }

    /**
     * Testa a remoção de algum contato
     */
    @Test
    public void removeContato() {
        int posicao = 1;
        String nome = "Francisco";
        String sobrenome = "Antonio";
        String telefone = "83991000000";
        agenda.cadastraContato(posicao, nome, sobrenome, telefone);
        String[] posicoes = {"1"};
        agenda.removeContato(posicoes);
        assertEquals("POSIÇÃO INVALIDA", agenda.getContato(posicao));
    }
}
