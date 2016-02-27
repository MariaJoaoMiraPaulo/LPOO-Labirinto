package game;

public class Heroi extends Personagem{
	
	Heroi(int linha,int coluna, char simbolo){
		super(linha,coluna,simbolo);
	}
	
	boolean estaArmado(){
		if(simbolo=='A')
			return true;
		else return false;
	}
}
