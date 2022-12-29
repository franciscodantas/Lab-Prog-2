package com.franciscodantas.similaridade;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.franciscodantas.lunr.documento.Documento;
import com.franciscodantas.lunr.documento.DocumentoService;

/**
 * Componente para tratamento da lógica de negócio relativa a similaridade.
 * 
 * @author francisco antonio dantas
 */
public class SimilaridadeService {
	
	/**
	 * DocumentoService a ser utilizado pelo SimilaridadeService.
	 */
	private DocumentoService documentoService;

	/**
	 * Inicialização da lógica de serviço.
	 * 
	 * @param documentoService DocumentoService a ser utilizado pelo
	 *                         SimilaridadeService.
	 */
	public SimilaridadeService(DocumentoService documentoService) {
		this.documentoService = documentoService;
	}

	/**
	 * Calcula e retorna a similaridade.
	 * 
	 * Para o cálculo da similaridade:
	 * <ul>
	 * <li>Pega o documento 1</li>
	 * <li>Pega o documento 2</li>
	 * <li>Pega os termos do documento 1 e coloca em um conjunto (Termos1)</li>
	 * <li>Pega os termos do documento 2 e coloca em um conjunto (Termos2)</li>
	 * <li>Calcula a interseção entre Termos1 e Termos2 (Inters)</li>
	 * <li>Calcula a união entre Termos1 e Termos2 (Uniao)</li>
	 * <li>A similaridade é o tamanho de Inters sobre o tamanho do conjunto
	 * Uniao</li>
	 * </ul>
	 * 
	 * @param docId1 Documento 1.
	 * @param docId2 Documento 2.
	 * @return Valor de similaridade (entre 0 e 1, inclusives) representando a
	 *         semelhança entre os documentos.
	 */
	public double similaridade(String docId1, String docId2) {
		// PEGA DOCUMENTO 1
		Optional<Documento> recdoc1 = this.documentoService.recuperaDocumento(docId1);
		// PEGA DOCUMENTO 2
		Optional<Documento> recdoc2 = this.documentoService.recuperaDocumento(docId2);
		// COLOCA TERMOS DO DOCUMENTO 1 EM UM CONJUNTO
		Documento doc1 = recdoc1.get(); 
		Set<String> termo1 = this.converteConjunto(doc1.getTexto());
		// COLOCA TERMOS DO DOCUMENTO 2 EM OUTRO CONJUNTO
		Documento doc2 = recdoc2.get(); 
		Set<String> termo2 = this.converteConjunto(doc2.getTexto());
		// A SIMILARIDADE É DETERMINADA PELO...
		double inters = 0;
		for(String aux1:termo1) {
			for(String aux2:termo2) {
				if(aux1.equals(aux2)) {
					inters += 1;
				}
			}
		}
		double uniao = (termo1.size() + termo2.size()) - inters;
		// --> (TAMANHO DA INTERSEÇÃO) / (TAMANHO DA UNIÃO DOS CONJUNTOS)
		return inters/uniao;
	}
	
	private Set<String> converteConjunto(String[] termo){
		Set<String> Set = new HashSet<>();
		for(String elemento: termo) {
			Set.add(elemento);
		}
		return Set;
	}
}
