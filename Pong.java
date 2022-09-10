package pongGame;


public class Pong {

	static GameFrame frame;
	public static void main(String[] args) {
		
		 frame = new GameFrame();
		
	}

	public static GameFrame getFrame(){
		return frame;
	}
}