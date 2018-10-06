package com.brandon.web;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Strings {
	
	//Find all permutations of x in y
	public static ArrayList<StringBuilder> permuationsOfXinY(String x, String y) {
		ArrayList<StringBuilder> permuteList = new ArrayList<StringBuilder>();
		HashMap<Character, Integer> hashTable = new HashMap<Character, Integer>();
		HashMap<Character, Integer> copyTable = new HashMap<Character, Integer>();
		int min = x.length();
		if(x.length() > y.length()) {
			return permuteList;
		}
		
		char[] characters = x.toCharArray();
		char[] characters2 = y.toCharArray();
			
		//iterate over charArray mapping its characters in hashmap<Character,int>
		for(int i = 0; i < characters.length; i++) {
			char c = characters[i];
			if(hashTable.containsKey(c)) {
				hashTable.put(c, hashTable.get(c)+1);
			} else {
				hashTable.put(c, 1);
			}	
		}
		
		//copy hashTable
		for(Character key : hashTable.keySet()) {
			copyTable.put(key, hashTable.get(key));
		}
		
		for(int j = 0; j < characters2.length-min+1; j++) {
			StringBuilder permutation = new StringBuilder();
			//match characters
			for(int a = j; a < j+min; a++) {
				char c1 = characters2[a];
				if (!hashTable.containsKey(c1)) {
					break;
				}
				hashTable.put(c1, hashTable.get(c1)-1);
				permutation.append(c1);
			}
			//check hashTable to see if permutation existed, then reset it
			boolean all0s = true;
			for (Integer value : hashTable.values()) {
				if(value != 0) {
					all0s = false;
				}
			}
			//Add permutation to list i
			if(all0s) {
				permuteList.add(permutation);
			}
			
			//reset hashTable
			for(Character key : copyTable.keySet()) {
				hashTable.put(key, copyTable.get(key));
			}
		}
		
		return permuteList;
		
	}
}
