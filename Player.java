package Connect4;

public class Player implements Comparable<Player>{
	private String playerChip;
	private String playerName;
	private int numGames;
	private int numWins;
	private int numLosses;
	
	public Player(){
		playerChip = " * ";
		playerName = "nem";
		numGames = 0;
		numWins = 0;
		numLosses = 0;
	}
	public Player(String name, String symbol){
		playerName = name;
		playerChip = symbol;		
	}
	public void addNumWins(){
		numWins++;
		numGames++;
	}
	public void addNumLosses(){
		numLosses++;
		numGames++;
	}
	public void addDraw(){
		numGames++;
	}
	public int getNumWins(){
		return numWins;
	}
	public int getNumLosses(){
		return numLosses;
	}
	public int getNumGames(){
		return numGames;
	}
	public String getChip(){
		return playerChip;
	}
	public String getName(){
		return playerName;
	}
	public boolean equals(Object o){
		if(o instanceof Player){
			Player otherPlayer = (Player)o;
			if(this.playerName.equalsIgnoreCase(otherPlayer.playerName)){
				if(this.playerChip.equals(otherPlayer.playerChip)){
					if(this.numGames == otherPlayer.numGames){
						if(this.numLosses == otherPlayer.numLosses){
							if(this.numWins == otherPlayer.numWins){
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	@Override
	public String toString(){
		String s = "Player [ name: " + playerName + ", chip:" + playerChip + ", numGames:" + numGames + ", numLosses:" + numLosses + ", numWins:" + numWins + "]";
		return s;
	}
	
	@Override
	public int compareTo(Player otherP) {
		if(this.numWins > otherP.numWins){
			return 1;
		}
		else if(this.numWins < otherP.numWins){
			return -1;
		}
		else {
			return 0;
		}
	}
}

