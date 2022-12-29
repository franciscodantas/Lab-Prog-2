package Lab2_FRANCISCOANTONIO;

public class CoisaBonus {
	
	public static void main(String[] args) {
		disciplina();
		System.out.println("-----");
		atividadesComplementares();
		System.out.println("-----");
		descanso();
	}
	
	private static void descanso() {
	       Descanso descanso = new Descanso();
	       System.out.println(descanso.getStatusGeral());
	       descanso.defineHorasDescanso(30);
	       descanso.defineNumeroSemanas(1);
	       descanso.definirEmoji("*_*");
	       System.out.println(descanso.getStatusGeral());
	       descanso.defineHorasDescanso(26);
	       descanso.defineNumeroSemanas(2);
	       System.out.println(descanso.getStatusGeral());
	       descanso.defineHorasDescanso(26);
	       descanso.defineNumeroSemanas(1);
	       System.out.println(descanso.getStatusGeral());
	}
	
	private static void disciplina() {
		Disciplina prog2 = new Disciplina("PROGRAMACAO 2", 2);
		prog2.cadastraHoras(4);
		prog2.cadastraNota(1, 10);
		prog2.cadastraNota(2, 10);
		System.out.println(prog2.aprovado());
		System.out.println(prog2.toString());
		int[] pesos = {6,4};
		Disciplina prog1 = new Disciplina("PROGRAMACAO 1", 2, pesos);
		prog1.cadastraHoras(4);
		prog1.cadastraNota(1, 6);
		prog1.cadastraNota(2, 10);
		System.out.println(prog1.aprovado());
		System.out.println(prog1.toString());
	}
	
	private static void atividadesComplementares() {
		 AtividadesComplementares minhasAtividades = new AtividadesComplementares();
	     int horasEstagio = 300;
	     int mesesProjeto = 6;
	     double horasCurso = 40.5;
	     minhasAtividades.adicionarEstagio(horasEstagio, 8);
		 minhasAtividades.adicionarEstagio(450);
	     minhasAtividades.adicionarProjeto(mesesProjeto);
	     minhasAtividades.adicionarCurso(horasCurso);
	     String[] atividades = minhasAtividades.pegaAtividades();
	     for (int i = 0; i < atividades.length; i++) {
	    	 System.out.println(minhasAtividades.pegaAtividades()[i]);
	    	 }
	     System.out.println(minhasAtividades.contaCreditos());
	     
	     horasEstagio = 600;
	     mesesProjeto = 6;
	     horasCurso = 40.5;
	     minhasAtividades.adicionarEstagio(horasEstagio, 4);
	     minhasAtividades.adicionarEstagio(horasEstagio, 8);
	     minhasAtividades.adicionarProjeto(mesesProjeto);
	     minhasAtividades.adicionarCurso(horasCurso);
	     atividades = minhasAtividades.pegaAtividades();
	     for (int i = 0; i < atividades.length; i++) {
	    	 System.out.println(minhasAtividades.pegaAtividades()[i]);
	    	 }
	     System.out.println(minhasAtividades.contaCreditos());
	}

}
