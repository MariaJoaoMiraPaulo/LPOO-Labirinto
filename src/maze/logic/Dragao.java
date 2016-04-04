package maze.logic;
/**
 * 
 * Representa um Dragao no jogo
 *
 */
public class Dragao extends Personagem {
	/**
	 *Estado do Dragao
	 */
	public enum EstadoDragao{
		MORTO,DORMIR,ACORDADO
	}
	
	private EstadoDragao estado=EstadoDragao.ACORDADO;
	/**
	 * Indica se o Dragao esta ou nao por cima da espada
	 */
	private boolean porCimaEspada=false;
	/**
	 * Inicializar Dragao Acordado com linha, coluna e simbolo
	 * @param linha
	 * @param coluna
	 * @param simbolo
	 */
	public Dragao(int linha, int coluna,char simbolo){
		super(linha,coluna,simbolo);
		estado=EstadoDragao.ACORDADO;
	}

	/**
	 * Retorna EstadoDragao (MORTO, a DORMIR ou ACORDADO)
	 * @return Estado do Dragao
	 */
	public EstadoDragao getEstado() {
		return estado;
	}

	/**
	 * Altera estado Dragão para morto, dormir ou acordado
	 * @param estado
	 */
	public void setEstado(EstadoDragao estado) {
		this.estado = estado;
	}

	/**
	 * Verifica se o Dragão está por cima da espada
	 * @return true se Dragão está por cima da espada, false se não
	 */
	public boolean isPorCimaEspada() {
		return porCimaEspada;
	}
	
	/**
	 * Altera a flag porCimaEspada do Dragão
	 * @param porCimaEspada
	 */
	public void setPorCimaEspada(boolean porCimaEspada) {
		this.porCimaEspada = porCimaEspada;
	}

}
