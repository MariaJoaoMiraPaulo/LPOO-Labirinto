package maze.logic;

import java.awt.Point;
import java.util.Random;
import java.util.Stack;
/**
 * Tabuleiro.java - Representa o tabuleiro do jogo (labirinto)
 *
 */
public class Tabuleiro {
	//	private char labirinto[][]={
	//			{'X','X','X','X','X','X','X','X','X','X'},
	//			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
	//			{'X',' ','X','X',' ','X',' ','X',' ','X'},
	//			{'X',' ','X','X',' ','X',' ','X',' ','X'},
	//			{'X',' ','X','X',' ','X',' ','X',' ','X'},
	//			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
	//			{'X',' ','X','X',' ','X',' ','X',' ','X'},
	//			{'X',' ','X','X',' ','X',' ','X',' ','X'},
	//			{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
	//			{'X','X','X','X','X','X','X','X','X','X'},
	//	};
	/**
	 * Array que contem o labirinto
	 */
	private char labirinto[][];
	/**
	 * Array utilizado na geracao aleatoria do labirinto, serve para fazer backtracing
	 */
	private char celulasVisitadas[][];
	/**
	 * Comprimento do labirinto
	 */
	private int n;
	/**
	 * Comprimento do array utilizado na geracao aleatoria do labirinto
	 */
	private int nCVisitadas;

	/**
	 * Inicializa um tabuleiro com uma dada dimensão, construtor usado na quando se quer gerar um labirinto aleatório
	 * @param dimensao dimensao do labirinto a gerar
	 */
	public Tabuleiro(int dimensao){
		n=dimensao;
		nCVisitadas=(n-1)/2;
		celulasVisitadas=new char[nCVisitadas][nCVisitadas];
		labirinto=new char[n][n];
		gerarLabirinto();

	}

	/**
	 * Inicializa um tabuleiro copiando para o labirinto o conteudo do parametro m
	 * @param m labirinto a ser usado
	 */
	public Tabuleiro(char m[][]){
		labirinto=m;
	}

	/**
	 * Funcao utilizada para criar a saida quando se esta a gerar um labirinto
	 * @return retorna o ponto onda esta a saída
	 */
	public Point retornaSaida(){
		Random rn=new Random();
		int linha=0;
		int coluna=0;
		//int CVisitadas=(n-1)/2;

		int extremo= rn.nextInt(4);
		int lado=rn.nextInt(n-2)+1;
		while(lado%2 == 0)
			lado=rn.nextInt(n-2)+1;

		switch(extremo){
		case 0:
			linha=0;
			coluna=lado;
			celulasVisitadas[0][(lado-1)/2]='+';
			break;
		case 1:
			linha=n-1;
			coluna=lado;
			celulasVisitadas[nCVisitadas-1][(lado-1)/2]='+';
			break;
		case 2:
			coluna=0;
			linha=lado;
			celulasVisitadas[(lado-1)/2][0]='+';
			break;
		case 3:
			coluna=n-1;
			linha=lado;
			celulasVisitadas[(lado-1)/2][nCVisitadas-1]='+';
			break;
		default:
			break;
		}

		Point p=new Point(linha,coluna);
		return p;
	}

	/**
	 * Funcao utilizada quando se esta a gerar o labirinto, serve para ver se o ponto p, 
	 * é um ponto válido para se mover, ou seja, se esta dentro das dimensoes do labirinto
	 * e se é uma posicao pela qual o algoritmo ainda não passou la
	 * @param p ponto a verificar 
	 * @return verdadeiro se poder mover, falso caso contrario
	 */
	public boolean possoMover(Point p){
		if(p.x>=nCVisitadas || p.y>=nCVisitadas || p.x<0 || p.y<0)
			return false;
		if(celulasVisitadas[p.x][p.y]=='+')
			return false;
		return true;
	}

