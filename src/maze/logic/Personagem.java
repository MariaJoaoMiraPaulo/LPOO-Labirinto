package maze.logic;

import java.awt.Point;

public class Personagem {
	//	public int linha;
	//	public int coluna;
	public Point p;
	public char simbolo;


	Personagem(int linha, int coluna,char simbolo){
		p= new Point(linha,coluna);
		this.simbolo=simbolo;
	}

	public Point getP() {
		return p;
	}

	public void setP(Point p) {
		this.p = p;
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

	public void moverEsquerda(){
		p.y-=1;
	}
	public void moverDireita(){
		p.y+=1;
	}
	public void moverBaixo(){
		p.x+=1;
	}
	public void moverCima(){
		p.x-=1;
	}

}
