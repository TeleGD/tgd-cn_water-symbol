package waterSymbol.board;

import java.util.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import waterSymbol.Character;
import waterSymbol.board.cases.*;

public class Board {
	private Case[][] cases;
	private int nbCol, nbLig;
	private float ratio;

	public Board(Case[][] cases, int nbLig, int nbCol, int width, int height) {
		this.ratio = (width/1920f)<(height/1080f)?(width/1920f):(height/1080f);

		this.nbLig = nbLig;
		this.nbCol = nbCol;
		this.cases = cases;
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		for(int i=0; i<nbLig; i++) {
			for(int j=0; j<nbCol; j++) {
				cases[i][j].update(container, game, delta);
			}
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		for(int i=0; i<nbLig; i++) {
			for(int j=0; j<nbCol; j++) {
				cases[i][j].render(container, game, context, ratio);
			}
		}
	}

	public void moveCharacter (Character character, int di, int dj) {
		int [] oldPos = character.getPos ();
		int [] newPos = new int [] {oldPos [0] + di, oldPos [1] + dj};
		if (0 < newPos [0] && newPos [0] < this.nbLig && 0 < newPos [1] && newPos [1] < this.nbCol && this.cases [newPos [0]] [newPos [1]].getCharacter () == null) {
			character.setPos (newPos);
			this.cases [oldPos [0]] [oldPos [1]].setCharacter (null);
			this.cases [newPos [0]] [newPos [1]].setCharacter (character);
		}
	}
}