	/**
	 * Funcao que serve para converter as coordenadas do atributo labirinto para
	 * o atributo CVisitadas
	 * @param p ponto a ser convertido
	 * @return retorna o ponto a ser utilizado no atributo CVisitadas
	 */
	public Point coordenadasParaCVisitadas(Point p){
		Point p2= (Point)p.clone();

		p2.x=(p2.x-1)/2;
		p2.y=(p2.y-1)/2;

		return p2;
	}

	/**
	 * Funcao utilizada quando se esta a gerar o labirinto, serve para verificar se o 
	 * ponto p é um beco sem saída, ou seja, se os pontos a volta ja foram visitados pelo
	 * algoritmo ou nao
	 * @param p ponto a ser verificado
	 * @return se todos os pontos a volta ja foram visitados pelo algoritmo retorna true, falso caso contrario
	 */
	public boolean deadEnd(Point p){  
		//verifica no array pequeno  se o ponto ï¿½ um dead end
		Point p2=new Point();
		int contador=0;

		//eixo dos x
		p2=(Point)p.clone();
		p2.x+=1;
		if((p2.x<nCVisitadas && p2.y<nCVisitadas) && (p2.x>=0 && p2.y>=0)){
			if(celulasVisitadas[p2.x][p2.y]=='+')
				contador++;
		}
		else contador++;

		p2=(Point)p.clone();
		p2.x-=1;
		if((p2.x<nCVisitadas && p2.y<nCVisitadas) && (p2.x>=0 && p2.y>=0)){
			if(celulasVisitadas[p2.x][p2.y]=='+')
				contador++;
		}
		else contador++;

		//eixo dos y
		p2=(Point)p.clone();
		p2.y-=1;
		if((p2.x<nCVisitadas && p2.y<nCVisitadas) && (p2.x>=0 && p2.y>=0)){
			if(celulasVisitadas[p2.x][p2.y]=='+')
				contador++;
		}
		else contador++;

		p2=(Point)p.clone();
		p2.y+=1;
		if((p2.x<nCVisitadas && p2.y<nCVisitadas) && (p2.x>=0 && p2.y>=0)){
			if(celulasVisitadas[p2.x][p2.y]=='+')
				contador++;
		}
		else contador++;

		if(contador==4)
			return true;

		return false;
	}

	/**
	 * Funcao responsavel por gerar labirinto, 
	 * esta funcao chama grande parte das funcoes em cima
	 */
	public void gerarLabirinto(){

		Stack<Point> st = new Stack();
		Random rn=new Random();

		for (int i=0;i<n;i++){
			for (int j=0;j<n;j++)
				labirinto[i][j]='X';
		}

		for (int i=0;i<n;i++){
			for (int j=0;j<n;j++){
				if(i%2!=0 && j%2!=0)
					labirinto[i][j]=' ';
			}
		}

		for(int i=0;i<nCVisitadas;i++){
			for(int j=0;j<nCVisitadas;j++)
				celulasVisitadas[i][j]='.';
		}

		Point pNoLab=retornaSaida();

		labirinto[pNoLab.x][pNoLab.y]='S';
		//st.push(pNoLab);
		if(pNoLab.x==0){
			pNoLab.x+=1;
		}
		else if(pNoLab.x==n-1){
			pNoLab.x-=1;
		}
		else if(pNoLab.y==0){
			pNoLab.y+=1;
		}
		else if(pNoLab.y==n-1){
			pNoLab.y-=1;
		}
		Point pNoCV=coordenadasParaCVisitadas(pNoLab);

		st.push(pNoCV);

		Point copia=new Point();
		boolean valido=false;

		while(!st.empty()){
			//for(int i=0;i<3;i++){ 
			if(!deadEnd(pNoCV)){
				while(!valido){
					copia=(Point)pNoCV.clone();
					switch(rn.nextInt(4)){
					case 0:    //mover para baixo
						copia.x+=1;
						if(possoMover(copia)){
							pNoCV.x+=1;
							pNoLab.x+=1;
							celulasVisitadas[pNoCV.x][pNoCV.y]='+';
							labirinto[pNoLab.x][pNoLab.y]=' ';
							pNoLab.x+=1;
							st.push((Point)pNoCV.clone());
							valido=true;
						}
						break;
					case 1:    //mover para cima
						copia.x-=1;
						if(possoMover(copia)){
							pNoCV.x-=1;
							pNoLab.x-=1;
							celulasVisitadas[pNoCV.x][pNoCV.y]='+';
							labirinto[pNoLab.x][pNoLab.y]=' ';
							pNoLab.x-=1;
							st.push((Point)pNoCV.clone());
							valido=true;
						}
						break;
					case 2:    //mover para esquerda
						copia.y-=1;
						if(possoMover(copia)){
							pNoCV.y-=1;
							pNoLab.y-=1;
							celulasVisitadas[pNoCV.x][pNoCV.y]='+';
							labirinto[pNoLab.x][pNoLab.y]=' ';
							pNoLab.y-=1;
							st.push((Point)pNoCV.clone());
							valido=true;
						}
						break;
					case 3:    //mover para direita
						copia.y+=1;
						if(possoMover(copia)){
							pNoCV.y+=1;
							pNoLab.y+=1;
							celulasVisitadas[pNoCV.x][pNoCV.y]='+';
							labirinto[pNoLab.x][pNoLab.y]=' ';
							pNoLab.y+=1;
							st.push((Point)pNoCV.clone());
							valido=true;
						}
						break;
					}
				}
				valido=false;
			}
			else {
				pNoCV=(Point)st.peek().clone();
				pNoLab=(Point)pNoCV.clone();
				pNoLab.x=pNoLab.x*2+1;
				pNoLab.y=pNoLab.y*2+1;
				st.pop();
			}
		}

		//desenhaCVisitadas();
	}

