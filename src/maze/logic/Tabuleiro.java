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

	public Tabuleiro(){

	}
	
	public void gerarLabirinto(int n){
		
		labirinto=new char[n][n];
		Stack st = new Stack();
		int CVisitadas=(n-1)/2;
		char celulasVisitadas[][]=new char[CVisitadas][CVisitadas];
		
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
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++)
				celulasVisitadas[i][j]='.';
		}
		
		Random rn=new Random();
		int linha=rn.nextInt(n-2)+1;
		int coluna=rn.nextInt(n-2)+1;
		
		
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
