package com.franciscodantas.lunr.documento;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

/**
 * Objeto de resposta com um documento. Esse objeto deve ser usado como resposta
 * ao usuário para evitar ou limitar o acesso a detalhes internos do documento,
 * como metadados e array dos termos.
 * 
 * DTO representa um objeto de transferência de dados (DATA TRANSFER OBJECT).
 */
public class DocumentoDTO {

	private String id;
	private Map<String, String> metadados;
	private String[] texto;
	private double metricaTextoUtil;
	private String saida;
	private String original;

	/**
	 * Construtor padrão a partir do documento.
	 * 
	 * Todos os dados do DocumentoDTO devem ser uma cópia dos atributos do
	 * documento.
	 * 
	 * @param d Documento a ser usado como base do DocumentoDTO.
	 */
	public DocumentoDTO(Documento d) {
		this.id = d.getId();
		this.metadados = Collections.unmodifiableMap(d.getMetadados());
		this.texto = Arrays.copyOf(d.getTexto(), d.getTexto().length);
		this.metricaTextoUtil = d.metricaTextoUtil();
		this.saida = d.toString();
		this.original = d.getOriginal();
	}

	/**
	 * Retorna métrica útil.
	 * 
	 * @return Métrica útil do texto.
	 */
	public double metricaTextoUtil() {
		return this.metricaTextoUtil;
	}

	/**
	 * Retorna ID do documento.
	 * 
	 * @return ID do documento.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Retorna os termos do documento.
	 * 
	 * @return Os termos do documento.
	 */
	public String[] getTexto() {
		return this.texto;
	}

	/**
	 * Retorna o mapa de metadados.
	 * 
	 * @return O mapa de metadados.
	 */
	public Map<String, String> getMetadados() {
		return this.metadados;
	}

	@Override
	public String toString() {
		return this.saida;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocumentoDTO other = (DocumentoDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	/**
	 * Retorna o texto original do documento.
	 * @return Uma String que é o texto armazenando no documento sem tratamento.
	 */
	public String getOriginal() {
		return this.original;
	}

	
	
}
