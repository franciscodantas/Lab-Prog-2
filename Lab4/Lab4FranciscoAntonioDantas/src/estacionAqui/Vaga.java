package estacionAqui;

import java.util.Objects;

/**
 * 
 * @author Francisco Antonio Sousa
 *
 */

public class Vaga {
	
	private int id;
	private double valor;
	private String endereco;
	private String url;
	private String status;
	private double area;
	private int usos;

	public Vaga(int id, double area, String endereco) {
		this.id = id;
		this.area = area;
		this.valor = 3 + 0.1*area;
		this.endereco = endereco;
		this.url = "https://";
		this.status = "LIVRE";
		this.usos = 0;
	}

	public Vaga(int id, double area, String endereco, String url) {
		this.id = id;
		this.valor = 3 + 0.1*area;
		this.area = area;
		this.endereco = endereco;
		this.url = url;
		this.status = "LIVRE";
		this.usos = 0;
	}

	public void mudarStatus() {
		if(this.status.equals("LIVRE")) {
			this.status = "OCUPADO";
			this.usos += 1;
		}else {
			this.status = "LIVRE";
		}
	}

	public String getStatus() {
		return this.status;
	}

	public double valorDevido(int horas) {
		return horas * this.valor;
	}
	
	@Override
	public String toString() {
		return this.id + " - " + this.endereco + " - " + this.url + " - " + this.getStatus();
	}
	
	public int getUsos() {
		return this.usos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(area, endereco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vaga other = (Vaga) obj;
		return Double.doubleToLongBits(area) == Double.doubleToLongBits(other.area)
				&& Objects.equals(endereco, other.endereco);
	}
	
	

}
