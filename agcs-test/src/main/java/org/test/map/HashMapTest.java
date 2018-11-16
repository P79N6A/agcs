package org.test.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
	
	public static void main(String[] args) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		HashMap<String, String> cloneMap = new HashMap<String, String>(map);
		for(Map.Entry<String, String> entry : cloneMap.entrySet()){
			System.out.println(entry.getKey());
			if("key2".equals(entry.getKey())){
				map.remove(entry.getKey());
			}
		}
		for(Map.Entry<String, String> entry : map.entrySet()){
			System.out.println(entry.getKey());
		}
		constor();
		int n = -25;
		System.out.println(Math.abs(++n));
	}
	
	public static void constor(){
		{
			System.out.println("constor");
		}
	}

}
