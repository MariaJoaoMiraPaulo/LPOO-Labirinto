package maze.logic;

public class Heroi extends Personagem{
	
	public enum EstadoHeroi{
		ARMADO,DESARMADO
	}
	
	public EstadoHeroi estado=EstadoHeroi.DESARMADO;
	
	public Heroi(int linha,int coluna, char simbolo){
		super(linha,coluna,simbolo);
	}
	
	boolean estaArmado(){
		if(simbolo=='A')
			return true;
		else return false;
	}

	public EstadoHeroi getEstado() {
		return estado;
	}

	public void setEstado(EstadoHeroi estado) {
		this.estado = estado;
	}
}
