package waterSymbol;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import app.AppLoader;

public class TeamBuilder {

	private Character characters[];
	private static Image image = AppLoader.loadPicture("/images/teamBuilder.png");
	
	private float charSize;
	private float ratioChar;
	private float xmin;
	private float xmax;
	private float ymin;
	private float ymax;
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	private Player activePlayer;
	
	private Player player1;
	private Player player2;
	
	public TeamBuilder(int width, int height, Player p1, Player p2) {
		
		player1 = p1;
		player2 = p2;
		activePlayer = p1;
		
		adpatSize(width, height);
		
		characters = new Character[4];
		
		resetCharacters();
	}
	
	private void adpatSize(int width, int height) {
		int a = (width * image.getHeight());
		int b = (image.getWidth() * height);
		int heightS = height;
		int widthS = width;
		if (a > b) {
			heightS = (image.getHeight() * width / image.getWidth());
		} else if (b > a) {
			widthS = (image.getWidth() * height / image.getHeight());
		}
		xmin = (width - widthS) / 2;
		ymin = (height - heightS) / 2;
		xmax = xmin + widthS;
		ymax = ymin + heightS;
		
		ratioChar = (width/1920f)>=(height/1080f)?(width/1920f):(height/1080f);
		charSize = 200*ratioChar;
		
		x1 = (int) (680*ratioChar+xmin);
		x2 = (int) (1050*ratioChar+xmin);
		y1 = (int) (160*ratioChar+ymin);
		y2 = (int) (520*ratioChar+ymin);
	}

	public void update(GameContainer container, StateBasedGame game, int delta) {
		onClickAction(container);	
	}

	public void render(GameContainer container, StateBasedGame game, Graphics context) {
		context.setColor(Color.black);
		context.drawImage(image, xmin, ymin, xmax, ymax, 0, 0, image.getWidth(), image.getHeight());
		context.drawRect(x1, y1, charSize, charSize);
		context.drawRect(x2, y1, charSize, charSize);
		context.drawRect(x1, y2, charSize, charSize);
		context.drawRect(x2, y2, charSize, charSize);
	}
	
	private void onClickAction(GameContainer container) {
		Input input = container.getInput();
		if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
			int choice = getChoice(input.getAbsoluteMouseX(),input.getAbsoluteMouseY());
			System.out.println(choice);
			if (choice != 0) {
				activePlayer.giveChar(characters[choice-1]);
				activePlayer = activePlayer==player1 ? player2 : player1;
				resetCharacters();
			}
		}
	}

	private void resetCharacters() {
		// TODO
		characters[0] = new Character("Amos", "a");
		characters[1] = new Character("Xavier", "a");
		characters[2] = new Character("David", "a");
		characters[3] = new Character("Frédéric", "a");
	}

	private int getChoice(int x, int y) {
		if (x>=x1 && x<=x1+charSize && y>=y1 && y<=y1+charSize) {
			return 1;
		} else if (x>=x2 && x<=x2+charSize && y>=y1 && y<=y1+charSize) {
			return 2;
		} else if (x>=x1 && x<=x1+charSize && y>=y2 && y<=y2+charSize) {
			return 3;
		} else if (x>=x2 && x<=x2+charSize && y>=y2 && y<=y2+charSize) {
			return 4;
		} else {
			return 0;
		}
	}
}