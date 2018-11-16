package org.test.base;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class RuntimeMXBeanTest {
	
	public static void main(String[] args) {
		int num = getPid();
		System.out.println(num);
		String ss = "sdf";
		//assert ss == null;
		System.out.println("assert true");
	}
	
	public static int getPid() {
        RuntimeMXBean runtime = ManagementFactory.getRuntimeMXBean();
        String name = runtime.getName(); // format: "pid@hostname"
        try {
            return Integer.parseInt(name.substring(0, name.indexOf('@')));
        }
        catch (Exception e) {
            return -1;
        }
    }

}
