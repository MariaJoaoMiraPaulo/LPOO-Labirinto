package maze.logic;

public class Dragao extends Personagem {
	
	public enum EstadoDragao{
		MORTO,DORMIR,ACORDADO
	}
	
	public EstadoDragao estado=EstadoDragao.ACORDADO;
	
	public Dragao(int linha, int coluna,char simbolo){
		super(linha,coluna,simbolo);
	}

	public EstadoDragao getEstado() {
		return estado;
	}

	public void setEstado(EstadoDragao estado) {
		this.estado = estado;
	}

}
