package maze.logic;

public class Dragao extends Personagem {
	
	public boolean morto=false;
	
	public Dragao(int linha, int coluna,char simbolo){
		super(linha,coluna,simbolo);
	}

	public boolean isMorto() {
		return morto;
	}

	public void setMorto(boolean morto) {
		this.morto = morto;
	}
}
