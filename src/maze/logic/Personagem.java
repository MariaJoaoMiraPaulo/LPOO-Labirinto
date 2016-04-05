package maze.logic;

import java.awt.Point;
/**
 * Personagem.java - Representa todas as personagens do jogo, classe mae das classes,
 * dragao, heroi e espada
 */
public class Personagem {
	//	public int linha;
	//	public int coluna;
	public Point p;
	public char simbolo;


	/**
	 * Inicializa Personagem pela linha, coluna e simbolo respetivo
	 * @param linha da personagem
	 * @param coluna da personagem
	 * @param simbolo da personagem
	 */
	Personagem(int linha, int coluna,char simbolo){
		p= new Point(linha,coluna);
		this.simbolo=simbolo;
	}

	/**
	 * Retorna o Ponto onde se encontra cada personagem
	 * @return ponto onde se encontra cada personagem
	 */
	public Point getP() {
		return p;
	}

	/**
	 * Altera ponto onde se encontra cada personagem
	 * @param novo ponto onde se encontra da personagem
	 */
	public void setP(Point p) {
		this.p = p;
	}

	/**
	 * Retorna simbolo relativo a uma personagem
	 * @return char que simboliza a personagem no tabuleiro
	 */
	public char getSimbolo() {
		return simbolo;
	}

	/**
	 * Altera simbolo relativo a uma personagem
	 * @param simbolo novo para uma personagem
	 */
	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

	/**
	 * Move Personagem para a esquerda, subtraindo um à componente y do ponto onde se encontra
	 */
	public void moverEsquerda(){
		p.y-=1;
	}
	/**
	 * Move Personagem para a direita, somando um à componente y do ponto onde se encontra
	 */
	public void moverDireita(){
		p.y+=1;
	}
	/**
	 * Move Personagem para baixo, somando um à componente x do ponto onde se encontra
	 */
	public void moverBaixo(){
		p.x+=1;
	}
	/**
	 * Move Personagem para cima, subtraindo um à componente x do ponto onde se encontra
	 */
	public void moverCima(){
		p.x-=1;
	}

}
