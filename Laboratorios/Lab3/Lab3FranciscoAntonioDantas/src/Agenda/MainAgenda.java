package agenda;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author Francisco Antonio
 *
 */
public class MainAgenda {
	
	/**
	 * metodo main
	 * 
	 * @param args comandos da linha de comando
	 */
	public static void main(String[] args) {
		Agenda agenda = new Agenda();

		System.out.println("Carregando agenda inicial");
		try {
			/*
			 * Essa � a maneira de lidar com possíveis erros por falta do arquivo. 
			 */
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, agenda, scanner);
		}

	}

	/**
	 * Exibe o menu e captura a escolha do/a usuario/a.
	 * 
	 * @param scanner Para captura da opcao do usuario.
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.print(
				"\n---\nMENU\n" + 
						"(C)adastrar Contato\n" + 
						"(L)istar Contatos\n" + 
						"(E)xibir Contato\n" +
						"(F)avoritos\n" +
						"(A)dicionar Favorito\n" +
						"(T)ags\n" +
						"(R)emover Contato\n"+
						"(S)air\n" + 
						"\n" + 
						"Opção> ");
		return scanner.nextLine().toUpperCase();
	}

	/**
	 * Interpreta a opcao escolhida por quem esta usando o sistema.
	 * 
	 * @param opcao   Opcao digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
		case "C":
			cadastraContato(agenda, scanner);
			break;
		case "L":
			listaContatos(agenda);
			break;
		case "E":
			exibeContato(agenda, scanner);
			break;
		case "F":
			favoritos(agenda);
			break;
		case "A":
			adicionarFavorito(agenda, scanner);
			break;
		case "T":
			adicionarTags(agenda,scanner);
			break;
		case "R":
			removerContato(agenda,scanner);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("Opção invalida!");
		}
	}
	
	/**
	 * Remove um contato da agenda
	 * 
	 * @param agenda A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void removerContato(Agenda agenda, Scanner scanner) {
		System.out.print("Contato(s)> ");
		String[] posicoes = scanner.nextLine().split(" ");
		agenda.removeContato(posicoes);
	}

	/**
	 * Imprime lista de contatos da agenda.
	 * 
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listaContatos(Agenda agenda) {
		System.out.println("\nLista de contatos: ");
		Contato[] contatos = agenda.getContatos();
		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] != null) {
				System.out.println("\n" + formataContato(i, contatos[i]));
			}
		}
	}

	/**
	 * Imprime os detalhes de um dos contatos da agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nQual contato> ");
		int posicao = Integer.parseInt(scanner.nextLine());
		String contato = agenda.getContato(posicao);
		System.out.println(contato);
	}

	/**
	 * Formata um contato para impressao na interface. 
	 * 
	 * @param posicao A posicao do contato (a ser exibida).
	 * @param contato O contato a ser impresso.
	 * @return A String formatada.
	 */
	private static String formataContato(int posicao, Contato contato) {
		return posicao + " - " + contato.getNomeCompleto();
	}

	/**
	 * Cadastra um contato na agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informacoes do contato.
	 */
	private static void cadastraContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nPosição na agenda> ");
		int posicao = Integer.parseInt(scanner.nextLine());
		System.out.print("\nNome> ");
		String nome = scanner.nextLine();
		System.out.print("\nSobrenome> ");
		String sobrenome = scanner.nextLine();
		System.out.print("\nTelefone> ");
		String telefone = scanner.nextLine();
		System.out.println(agenda.cadastraContato(posicao, nome, sobrenome, telefone));
	}

	/**
	 * Adiciona um favorito ao contato.
	 * 
	 * @param agenda a agenda que vai ser manipulada.
	 * @param scanner Objeto que ler o input.
	 */
	private static void adicionarFavorito(Agenda agenda, Scanner scanner) {
		System.out.print("\nQual contato> ");
		int posicao = Integer.parseInt(scanner.nextLine());
		String favorito = agenda.adicionafavorito(posicao);
		System.out.println(favorito);
	}

	/**
	 * Exibe uma lista de todos os favoritos do sistema.
	 * 
	 * @param agenda A agenda que est� sendo usada.
	 */
	private static void favoritos(Agenda agenda) {
		Contato[] contatos = agenda.getFavoritos();
		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] != null) {
				System.out.println("\n" + formataContato(i, contatos[i]));
			}
		}
	}

	/**
	 * Adiciona uma nova tag ao contato
	 * 
	 * @param agenda A agenda que est� sendo usada.
	 * @param scanner Objeto que ler o input.
	 */
	private static void adicionarTags(Agenda agenda, Scanner scanner) {
		System.out.print("Qual contato (s)> ");
		String[] posicoes = scanner.nextLine().split(" ");
		System.out.print("Tag> ");
		String tag = scanner.nextLine();
		System.out.print("Posicao tag> ");
		String posicaoTag = scanner.nextLine();
		agenda.adicionaTags(posicoes, tag, posicaoTag);
	}

	/**
	 * Sai da aplicacao.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

	/**
	 * Le uma agenda de um arquivo csv. 
	 * 
	 * @param arquivoContatos O caminho para o arquivo.
	 * @param agenda A agenda que deve ser populada com os dados. 
	 * @throws IOException Caso o arquivo não exista ou não possa ser lido.
	 */
	private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		LeitorDeAgenda leitor = new LeitorDeAgenda();
		
		int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
		System.out.println("Carregamos " + carregados + " registros.");
	}
}
