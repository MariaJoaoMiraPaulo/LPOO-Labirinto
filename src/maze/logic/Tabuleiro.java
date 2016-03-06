package maze.logic;

import java.awt.Point;
import java.util.Random;
import java.util.Stack;

public class Tabuleiro {
	private char labirinto[][]={
			{'X','X','X','X','X','X','X','X','X','X'},
			{'X',' ',' ',' ',' ',' ',' ',' ',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ',' ',' ',' ',' ',' ','X',' ','S'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ','X',' ','X',' ','X'},
			{'X',' ','X','X',' ',' ',' ',' ',' ','X'},
			{'X','X','X','X','X','X','X','X','X','X'},
	};
	
	private char celulasVisitadas[][];

	public Tabuleiro(){
		
	}
	
	public void gerarSaida(int n){
		Random rn=new Random();
		int linha=0;
		int coluna=0;
		int CVisitadas=(n-1)/2;
		
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
			celulasVisitadas[CVisitadas-1][(lado-1)/2]='+';
			break;
		case 2:
			coluna=0;
			linha=lado;
			celulasVisitadas[(lado-1)/2][0]='+';
			break;
		case 3:
			coluna=n-1;
			linha=lado;
			celulasVisitadas[(lado-1)/2][CVisitadas-1]='+';
			break;
		default:
			break;
		}
		labirinto[linha][coluna]='S';
	}
	
	public void gerarLabirinto(int n){

		labirinto=new char[n][n];
		Stack st = new Stack();
		int CVisitadas=(n-1)/2;
		 celulasVisitadas=new char[CVisitadas][CVisitadas];

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

		for(int i=0;i<CVisitadas;i++){
			for(int j=0;j<CVisitadas;j++)
				celulasVisitadas[i][j]='.';
		}

		gerarSaida(n);
		
		for(int i=0; i<CVisitadas;i++){
			for(int j=0; j<CVisitadas;j++)
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
				resultado+= " " +labirinto[i][j];
			}
			resultado+= "\n";
		}

		return resultado;
	}
}
