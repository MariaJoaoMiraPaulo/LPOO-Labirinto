package maze.logic;
/**
 * Espada.java - Representa a espada no jogo
 *
 */
public class Espada extends Personagem {
	
	/**
	 * Flag que indica se a espada foi ou nao encontrada pelo heroi
	 */
	public boolean encontrada=false;
	
	/**
	 * Inicializa espada com linha, coluna e símbolo
	 * @param linha linha da espada
	 * @param coluna coluna da espada
 	 * @param simbolo simbolo da espada
	 */
	public Espada(int linha,int coluna, char simbolo){
		super(linha,coluna,simbolo);
	}

	/**
	 * Verifica se a espada foi ou nao encontrada pelo Heroi
	 * @return true se Espada tiver sido encontrada pelo heroi
	 */
	public boolean isEncontrada() {
		return encontrada;
	}

	/**
	 * Altera flag Encontrada para true ou false
	 * @param encontrada flag que indica se o heroi encontrou ou nao a espada
	 */
	public void setEncontrada(boolean encontrada) {
		this.encontrada = encontrada;
	}
	
}
