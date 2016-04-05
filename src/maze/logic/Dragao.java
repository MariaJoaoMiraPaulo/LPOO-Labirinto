package maze.logic;
/**
 * 
 * Dragao.java - Representa um Dragao no jogo
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
	 * @param linha linha do dragao
	 * @param coluna coluna do dragao
	 * @param simbolo do dragao
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
	 * Altera estado Drag√£o para morto, dormir ou acordado
	 * @param estado estado atual do dragao
	 */
	public void setEstado(EstadoDragao estado) {
		this.estado = estado;
	}

	/**
	 * Verifica se o Dragao esta° por cima da espada
	 * @return true se Dragaoo esta° por cima da espada, false se nao
	 */
	public boolean isPorCimaEspada() {
		return porCimaEspada;
	}
	
	/**
	 * Altera a flag porCimaEspada do Dragao
	 * @param porCimaEspada flag que indica se o dragao esta ou nao por cima da espada
	 */
	public void setPorCimaEspada(boolean porCimaEspada) {
		this.porCimaEspada = porCimaEspada;
	}

}