	/**
	 * Funcao utilizada para desenhar o atributo CVisitadas na consola
	 */
	public void desenhaCVisitadas(){
		for(int i=0; i<nCVisitadas;i++){
			for(int j=0; j<nCVisitadas;j++)
				System.out.print(celulasVisitadas[i][j] + " ");
			System.out.println();
		}

	}

	/**
	 * Funcao utilizada para desenhar o atributo labirinto na consola
	 */
	public void desenhaTab(){
		for(int i=0;i<labirinto.length;i++){
			for(int j=0;j<labirinto[i].length;j++){
				System.out.print(labirinto[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Funcao utilizada para retornar um determinado carater numa determinada posicao
	 * @param linha linha do elemento
	 * @param coluna coluna do elemento
	 * @return retorna o carater na posicao labirinto[linha][coluna]
	 */
	public char retornaChar(int linha,int coluna){
		//if(linha<labirinto.length && coluna<labirinto[0].length)
		return labirinto[linha][coluna];
		//return 's';
	}

	/**
	 * Funcao utilizada para retornar um determinado carater numa determinada posicao
	 * @param p ponto onde se quer saber o carater
	 * @return retorna o carater na posicao labirinto[p.x][p.y]
	 */
	public char retornaChar(Point p){
		return labirinto[p.x][p.y];
	}

	/**
	 * Funcao utilizada para inserir o carater letra no labirinto na posicao p 
	 * @param p ponto para inserir o carater
	 * @param letra carater a ser inserido
	 */
	public void inserirChar(Point p, char letra){
		labirinto[p.x][p.y]=letra;
	}

	/**
	 * Transforma o atributo labirinto de um array bidimensional numa string
	 * @return retorna o labirinto numa string
	 */
	public String paraString(){
		String resultado="";

		for(int i=0; i< labirinto.length;i++){
			for(int j=0;j<labirinto[i].length;j++){
				//resultado+= " " +labirinto[i][j];
				resultado+=labirinto[i][j];
			}   
			resultado+= "\n";
		}

		return resultado;
	}

	/**
	 * Funcao que retorna a dimensao do labirinto
	 * @return retorna a dimensao do labirinto
	 */
	public int getN() {
		return n;
	}

	/**
	 * Funcao que retorna o labirinto
	 * @return retorna o labirinto
	 */
	public char[][] getLabirinto() {
		return labirinto;
	}

}
