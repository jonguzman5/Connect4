package Connect4;

public class SHOW_CONNECT4 {
	public static void main(String[]args){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){//interface
			@Override
			public void run() {
				CONNECT4_GUI CONNECT4_GUI = new CONNECT4_GUI();
			}
		});
	}
}
