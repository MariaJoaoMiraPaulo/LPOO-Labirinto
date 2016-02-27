package game;

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
	
	public void desenhaTab(){
		for(int i=0;i<labirinto.length;i++){
			for(int j=0;j<labirinto[i].length;j++){
				System.out.print(labirinto[i][j]);
			}
			System.out.println();
		}
	}
	
	public void inserirChar(int linha, int coluna,char letra){
		labirinto[linha][coluna]=letra;
	}
	
	public char retornaChar(int linha,int coluna){
		return labirinto[linha][coluna];
	}
}
