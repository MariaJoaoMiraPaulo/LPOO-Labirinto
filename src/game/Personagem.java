package game;

public class Personagem {
	private int linha;
	private int coluna;
	private char simbolo;
	
	Personagem(int linha, int coluna,char simbolo){
		this.linha=linha;
		this.coluna=coluna;
		this.simbolo=simbolo;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}
}
