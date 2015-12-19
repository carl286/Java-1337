package com;

import java.util.HashMap;
import java.util.Map;

//Basic Methods
//A map has the form Map <K, V> where:
//K: specifies the type of keys maintained in this map.
//V: defines the type of mapped values.
//Furthermore, the Map interface provides a set of methods that must be implemented. In this section, we will discuss about the most famous methods:
//clear: Removes all the elements from the map.
//containsKey: Returns true if the map contains the requested key.
//containsValue: Returns true if the map contains the requested value.
//equals: Compares an Object with the map for equality.
//get: Retrieve the value of the requested key.
//keySet: Returns a Set that contains all keys of the map.
//put: Adds the requested key-value pair in the map.
//remove: Removes the requested key and its value from the map, if the key exists.
//size: Returns the number of key-value pairs currently in the map.

//The most common class that implements the Map interface is the Java HashMap. A HashMap is a hash table based implementation of the Map interface. It permits null keys and values. Also, this class does not maintain any order among its elements and especially, it does not guarantee that the order will remain constant over time. Finally, a HashMap contains two fundamental parameters: initial capacity and performance. The capacity is defined as the number of buckets in the hash table, while the load factor is a measure that indicates the maximum value the hash table can reach, before being automatically increased.
public class HashMapExample {

	public static void main(String[] args) {
		Map vehicles = new HashMap();
		
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

	}

}
