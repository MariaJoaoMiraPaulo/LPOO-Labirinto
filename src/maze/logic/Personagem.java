package maze.logic;

public class Personagem {
	public int linha;
	public int coluna;
	public char simbolo;
	
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
	
	public void moverEsquerda(){
		coluna-=1;
	}
	public void moverDireita(){
		coluna+=1;
	}
	public void moverBaixo(){
		linha+=1;
	}
	public void moverCima(){
		linha-=1;
	}
}
