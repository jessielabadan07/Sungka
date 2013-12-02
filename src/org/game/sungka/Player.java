package org.game.sungka;

public class Player {
	
	private String _name;
	private int _score;
	
	public Player(){
		// put some code here
	}
	
	public Player(String name) {
		this._name = name;
	}
	
	public void setName(String name) {
		this._name = name;
	}
	
	public String getName() {
		return this._name;
	}
	
	public void setScore(int score){
		this._score = score;
	}
	
	public int getScore() {
		return this._score;
	}
	
	public Boolean validateName(String name){
		if(name.matches("[a-z]|[A-Z]|[a-zA-Z]+")) {
			return true;
		}
		return false;
	}

}
