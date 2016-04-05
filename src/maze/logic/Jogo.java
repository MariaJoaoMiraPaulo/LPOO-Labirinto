package maze.logic;


import java.awt.Point;
import java.util.Random;
import maze.cli.CommandLineInterface;
import maze.logic.Dragao.EstadoDragao;
import maze.logic.Heroi.EstadoHeroi;
import maze.logic.Jogo.Movimento;

import java.util.ArrayList;

/**
 * Jogo.java - Classe que contem todos os elementos do jogo
 */
public class Jogo {
	/**
	 * Tabuleiro do jogo
	 */
	private Tabuleiro tab;
	/**
	 * Heroi do jogo
	 */
	private Heroi heroi;
	/**
	 * Espada do jogo
	 */
	private Espada espada;
	/**
	 * flag que indica se o jogo acabou ou nao
	 */
	private boolean fimDeJogo=false;
	/**
	 * Command Line Interface
	 */
	private CommandLineInterface cli;
	/**
	 * Modo de jogo
	 */
	private int modoJogo;
	/**
	 * Array List onde se guardam os dragoes 
	 */
	public ArrayList<Dragao> dragoes= new ArrayList<Dragao>();

	/**
	 * Especifica os movimentos das personagens, Direita, Esquerda, Cima ou Baixo
	 *
	 */
	public enum Movimento{
		DIREITA,ESQUERDA,CIMA,BAIXO
	}

	private Movimento movimentoDragao=Movimento.CIMA;

	/**
	 * Inicializa um jogo com um labirinto aleatorio, e coloca Personagens tambem aleatoriamente
	 */
	public Jogo(){
		cli=new CommandLineInterface();
		tab=new Tabuleiro(cli.retornaTamanhoTabuleiro());
		colocaHeroiAletorio();
		colocaEspadaAleatoria();
		colocaDragoesAleatorio(cli.retornaNumeroDragoes());
	}

	/**
	 * Utilizado para inicializar um jogo quando é o utilizador a criar o seu proprio labirinto
	 * @param heroi heroi a ser inicializado
	 * @param dragoes dragoes a serem inicializados
	 * @param espada espada a ser inicializada
	 * @param tab tabuleiro a ser inicializado
	 */
	public Jogo(Heroi heroi, ArrayList<Dragao> dragoes, Espada espada,Tabuleiro tab){
		this.heroi=heroi;
		this.dragoes=dragoes;
		this.espada=espada;
		this.tab=tab;
	}

	/**
	 * Inicializa jogo com tabuleiro aleatorio de tamanho dimensao e com numeroDragoes numero de Dragoes
	 * @param numeroDragoes numero de dragoes existentes no labirinto
	 * @param dimensao dimensao do labirinto
	 */
	public Jogo(int numeroDragoes, int dimensao){
		cli=new CommandLineInterface();
		tab=new Tabuleiro(dimensao);
		colocaHeroiAletorio();
		colocaEspadaAleatoria();
		colocaDragoesAleatorio(numeroDragoes);
	}

	/**
	 * Inicializa um jogo com um labirinto especifico (labirinto) e com coordenadas das personagens já definidas
	 * @param labirinto labirinto a ser inicializado
	 */
	public Jogo(char labirinto[][]){
		tab=new Tabuleiro(labirinto);
		//dragao=new Dragao(3,1,'D');
		heroi=new Heroi(1,1,'H');
		espada=new Espada(4,1,'E');
		cli=new CommandLineInterface();
		//tab.inserirChar(dragao.getP(), dragao.getSimbolo());
		tab.inserirChar(heroi.getP(), heroi.getSimbolo());
		tab.inserirChar(espada.getP(), espada.getSimbolo());
	}

	/**
	 * Inicializa um jogo com uma labirinto especifico (m), Atribuindo as personagens as coordenadas especificas
	 * @param m	labirinto a ser utilizado
	 * @param construtor serve para distinguir construtores
	 */
	public Jogo(char m[][], int construtor){   
		tab=new Tabuleiro(m);
		for(int i=0; i< m.length;i++){
			for(int j=0;j<m[i].length;j++){
				Point p=new Point(i,j);
				if(tab.retornaChar(p)=='H'){
					heroi=new Heroi(p.x,p.y,'H');
					heroi.setEstado(EstadoHeroi.DESARMADO);
				}
				if(tab.retornaChar(p)=='A'){
					heroi=new Heroi(p.x,p.y,'H');
					heroi.setEstado(EstadoHeroi.ARMADO);
				}
				if(tab.retornaChar(p)=='E' || tab.retornaChar(p)=='F' || tab.retornaChar(p)=='A'){
					espada=new Espada(p.x,p.y,'E');
					System.out.println("passei1");
				}


				if(tab.retornaChar(p)=='D'||tab.retornaChar(p)=='d'){
					dragoes.add(new Dragao(p.x,p.y,'D'));
					System.out.println("passei2");
				}
				if(tab.retornaChar(p)=='F'){
					Dragao d=new Dragao(p.x,p.y,'D');
					d.setPorCimaEspada(true);
					dragoes.add(d);
				}



			}
		}
		//		
		//		if(tab.retornaChar(heroi.getP())=='A')
		//			espada.setEncontrada(true);
	}

