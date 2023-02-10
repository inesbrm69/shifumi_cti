package common;

import java.io.Serializable;

import common.enums.tileType;

public class Map implements Serializable {
	//private tileType tile;
	private String mapPng;
	private String[][] mapArray;

	public String getMapPng() {
		return mapPng;
	}

//	public void setMapPng(String mapPng) {
//		this.mapPng = mapPng;
//	}

	public String[][] getMapArray() {
		return mapArray;
	}

//	public void setMapArray(String[][] mapArray) {
//		this.mapArray = mapArray;
//	}
	
	public void initMap() {
		//mapArray = /*(map comme sur jeu javascript)*/
	}
	
	
}
