package common;

import java.io.Serializable;

import common.enums.tileType;

public class Map implements Serializable {
	private String[][] mapArray = new String[17][9];

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

	/**
	 * initialise une carte en créant un tableau de chaînes de caractères représentant les différents types de cases de la carte.
	 * Chaque case est représentée par une chaîne de caractères qui indique son type
	 */
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
