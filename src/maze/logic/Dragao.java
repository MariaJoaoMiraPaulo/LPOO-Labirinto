package maze.logic;

public class Dragao extends Personagem {
	
	public boolean morto=false;
	public boolean dormir=false;
	
	public Dragao(int linha, int coluna,char simbolo){
		super(linha,coluna,simbolo);
	}

	public boolean isMorto() {
		return morto;
	}

	public void setMorto(boolean morto) {
		this.morto = morto;
	}

	public boolean isDormir() {
		return dormir;
	}

	public void setDormir(boolean dormir) {
		this.dormir = dormir;
	}
}
