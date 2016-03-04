package maze.logic;

import java.awt.Point;
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
		tab.inserirChar(dragao.getP(), dragao.getSimbolo());
		tab.inserirChar(heroi.getP(), heroi.getSimbolo());
		tab.inserirChar(espada.getP(), espada.getSimbolo());
	}

	public boolean podeMoverHeroi(Movimento direcao ){
		boolean valido=false;

		//	int linha=heroi.getP().x,coluna=heroi.getP().y;
		Point p= (Point)heroi.getP().clone();

		switch (direcao){

		case DIREITA:
			p.y+=1;
			break;
		case ESQUERDA:
			p.y-=1;
			break;
		case BAIXO:
			p.x+=1;
			break;
		case CIMA:
			p.x-=1;
			break;
		default:
			break;
		}

		if(dragao.getEstado()==EstadoDragao.ACORDADO || dragao.getEstado()==EstadoDragao.DORMIR){
			if(tab.retornaChar(p)!='X' && tab.retornaChar(p)!='S' && tab.retornaChar(p)!= 'd'){
				valido=true;
			}
		}
		else if(tab.retornaChar(p)!='X')
			valido=true;

		return valido;

	}

	public void moverHeroi(){

		Movimento direcao=cli.lerDirecao();
		tab.inserirChar(heroi.getP(),' ');
		switch (direcao){
		case ESQUERDA: 
			if(podeMoverHeroi(Movimento.ESQUERDA))
				heroi.moverEsquerda();
			break;
		case DIREITA: 
			if(podeMoverHeroi(Movimento.DIREITA)){
				heroi.moverDireita();
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
		default:
			break;
		}

		verificaSaida();
		tab.inserirChar(heroi.getP(), heroi.getSimbolo());
	}

	public void verificaSaida(){

		if(tab.retornaChar(heroi.getP())=='S')
			fimDeJogo=true;
	}

	public void verificaEspada(){

		if(heroi.getP().x==espada.getP().x && heroi.getP().y==espada.getP().y){
			heroi.setSimbolo('A');
			heroi.setEstado(EstadoHeroi.ARMADO);
			tab.inserirChar(espada.getP(),heroi.getSimbolo());
		}
	}

	public void verificaDragao(){

		boolean mesmaPosicao=false;

		if(dragao.getSimbolo()=='D' || heroi.getSimbolo()=='A'){
			if(tab.retornaChar(heroi.getP().x-1, heroi.getP().y)==dragao.getSimbolo())
				mesmaPosicao=true;
			else if(tab.retornaChar(heroi.getP().x+1,heroi.getP().y)==dragao.getSimbolo())
				mesmaPosicao=true;
			else if(tab.retornaChar(heroi.getP().x, heroi.getP().y-1)==dragao.getSimbolo())
				mesmaPosicao=true;
			else if(tab.retornaChar(heroi.getP().x, heroi.getP().y+1)==dragao.getSimbolo())
				mesmaPosicao=true;

			if (mesmaPosicao){
				if (heroi.getEstado()==EstadoHeroi.ARMADO){
					dragao.setEstado(EstadoDragao.MORTO);
					tab.inserirChar(dragao.getP(), ' ');
				}
				else if (dragao.getEstado()==EstadoDragao.ACORDADO) {
					fimDeJogo=true;
				}
			}
		}
	}

	public void dragaoEEspada(Point p){
		if(espada.isCoberta())
			tab.inserirChar(dragao.getP(),'E');
		else tab.inserirChar(dragao.getP(),' ');

		dragao.setP(p);

		if(tab.retornaChar(p)== 'E'){
			espada.setCoberta(true);
			tab.inserirChar(p, 'F');
		}
		else {
			espada.setCoberta(false);
			tab.inserirChar(p, dragao.getSimbolo());
		}
	}
	
	public void dragaoADormir(){
		Random rn=new Random();
		int direcao;
		
		direcao=rn.nextInt(2);

		switch (direcao){
		case 0: 
			break;
		case 1:
			dragao.setEstado(EstadoDragao.ACORDADO);
			dragao.setSimbolo('D'); 
			tab.inserirChar(dragao.getP(), 'D');
			break;
		}
	}
	
	public void dragaoAcordado(){
		
		Random rn=new Random();

		int direcao;
		boolean valido=false;
		Point p;
		
		direcao=rn.nextInt(6);
		p=(Point)dragao.getP().clone();

		switch (direcao){
		case 0: 
			p.y-=1;
			break;
		case 1: 
			p.y+=1;
			break;
		case 2:
			p.x+=1;
			break;
		case 3:
			p.x-=1;
			break;
		case 4:
			break;
		case 5:
			if (modoJogo==3){
				dragao.setEstado(EstadoDragao.DORMIR);
				dragao.setSimbolo('d');
				tab.inserirChar(p, 'd');
			}
			break;

		}
		if(tab.retornaChar(p)!='X' && tab.retornaChar(p)!= 'S'){
			valido=true;
		}

		if(valido){
			dragaoEEspada(p);
		}
	}

	public void moverDragao(){

		if (dragao.getEstado()==EstadoDragao.ACORDADO){
			dragaoAcordado();
		}
		else if (dragao.getEstado()==EstadoDragao.DORMIR ){
			dragaoADormir();
		}
	}


	public boolean jogada( ){
		moverHeroi();
		verificaEspada();
		if(dragao.getEstado()==EstadoDragao.ACORDADO)
			verificaDragao();
		if (modoJogo!=1){
			if(dragao.getEstado()!=EstadoDragao.MORTO)
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