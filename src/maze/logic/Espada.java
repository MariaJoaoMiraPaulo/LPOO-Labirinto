package maze.logic;

public class Espada extends Personagem {
	
	public boolean encontrada=false;
	//public boolean coberta=false;
	
	public Espada(int linha,int coluna, char simbolo){
		super(linha,coluna,simbolo);
	}

	public boolean isEncontrada() {
		return encontrada;
	}

	public void setEncontrada(boolean encontrada) {
		this.encontrada = encontrada;
	}

//	public boolean isCoberta() {
//		return coberta;
//	}
//
//	public void setCoberta(boolean coberta) {
//		this.coberta = coberta;
//	}
	
	
}
