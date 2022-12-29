package com.franciscodantas.lunr.documento;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import biblitex.TransformaTexto;

/**
 * Documento base java. As palavras-chave da linguagem ainda são preservadas
 * (como public, private, etc), mas elimina-se documentação e comentários.
 */
class DocumentoJava implements Documento {

	private String id;
	private String original;
	private String limpo;
	private Map<String, String> metadados;
	private String[] split;

	/**
	 * Cria o DocumentoJava, extraindo o texot base.
	 * 
	 * @param id       ID do documento a ser criado.
	 * @param original Código java original.
	 */
	public DocumentoJava(String id, String original) {
		this.id = id;
		this.original = original;
		var transformaTexto = new TransformaTexto();
		var txt = transformaTexto.transforma(TransformaTexto.Algoritmos.java, original);
		this.limpo = transformaTexto.transforma(TransformaTexto.Algoritmos.clean, txt).strip();
	}

	@Override
	public double metricaTextoUtil() {
		long extractedSize = (new TransformaTexto()).transforma(TransformaTexto.Algoritmos.cleanSpaces, this.limpo)
				.length();
		return (1.0 * extractedSize) / this.original.length();
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public String[] getTexto() {
		if (this.split == null) {
			this.split = (new TransformaTexto()).transforma(TransformaTexto.Algoritmos.cleanLines, this.limpo)
					.split(" ");
			Arrays.sort(this.split);
		}
		return this.split;
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
		DocumentoJava other = (DocumentoJava) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "===" + this.id + System.lineSeparator() + this.limpo;
	}

	@Override
	public Map<String, String> getMetadados() {
		if (this.metadados != null) {
			return this.metadados;
		}
		this.metadados = extractData(this.original);
		this.metadados.put("LINHAS", "" + this.original.chars().filter((value) -> '\n' == value).count());
		this.metadados.put("TAMANHO", "" + this.limpo.length());
		this.metadados.put("METADATADATE", "" + System.currentTimeMillis());
		this.metadados.put("TIPO", "" + "java");
		return this.metadados;
	}

	/*
	 * Metadados particulares de java: número de imports e autor da classe.
	 */
	private Map<String, String> extractData(String original2) {
		Map<String, String> metadados2 = new HashMap<>();
		metadados2.put("IMPORTS", "" + ((this.limpo.length() - this.limpo.replaceAll("import ", "").length()) / 7));
		metadados2.put("AUTHOR", "" + (this.original.indexOf("@author") != -1 ? "TRUE" : ""));
		return metadados2;
	}

	@Override
	public String getOriginal() {
		return this.original;
	}

}
