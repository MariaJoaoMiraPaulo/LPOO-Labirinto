package maze.logic;

import java.util.Random;

import maze.cli.CommandLineInterface;

public class Jogo {

	private Tabuleiro tab;
	private Dragao dragao;
	private Heroi heroi;
	private Espada espada;
	private boolean fimDeJogo=false;
	private CommandLineInterface cli;
	private int modoJogo;


	Jogo(){
		tab=new Tabuleiro();
		dragao=new Dragao(3,1,'D');
		heroi=new Heroi(1,1,'H');
		espada=new Espada(4,1,'E');
		cli=new CommandLineInterface();
		tab.inserirChar(dragao.getLinha(), dragao.getColuna(), dragao.getSimbolo());
		tab.inserirChar(heroi.getLinha(), heroi.getColuna(), heroi.getSimbolo());
		tab.inserirChar(espada.getLinha(), espada.getColuna(), espada.getSimbolo());
	}

	public void moverHeroi(){


		boolean valido=false;
		int linha,coluna;
		do{
			linha=heroi.getLinha();
			coluna=heroi.getColuna();
			char direcao=cli.lerDirecao();
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
			else if (!dragao.isDormir()) {
				fimDeJogo=true;
			}
		}
	}

	public void moverDragao(){

		Random rn=new Random();
		int linha;
		int coluna;
		int direcao;
		boolean valido=false;

		if (!dragao.isDormir()){
			do{
				direcao=rn.nextInt(6);
				linha=dragao.getLinha();
				coluna=dragao.getColuna();
				switch (direcao){
				case 0: 
					coluna-=1;
					break;
				case 1: 
					coluna+=1;
					break;
				case 2:
					linha+=1;
					break;
				case 3:
					linha-=1;
					break;
				case 4:
					break;
				case 5:
					if (modoJogo==3){
						dragao.setDormir(true);
						dragao.setSimbolo('d');
						tab.inserirChar(linha, coluna, 'd');
					}
					break;

				}
				if(tab.retornaChar(linha,coluna)!='X' && tab.retornaChar(linha, coluna)!= 'S'){
					valido=true;
				}
			}while(!valido);

			if(espada.isCoberta())
				tab.inserirChar(dragao.getLinha(),dragao.getColuna(),'E');
			else tab.inserirChar(dragao.getLinha(),dragao.getColuna(),' ');

			dragao.setColuna(coluna);
			dragao.setLinha(linha);

			if(tab.retornaChar(linha,coluna)== 'E'){
				espada.setCoberta(true);
				tab.inserirChar(linha, coluna, 'F');
			}
			else {
				espada.setCoberta(false);
				tab.inserirChar(linha, coluna, dragao.getSimbolo());
			}
		}
		else if (dragao.isDormir()){
			direcao=rn.nextInt(1);
			linha=dragao.getLinha();
			coluna=dragao.getColuna();
			switch (direcao){
			case 0: 
				dragao.setDormir(false); 
				dragao.setSimbolo('D'); 
				tab.inserirChar(linha, coluna, 'D'); 
				break;
			case 1:
				break;
			}
		}
	}


	public boolean jogada( ){
		moverHeroi();
		verificaEspada();
		if(!dragao.isMorto())
			verificaDragao();
		if (modoJogo!=1){
			if(!dragao.isMorto())
				moverDragao();
		}
		if(!dragao.isMorto())
			verificaDragao();

		return fimDeJogo;
	}

	public Tabuleiro getTab() {
		return tab;
	}

	public Dragao getDragao() {
		return dragao;
	}

	public void setDragao(Dragao dragao) {
		this.dragao = dragao;
	}

	public int getModoJogo() {
		return modoJogo;
	}

	public void setModoJogo(int modoJogo) {
		this.modoJogo = modoJogo;
	}

}