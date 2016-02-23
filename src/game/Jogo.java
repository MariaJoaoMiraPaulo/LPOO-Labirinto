package game;

public class Jogo {

	private Tabuleiro tab;
	private Dragao dragao;
	private Heroi heroi;
	
	Jogo(){
		tab=new Tabuleiro();
		dragao=new Dragao(3,1,'D');
		heroi=new Heroi(1,1,'H');
		tab.inserirChar(dragao.getLinha(), dragao.getColuna(), dragao.getSimbolo());
		tab.inserirChar(heroi.getLinha(), heroi.getColuna(), heroi.getSimbolo());
		tab.printTab();
	}
	
	public void moverHeroi(){
		System.out.print("Para onde pretende mover? (e,d,f,t)");
	}
}
