package common.enums;

public enum tileType {
	VOID("v"),
	FLOOR("f"),
	WALL("w"),
	TABLE("t"),
	CHAIR("c"),
	PLAYER("p"),
	OTHERS("o");
	
	private String num;
	
	private tileType(String num) {
		this.num = num;
	}
	
	public String getNum() {
		return num;
	}
}
