package maze.logic;

import java.util.Random;
import maze.cli.CommandLineInterface;
import maze.logic.Dragao.EstadoDragao;
import maze.logic.Heroi.EstadoHeroi;


public class Jogo {

	private Tabuleiro tab;
	private Dragao dragao;
	private Heroi heroi;
	private Espada espada;
	private boolean fimDeJogo=false;
	private CommandLineInterface cli;
	private int modoJogo;

	public enum Movimento{
		DIREITA,ESQUERDA,CIMA,BAIXO
	}
	
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

	public boolean podeMoverHeroi(Movimento direcao ){
		boolean valido=false;
		
		int linha=heroi.getLinha(),coluna=heroi.getColuna();
		
		switch (direcao){
		
		case DIREITA: 
			coluna+=1;
			break;
		case ESQUERDA:
			coluna-=1;
			break;
		case BAIXO:
			linha+=1;
			break;
		case CIMA:
			linha-=1;
			break;
		}

		if(dragao.getEstado()==EstadoDragao.ACORDADO || dragao.getEstado()==EstadoDragao.DORMIR){
			if(tab.retornaChar(linha,coluna)!='X' && tab.retornaChar(linha, coluna)!='S'){
				valido=true;
			}
		}
		else if(tab.retornaChar(linha,coluna)!='X')
			valido=true;

		return valido;

	}

	public void moverHeroi(){

		int linha,coluna;
		Movimento direcao=cli.lerDirecao();
		tab.inserirChar(heroi.getLinha(),heroi.getColuna(),' ');
		switch (direcao){
		case ESQUERDA: 
			if(podeMoverHeroi(Movimento.ESQUERDA))
				heroi.moverEsquerda();
			break;
		case DIREITA: 
			if(podeMoverHeroi(Movimento.DIREITA)){
				heroi.moverDireita();
				System.out.println("Quero virar a direita");
			}
			break;
		case BAIXO:
			if(podeMoverHeroi(Movimento.BAIXO))
				heroi.moverBaixo();
			break;
		case CIMA:
			if(podeMoverHeroi(Movimento.CIMA))
				heroi.moverCima();
			break;
		}

		verificaSaida();
		tab.inserirChar(heroi.linha, heroi.coluna, heroi.getSimbolo());
	}
	
	public void verificaSaida(){
		
		if(tab.retornaChar(heroi.linha,heroi.coluna)=='S')
			fimDeJogo=true;
	}

	public void verificaEspada(){

		if(heroi.getLinha()==espada.getLinha() && heroi.getColuna()==espada.getColuna()){
			heroi.setSimbolo('A');
			heroi.setEstado(EstadoHeroi.ARMADO);
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
			if (heroi.getEstado()==EstadoHeroi.ARMADO){
				dragao.setEstado(EstadoDragao.MORTO);
				tab.inserirChar(dragao.getLinha(), dragao.getColuna(), ' ');
			}
			else if (dragao.getEstado()==EstadoDragao.ACORDADO) {
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

		if (dragao.getEstado()==EstadoDragao.ACORDADO){
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
						dragao.setEstado(EstadoDragao.DORMIR);;
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
		else if (dragao.getEstado()==EstadoDragao.DORMIR){
			direcao=rn.nextInt(1);
			linha=dragao.getLinha();
			coluna=dragao.getColuna();
			switch (direcao){
			case 0: 
				dragao.setEstado(EstadoDragao.ACORDADO);; 
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
		if(dragao.getEstado()==EstadoDragao.ACORDADO)
			verificaDragao();
		if (modoJogo!=1){
			if(dragao.getEstado()==EstadoDragao.ACORDADO)
				moverDragao();
		}
		if(dragao.getEstado()==EstadoDragao.ACORDADO || dragao.getEstado()==EstadoDragao.DORMIR)
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