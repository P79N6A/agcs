package junit;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.agcs.system.codegenerate.window.CodeWindow;


public class test {
	public static void main(String[] args) {
		System.out.println((Math.round((546282.47)*100))/100);
		System.out.println((float)(Math.round((546282.4622)*100))/100);
		
        System.out.println(Float.parseFloat(String.format("%.2f", 25.254)));
        
        System.out.println(562656-26.3);
        
        CodeWindow codeWindow = new CodeWindow();
		codeWindow.pack();
	}

}
