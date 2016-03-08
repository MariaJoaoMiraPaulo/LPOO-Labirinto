package maze.logic;

public class Dragao extends Personagem {
	
	public enum EstadoDragao{
		MORTO,DORMIR,ACORDADO
	}
	
	private EstadoDragao estado=EstadoDragao.ACORDADO;
	private boolean porCimaEspada=false;
	
	public Dragao(int linha, int coluna,char simbolo){
		super(linha,coluna,simbolo);
		estado=EstadoDragao.ACORDADO;
	}

	public EstadoDragao getEstado() {
		return estado;
	}

	public void setEstado(EstadoDragao estado) {
		this.estado = estado;
	}

	public boolean isPorCimaEspada() {
		return porCimaEspada;
	}

	public void setPorCimaEspada(boolean porCimaEspada) {
		this.porCimaEspada = porCimaEspada;
	}

}
