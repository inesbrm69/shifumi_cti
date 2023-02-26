package common;

import java.io.Serializable;

import common.enums.tileType;

public class Map implements Serializable {
	//private tileType tile;
	//private String mapPng;
	private String[][] mapArray = new String[17][9];

	//public String getMapPng() {
		//return mapPng;
	//}

//	public void setMapPng(String mapPng) {
//		this.mapPng = mapPng;
//	}

	public String[][] getMapArray() {
		return mapArray;
	}

	public void setMapArray(String[][] mapArray) {
		this.mapArray = mapArray;
	}

	public void setMapTile(int x, int y, String type){
		this.mapArray[y][x] = type;
	}

	public String getMapTile(int x, int y){
		return this.mapArray[y][x];
	}
	
	public void initMap() {
		String[][] tab = {
							{"v","v","v","v","v","v","v","v","v","v","v","v","v","v","v","v","v"},
							{"v","f","f","f","f","f","v","v","v","v","v","f","f","f","f","f","v"},
							{"v","f","f","f","f","f","v","v","v","v","v","f","f","f","f","f","v"},
							{"v","f","c","t","c","f","f","f","f","f","f","f","c","t","c","f","v"},
							{"v","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","v"},
							{"v","f","c","t","c","f","f","f","f","f","f","f","c","t","c","f","v"},
							{"v","f","f","f","f","f","f","f","f","f","f","f","f","f","f","f","v"},
							{"v","v","f","f","f","f","f","f","f","f","f","f","f","f","f","v","v"},
							{"v","v","v","v","v","v","v","v","v","v","v","v","v","v","v","v","v"}
		};

		setMapArray(tab);
	}
	
	
}
