package maze.logic;

public class Espada extends Personagem {
	
	public boolean encontrada=false;
	
	/**
	 * Inicializa espada com linha, coluna e símbolo
	 * @param linha
	 * @param coluna
	 * @param simbolo
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
	 * @param encontrada
	 */
	public void setEncontrada(boolean encontrada) {
		this.encontrada = encontrada;
	}
	
}
