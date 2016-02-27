package game;
import java.util.Scanner;

public class Jogo {

	private Tabuleiro tab;
	private Dragao dragao;
	private Heroi heroi;
	private Espada espada;
	private boolean fimDeJogo=false;

	Jogo(){
		tab=new Tabuleiro();
		dragao=new Dragao(3,1,'D');
		heroi=new Heroi(1,1,'H');
		espada=new Espada(8,1,'E');
		tab.inserirChar(dragao.getLinha(), dragao.getColuna(), dragao.getSimbolo());
		tab.inserirChar(heroi.getLinha(), heroi.getColuna(), heroi.getSimbolo());
		tab.inserirChar(espada.getLinha(), espada.getColuna(), espada.getSimbolo());
		tab.desenhaTab();
	}

	public char lerDirecao(){
		System.out.print("Para onde pretende mover? (e,d,b,c)\n");
		char c;
		Scanner ler = new Scanner (System.in);
		c = ler.next().charAt(0); //take the first character from Scanner.next
		c=Character.toLowerCase(c);
		while(c!='e' && c!='d' && c!='c' && c!='b'){ //|| c!='E' || c!='D' || c!='C' || c!='B'
			System.out.print("Introduza uma entrada valida (e,d,c,b) \n");
			c = ler.next().charAt(0); //take the first character from Scanner.next	
			c=Character.toLowerCase(c);
		}
		return c;
	}

	public void moverHeroi(){


		boolean valido=false;
		int linha,coluna;
		do{
			linha=heroi.getLinha();
			coluna=heroi.getColuna();
			char direcao=lerDirecao();
			switch (direcao){
			case 'e': 
				coluna-=1;
				break;
			case 'd': 
				coluna+=1;
				break;
			case 'b':
				linha+=1;
				break;
			case 'c':
				linha-=1;
				break;
			}

			if(!dragao.morto){
				if(tab.retornaChar(linha,coluna)!='X' && tab.retornaChar(linha, coluna)!='S'){
					valido=true;
				}
			}
			else if(tab.retornaChar(linha,coluna)!='X')
				valido=true;


		}while(!valido);
		if(tab.retornaChar(linha,coluna)=='S')
			fimDeJogo=true;
		tab.inserirChar(heroi.getLinha(),heroi.getColuna(),' ');
		heroi.setColuna(coluna);
		heroi.setLinha(linha);
		tab.inserirChar(linha, coluna, heroi.getSimbolo());
	}

	public void verificaEspada(){

		if(heroi.getLinha()==espada.getLinha() && heroi.getColuna()==espada.getColuna()){
			heroi.setSimbolo('A');
			tab.inserirChar(espada.getLinha(),espada.getColuna(),heroi.getSimbolo());
		}
	}

	public void verificaDragao(){

		boolean mesmaPosicao=false;

		if(tab.retornaChar(heroi.getLinha()-1, heroi.getColuna())==dragao.getSimbolo())
			mesmaPosicao=true;
		else if(tab.retornaChar(heroi.getLinha()+1,heroi.getColuna())==dragao.getSimbolo())
			mesmaPosicao=true;
		else if(tab.retornaChar(heroi.getLinha(), heroi.getColuna()-1)==dragao.getSimbolo())
			mesmaPosicao=true;
		else if(tab.retornaChar(heroi.getLinha(), heroi.getColuna()+1)==dragao.getSimbolo())
			mesmaPosicao=true;

		if (mesmaPosicao){
			if (heroi.estaArmado()){
				dragao.setMorto(true);
				tab.inserirChar(dragao.getLinha(), dragao.getColuna(), ' ');
			}
			else {
				fimDeJogo=true;
			}
		}
	}


	public void jogar(){

		while(!fimDeJogo){
			moverHeroi();
			verificaEspada();
			if(!dragao.isMorto())
				verificaDragao();
			tab.desenhaTab();
		}
		System.out.println("Fim do Jogo!");
	}
}