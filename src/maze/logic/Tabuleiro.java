package maze.logic;

import java.awt.Point;
import java.util.Random;
import java.util.Stack;
/**
 * Representa o tabuleiro do jogo (labirinto)
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
	private char labirinto[][];

	private char celulasVisitadas[][];
	private int n;
	private int nCVisitadas;

	public Tabuleiro(int dimensao){
		n=dimensao;
		nCVisitadas=(n-1)/2;
		celulasVisitadas=new char[nCVisitadas][nCVisitadas];
		labirinto=new char[n][n];
		gerarLabirinto();

	}

	public Tabuleiro(char m[][]){
		labirinto=m;
	}

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

	public boolean possoMover(Point p){
		if(p.x>=nCVisitadas || p.y>=nCVisitadas || p.x<0 || p.y<0)
			return false;
		if(celulasVisitadas[p.x][p.y]=='+')
			return false;
		return true;
	}

	public Point coordenadasParaCVisitadas(Point p){
		Point p2= (Point)p.clone();

		p2.x=(p2.x-1)/2;
		p2.y=(p2.y-1)/2;

		return p2;
	}

	public boolean deadEnd(Point p){  
		//verifica no array pequeno  se o ponto � um dead end
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

	public void desenhaCVisitadas(){
		for(int i=0; i<nCVisitadas;i++){
			for(int j=0; j<nCVisitadas;j++)
				System.out.print(celulasVisitadas[i][j] + " ");
			System.out.println();
		}

	}

	public void desenhaTab(){
		for(int i=0;i<labirinto.length;i++){
			for(int j=0;j<labirinto[i].length;j++){
				System.out.print(labirinto[i][j]);
			}
			System.out.println();
		}
	}

	public char retornaChar(int linha,int coluna){
		//if(linha<labirinto.length && coluna<labirinto[0].length)
		return labirinto[linha][coluna];
		//return 's';
	}

	public char retornaChar(Point p){
		return labirinto[p.x][p.y];
	}

	//	public void inserirChar(int linha,int coluna, char letra){
	//		labirinto[linha][coluna]=letra;
	//	}

	public void inserirChar(Point p, char letra){
		labirinto[p.x][p.y]=letra;
	}


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

	public int getN() {
		return n;
	}

	public char[][] getLabirinto() {
		return labirinto;
	}
	
}