	/**
	 * Coloca Heroi Aleatoriamente no tabuleiro
	 */
	public void colocaHeroiAletorio(){
		Random rn=new Random();
		boolean valido=false;

		int x,y;
		Point p;

		do{
			x=rn.nextInt(tab.getN()); 
			y=rn.nextInt(tab.getN()); 
			p=new Point(x,y);
			double distancia=p.distance(tab.retornaSaida());
			if(tab.retornaChar(p)==' ' && distancia>tab.getN()/3){
				valido=true;
			}
		}while(!valido);

		heroi=new Heroi(p.x,p.y,'H');
		tab.inserirChar(heroi.getP(), heroi.getSimbolo());

	}

	/**
	 * Retorna um ponto aleatorio
	 * @return ponto aleatorio
	 */
	public Point retornaPontoAleatorio(){
		Random rn=new Random();
		boolean valido=false;

		int x,y;
		Point p;

		do{
			x=rn.nextInt(tab.getN()); //0 a tab.getN() exclusive
			y=rn.nextInt(tab.getN()); //0 a tab.getN() exclusive
			p=new Point(x,y);
			if(tab.retornaChar(p)==' ')
				valido=true;

		}while(!valido);

		return p;
	}

	/**
	 * Coloca Espada Aleatoriamente no Tabuleiro
	 */
	public void colocaEspadaAleatoria(){

		Point p=(Point)retornaPontoAleatorio().clone();

		espada=new Espada(p.x,p.y,'E');
		tab.inserirChar(espada.getP(), espada.getSimbolo());

	}

	/**
	 * Verifica se pode ou não colocar o Dragao no tabuleiro
	 * @param p ponto onde verifica se pode colocar o dragao
	 * @return boolean true se puder colocar dragao, false se nao puder colocar dragao
	 */
	public boolean possoColocarDragao(Point p){
		Point copia;
		int contador=0;

		copia=(Point)p.clone();
		copia.x+=1;
		if(tab.retornaChar(copia)!='H')
			contador++;

		copia=(Point)p.clone();
		copia.x-=1;
		if(tab.retornaChar(copia)!='H')
			contador++;

		copia=(Point)p.clone();
		copia.y+=1;
		if(tab.retornaChar(copia)!='H')
			contador++;

		copia=(Point)p.clone();
		copia.y-=1;
		if(tab.retornaChar(copia)!='H')
			contador++;

		if(contador==4)
			return true;

		return false;
	}

	/**
	 * Coloca dragoes aleatoriamente no tabuleiro
	 * @param numeroDragoes a serem colocados aleatoriamente no tabuleiro
	 */
	public void colocaDragoesAleatorio(int numeroDragoes){

		Point p;

		for(int i=0;i<numeroDragoes;i++){
			p=(Point)retornaPontoAleatorio().clone();

			if(possoColocarDragao(p)){
				Dragao dragao=new Dragao(p.x,p.y,'D');
				tab.inserirChar(dragao.getP(), dragao.getSimbolo());
				dragoes.add(dragao);
			}
			else i--;
		}

	}

	/**
	 * Verifica se existe alguma personagem no ponto dado como paramentro, verificando se pode ou nao colocar o dragao
	 * @param ponto ponto onde esta o dragao a ser verificado
	 * @return verdadeiro se puder, falso caso contrario
	 */
	public boolean podeColocarDragao(Point ponto){

		if (tab.retornaChar(ponto)==' ')
			return true;

		return false;

	}

	/**
	 * Verifica se pode ou nao mover o heroi para uma direcao especifica, direita, esquerda, cima ou baixo
	 * @param direcao direcao para onde se pretende mover o heroi
	 * @return verdadeiro se puder, falso caso contrario
	 */
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

