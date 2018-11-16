package org.test.switchs;

public class SwitchTest {
	
	public static void main(String[] args) {
		String Test = "B";
		switch(Test){
		case "A":
			System.out.println("A");
			break;
		case "B":
			System.out.println("B");
			//break;
		default:
			System.out.println("default");
			break;
		}
		System.out.println("结束");
	}

}
