package com.franciscodantas.apresentacao;

class DocumentoExemplos {

	public String sampleJava() {
		return "package com.franciscodantas.lunr;\n"
				+ "\n"
				+ "import java.util.Optional;\n"
				+ "\n"
				+ "import com.franciscodantas.lunr.busca.BuscaController;\n"
				+ "import com.franciscodantas.lunr.busca.HistoricoBusca;\r\n"
				+ "import com.franciscodantas.lunr.documento.Documento;\r\n"
				+ "import com.franciscodantas.lunr.documento.DocumentoController;\r\n"
				+ "import com.franciscodantas.lunr.documento.DocumentoDTO;\r\n"
				+ "import com.franciscodantas.lunr.documento.DocumentoRepository;\r\n"
				+ "import com.franciscodantas.lunr.documento.DocumentoService;\r\n"
				+ "import com.franciscodantas.similaridade.ResultadoSimilaridade;\r\n"
				+ "import com.franciscodantas.similaridade.SimilaridadeController;\r\n"
				+ "\r\n"
				+ "/**\r\n"
				+ " * O Lunr é um sistema de cadastro e busca de documentos, use como de realização\r\n"
				+ " * de operações de similaridade entre os documentos cadastrados.\r\n"
				+ " * \r\n"
				+ " * O LunrFacade por sua vez é responsável por centralizar todas as operações de\r\n"
				+ " * lógica no sistema bem como inicializar todos os objetos nele existentes.\r\n"
				+ " * \r\n"
				+ " * Por centralizar a inicialização dos objetos principais de lógica do sistema,\r\n"
				+ " * é importante que o LunrFacade seja a única fote de acesso as demais\r\n"
				+ " * operações.\r\n"
				+ " * @author matheus gaudencio\r\n"
				+ " */\r\n"
				+ "public class LunrFacade {\r\n"
				+ "\r\n"
				+ "	private DocumentoService ds;\r\n"
				+ "	private DocumentoController dc;\r\n"
				+ "	private BuscaController bc;\r\n"
				+ "	private SimilaridadeController ps;\r\n"
				+ "\r\n"
				+ "	public LunrFacade() {\r\n"
				+ "		this.ds = new DocumentoService(new DocumentoRepository());\r\n"
				+ "		this.dc = new DocumentoController(ds);\r\n"
				+ "		this.bc = new BuscaController(ds);\r\n"
				+ "		this.ps = new SimilaridadeController(ds);\n"
				+ "	}\n"
				+ "	\n"
				+ "	public void adicionaDocumentoTxt(String id, String use) {\n"
				+ "		this.dc.adicionaDocumentoTxt(id, use);\n"
				+ "	}\n"
				+ "}";
	}

	public String sampleHTML() {
		return "<!doctype html>\n"
				+ "<html>\n"
				+ "<head>\n"
				+ "    <title>Example Domain</title>\n"
				+ "\n"
				+ "    <meta charset=\"utf-8\" />\r\n"
				+ "    <meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\r\n"
				+ "    <style type=\"text/css\">\r\n"
				+ "    body {\r\n"
				+ "        background-color: #f0f0f2;\r\n"
				+ "        margin: 0;\r\n"
				+ "        padding: 0;\r\n"
				+ "        font-family: -apple-system, system-ui, BlinkMacSystemFont, \"Segoe UI\", \"Open Sans\", \"Helvetica Neue\", Helvetica, Arial, sans-serif;\r\n"
				+ "        \r\n"
				+ "    }\r\n"
				+ "    div {\r\n"
				+ "        width: 600px;\r\n"
				+ "        margin: 5em auto;\r\n"
				+ "        padding: 2em;\r\n"
				+ "        background-color: #fdfdff;\r\n"
				+ "        border-radius: 0.5em;\r\n"
				+ "        box-shadow: 2px 3px 7px 2px rgba(0,0,0,0.02);\r\n"
				+ "    }\r\n"
				+ "    a:link, a:visited {\r\n"
				+ "        color: #38488f;\r\n"
				+ "        text-decoration: none;\r\n"
				+ "    }\r\n"
				+ "    @media (max-width: 700px) {\r\n"
				+ "        div {\r\n"
				+ "            margin: 0 auto;\r\n"
				+ "            width: auto;\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "    </style>    \r\n"
				+ "</head>\r\n"
				+ "\r\n"
				+ "<body>\r\n"
				+ "<div>\r\n"
				+ "    <h1>Example Domain</h1>\r\n"
				+ "    <p>This domain is for use in illustrative examples in documents. You may use this" + "\n"
				+ "    domain in literature without prior coordination or asking for permission.</p>" + "\n"
				+ "    <p><a href=\"https://www.iana.org/domains/example\">More information...</a></p>" + "\n"
				+ "</div>" + "\n"
				+ "</body>" + "\n"
				+ "</html>" + "\n";
	}
	
}
