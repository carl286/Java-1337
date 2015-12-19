package com;

import java.util.Hashtable;
import java.util.Map;

//The HashTable class implements a hash table and maps keys to values. However, neither the key nor the value can be null. This class contains two fundamental parameters: initial capacity and performance, with the same definitions as the HashMap class.
public class HashTableExample {

	public static void main(String[] args) {
Map vehicles = new Hashtable();
		
		// Add some vehicles.
		vehicles.put("BMW", 5);
		vehicles.put("Mercedes", 3);
		vehicles.put("Audi", 4);
		vehicles.put("Ford", 10);
		
		System.out.println("Total vehicles: " + vehicles.size());
		
		// Iterate over all vehicles, using the keySet method.
		for(Object key: vehicles.keySet())
			System.out.println(key + " - " + vehicles.get(key));
		System.out.println();
		
		String searchKey = "Audi";
		if(vehicles.containsKey(searchKey))
			System.out.println("Found total " + vehicles.get(searchKey) + " "
					+ searchKey + " cars!\n");
		
		// Clear all values.
		vehicles.clear();
		
		// Equals to zero.
		System.out.println("After clear operation, size: " + vehicles.size());

		// The next statements throw a NullPointerException, if uncommented.
//		vehicles.put("Nissan", null);
//		vehicles.put(null, 6);

	}

}
