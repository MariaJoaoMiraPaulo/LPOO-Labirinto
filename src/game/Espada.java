package game;

public class Espada extends Personagem {
	
	public boolean encontrada=false;
	
	Espada(int linha,int coluna, char simbolo){
		super(linha,coluna,simbolo);
	}

	public boolean isEncontrada() {
		return encontrada;
	}

	public void setEncontrada(boolean encontrada) {
		this.encontrada = encontrada;
	}
	
	
}