		if(dragoesVivos()){
			if(tab.retornaChar(p)!='X' && tab.retornaChar(p)!='S' && tab.retornaChar(p)!= 'd'){
				valido=true;
			}
		}
		else if(tab.retornaChar(p)!='X')
			valido=true;

		return valido;

	}

	/**
	 * Verifica se ainda existem dragoes vivos no tabuleiro
	 * @return boolean true se ainda existirem dragoes no tabuleiro, false se já estiverem todos mortos
	 */
	public boolean dragoesVivos(){

		for (int i=0;i<dragoes.size();i++){
			if(dragoes.get(i).getEstado()!=EstadoDragao.MORTO)
				return true;
		}
		return false;
	}

	/**
	 * Move heroi para uma determinada direcao (Direita, Esquerda, Cima ou Baixo)
	 * @param direcao direcao para onde quero mover o heroi
	 */
	public void moverHeroi(Movimento direcao){
		//Movimento direcao=cli.lerDirecao();
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

	/**
	 * Verifica se o heroi se encontra na saida, caso esteja atribui true a flag fimDeJogo
	 */
	public void verificaSaida(){

		if(tab.retornaChar(heroi.getP())=='S')
			fimDeJogo=true;
	}

	/**
	 * Verifica se o Heroi apanha a espada, caso apanhe muda o estado do heroi para armado e muda o simbolo respetivo da personagem
	 */
	public void verificaEspada(){

		if(heroi.getP().x==espada.getP().x && heroi.getP().y==espada.getP().y){
			heroi.setSimbolo('A');
			heroi.setEstado(EstadoHeroi.ARMADO);
			tab.inserirChar(espada.getP(),heroi.getSimbolo());
		}
	}

	/**
	 * Verifica se existe algum dragao nas posiçoes adjacentes ao heroi, caso haja a flag de fimDeJogo fica a true
	 */
	public void verificaDragao(){

		for (int i=0;i<dragoes.size();i++){

			if(dragoes.get(i).getEstado()!= EstadoDragao.MORTO){
				boolean mesmaPos=false;

				if(dragoes.get(i).getEstado()==EstadoDragao.ACORDADO || heroi.getSimbolo()=='A'){

					if(heroi.getP().x-1==dragoes.get(i).getP().x && heroi.getP().y==dragoes.get(i).getP().y)
						mesmaPos=true;
					else if(heroi.getP().x+1==dragoes.get(i).getP().x && heroi.getP().y==dragoes.get(i).getP().y)
						mesmaPos=true;
					else if(heroi.getP().x==dragoes.get(i).getP().x && heroi.getP().y-1==dragoes.get(i).getP().y)
						mesmaPos=true;
					else if(heroi.getP().x==dragoes.get(i).getP().x && heroi.getP().y+1==dragoes.get(i).getP().y)
						mesmaPos=true;

					if (mesmaPos){
						if (heroi.getEstado()==EstadoHeroi.ARMADO){
							dragoes.get(i).setEstado(EstadoDragao.MORTO);
							tab.inserirChar(dragoes.get(i).getP(), ' ');
						}
						else if (dragoes.get(i).getEstado()==EstadoDragao.ACORDADO) {
							fimDeJogo=true;
						}
					}
				} 
			}
		}
	}

	/**
	 * Verifica se o Dragao cobre ou nao a espada, caso um dragao esteja em cima da espada, altera o simbolo desse dragao
	 * @param p ponto a verificar 
	 * @param indice indice do dragao a ser verificado no arraylist dragoes
	 */
	public void dragaoEEspada(Point p, int indice){

		if( dragoes.get(indice).isPorCimaEspada()){	
			dragoes.get(indice).setPorCimaEspada(false);
			tab.inserirChar(dragoes.get(indice).getP(),'E');
		}
		else{
			tab.inserirChar(dragoes.get(indice).getP(),' ');
		}

		dragoes.get(indice).setP(p); 

		if(tab.retornaChar(p) == 'E'){
			dragoes.get(indice).setPorCimaEspada(true);
			tab.inserirChar(p, 'F');
		}
		else {
			tab.inserirChar(p, dragoes.get(indice).getSimbolo());
		}
	}

	/**
	 * Altera posicao ou estado do heroi. Quando esta a dormir pode continuar a dormir ou acordar
	 * @param indice indice do dragao a ser verificado no arraylist dragoes
	 */
	public void dragaoADormir(int indice){
		Random rn=new Random();
		int direcao;

		direcao=rn.nextInt(2);

		switch (direcao){
		case 0: 
			break;
		case 1:
			dragoes.get(indice).setEstado(EstadoDragao.ACORDADO);
			dragoes.get(indice).setSimbolo('D'); 
			tab.inserirChar(dragoes.get(indice).getP(), 'D');
			break;
		}
	}

	/**
	 * Altera posicao ou estado do dragao. Dragao pode mover se para a esquerda, para a direita, para cima, para baixo ou simplesmnete adormecer
	 * @param indice indice do dragao a ser verificado no arraylist dragoes
	 */
	public void dragaoAcordado(int indice){

		Random rn=new Random();

		int direcao;
		boolean valido=false;
		Point p;

		direcao=rn.nextInt(6);
		p=(Point)dragoes.get(indice).getP().clone();

		switch (direcao){
		case 0: 
			p.y-=1;
			movimentoDragao=Movimento.ESQUERDA;
			break;
		case 1: 
			p.y+=1;
			movimentoDragao=Movimento.DIREITA;
			break;
		case 2:
			p.x+=1;
			movimentoDragao=Movimento.BAIXO;
			break;
		case 3:
			p.x-=1;   
			movimentoDragao=Movimento.CIMA;
			break;
		case 4:
			break;
		case 5:
			if (modoJogo==3){
				dragoes.get(indice).setEstado(EstadoDragao.DORMIR);
				dragoes.get(indice).setSimbolo('d');
				tab.inserirChar(p, 'd');
			}
			break;

		}
		if(DragaoPodeMover(p)){
			valido=true;
		}

		if(valido){
			dragaoEEspada(p,indice);
		}
	}


	/**
	 * Verifica se o dragao pode ou nao mover se para o ponto dado como parametro
	 * @param p ponto para onde se pretende mover o dragao
	 * @return boolean true se pode mover se, false se nao
	 */
	public boolean DragaoPodeMover(Point p){
		if(tab.retornaChar(p)!='X' && tab.retornaChar(p)!= 'S' && tab.retornaChar(p)!= 'D' && tab.retornaChar(p)!= 'd')
			return true;
		return false;
	}


	/**
	 * Responsavel por mover Dragao
	 */
	public void moverDragao(){

		for (int i=0;i<dragoes.size();i++){
			if (dragoes.get(i).getEstado()==EstadoDragao.ACORDADO){
				dragaoAcordado(i);
			}
			else if (dragoes.get(i).getEstado()==EstadoDragao.DORMIR ){
				dragaoADormir(i);
			}
		}

	}

	/**
	 * Responsavel por uma jogada, mover heroi, mover dragoes e verificar estado do jogo
	 * @param direcao direcao para onde se pretende mover o heroi
	 * @return retorna verdadeiro se for fim de jogo, falso caso contrario
	 */
	public boolean jogada(Movimento direcao){
		moverHeroi(direcao);
		verificaEspada();
		verificaDragao();
		if (modoJogo!=1){
			moverDragao();
		}
		verificaDragao();

		return fimDeJogo;
	}

	/**
	 * Retorna tabuleiro
	 * @return tabuleiro
	 */
	public Tabuleiro getTab() {
		return tab;
	}

	/**
	 * Retorna modo de jogo
	 * @return modo de jogo
	 */
	public int getModoJogo() {
		return modoJogo;
	}

	/**
	 * Altera modo de jogo 
	 * @param modoJogo
	 */
	public void setModoJogo(int modoJogo) {
		this.modoJogo = modoJogo;
	}

	/**
	 * Retorna Heroi
	 * @return Heroi
	 */
	public Heroi getHeroi() {
		return heroi;
	}

	/**
	 * Atualiza Heroi
	 * @param heroi
	 */
	public void setHeroi(Heroi heroi) {
		this.heroi = heroi;
	}

	/**
	 * Verifica se acabou ou nao o jogo
	 * @return boolean true se acabou o jogo
	 */
	public boolean isFimDeJogo() {
		return fimDeJogo;
	}

	/**
	 * Atualiza estado de fim de jogo, true se o jogo acabou, false se ainda nao acabou
	 * @param fimDeJogo flag que indica se o jogo acabou ou nao
	 */
	public void setFimDeJogo(boolean fimDeJogo) {
		this.fimDeJogo = fimDeJogo;
	}

	/**
	 * Retorna Movimento do Dragao
	 * @return retorna o movimento do dragao
	 */
	public Movimento getMovimentoDragao() {
		return movimentoDragao;
	}

}