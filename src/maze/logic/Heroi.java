package maze.logic;
/**
 * Representa o Heroi do jogo
 *
 */
public class Heroi extends Personagem{
	/**
	 * Estado do Heroi
	 *
	 */
	public enum EstadoHeroi{
		ARMADO,DESARMADO
	}
	
	public EstadoHeroi estado=EstadoHeroi.DESARMADO;
	
	/**
	 * Inicialização do heroi com a linha, coluna e símbolo respetivo
	 * @param linha
	 * @param coluna
	 * @param simbolo
	 */
	public Heroi(int linha,int coluna, char simbolo){
		super(linha,coluna,simbolo);
	}
	/**
	 * Retorna um booleano informando se o heroi está ou nao armado
	 * @return true se o heroi tiver apanhado a espada (Armado), false caso nao a tenha encontrado
	 */
	boolean estaArmado(){
		if(simbolo=='A')
			return true;
		else return false;
	}

	/**
	 * Retorna estado do Heroi (Armado ou Desarmado)
	 * @return EstadoHeroi
	 */
	public EstadoHeroi getEstado() {
		return estado;
	}

	/**
	 * Altera estado do Heroi para Armado ou Desarmado
	 * @param estado
	 */
	public void setEstado(EstadoHeroi estado) {
		this.estado = estado;
	}
}
