package estacionAqui;

/**
 * 
 * @author Francisco Antonio Sousa
 *
 */

public class Estacionamento {
	Vaga[] vagas;
	int quantVagas;
	
	public Estacionamento() {
		vagas = new Vaga[100];
		quantVagas = 0;
	}
	
	public boolean cadastravaga(double valor, String endereco, String url) {
		if(validaVaga(valor, endereco, url)) {
			throw new IllegalArgumentException("entrada invalida");
		}
		vagas[quantVagas] = new Vaga(quantVagas, valor, endereco, url);
		this.quantVagas += 1;
		return true;
		
	}
	
	private boolean validaVaga(double valor, String endereco, String url) {
		if(endereco == null) {
			return true;
		}
		else if(endereco.length() == 0) {
			return true;
		}
		if(url == null) {
			return true;
		}
		else if(url.length() == 0) {
			return true;
		}
		return false;
	}

	public boolean cadastravaga(double valor, String endereco) {
		if(validaVaga(valor, endereco)) {
			throw new IllegalArgumentException("entrada invalida");
		}
		vagas[quantVagas] = new Vaga(quantVagas, valor, endereco);
		this.quantVagas += 1;
		return true;
		
	}

	private boolean validaVaga(double valor, String endereco) {
		if(endereco == null) {
			return true;
		}
		else if(endereco.length() == 0) {
			return true;
		}
		return false;
	}
	
	public String mudarStatus(int posicao) {
		vagas[posicao].mudarStatus();
		return "Estado mudado para: " + vagas[posicao].getStatus();
	}
	
	public double simulaValor(int posicao, int horas) {
		if(validaSimula(posicao, horas)) {
			return vagas[posicao].valorDevido(horas);
		}
		else {
			throw new IllegalArgumentException("entrada invalida"); 
		}
	}
	
	private boolean validaSimula(int posicao, int horas) {
		if(posicao > 99 || posicao < 0) {
			return false;
		}
		else if(horas == 0) {
			return false;
		}
		else if(vagas[posicao] == null) {
			return false;
		}
		return true;
	}

	public int vagaLivre() {
		for(int i = 0; i < quantVagas; i++) {
			if(vagas[i].getStatus().equals("LIVRE")) {
				return i;
			}
		}
		return -1;
	}
	
	public String listavagas() {
		String retorno = "";
		for(int i = 0; i < quantVagas; i++) {
			retorno += vagas[i].toString() + "\n";
		}
		return retorno;
	}
	
	public int getVagasAtivas() {
		return this.quantVagas;
	}
	
	public String relatorio() {
		String relatorio = "";
		for(int i = 0; i < quantVagas; i++) {
			relatorio += "Vaga " + i + " - " + vagas[i].getUsos() + "\n";
		}
		return relatorio;
	}
	
	public int vagaLivre(String endereco, double area) {
		Vaga vagaComparada = new Vaga(0,area, endereco);
		for(int i = 0; i < this.quantVagas; i++) {
			if(vagas[i].equals(vagaComparada)) {
				if(vagas[i].getStatus().equals("LIVRE")) {
					return i;
				}
			}
		}
		return -1;
	}
}


