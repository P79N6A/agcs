package org.agcs.system.codegenerate.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import org.agcs.system.codegenerate.database.DatabaseTools;
import org.agcs.system.codegenerate.pojo.CreateFileFlag;
import org.agcs.system.core.util.StringUtil;
import org.agcs.system.codegenerate.generate.CodeGenerate;

public class CodeWindow extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6317097985650209359L;
	private static int fieldRowNum = 1;
	private static String entityPackage = "test";
	private static String entityName = "TestEntity";
	private static String tableName = "t00_test";
	private static String sequneceCode = "";
	private static String funDescription = "\u6d4b\u8bd5";
	private static String primaryKeyPolicy = "uuid";
	String planets[] = {"uuid", "identity", "sequence"};
	
	public CodeWindow(){
		JPanel jp = new JPanel();
		setContentPane(jp);
		jp.setLayout(new GridLayout(14, 2));
		JLabel infolbl = new JLabel("\u63D0\u793A:");
		final JLabel showlbl = new JLabel();
		
		JLabel packagebl = new JLabel("\u5305\u540D\uFF08\u5C0F\u5199\uFF09\uFF1A");
		final JTextField packField = new JTextField();
		
		JLabel entitylbl = new JLabel("\u5B9E\u4F53\u7C7B\u540D\uFF08\u9996\u5B57\u6BCD\u5927\u5199\uFF09\uFF1A");
		final JTextField entityField = new JTextField();
		
		JLabel tablejbl = new JLabel("\u8868\u540D\uFF1A");
        final JTextField tablefld = new JTextField(20);
        
        JLabel tablekeyjbl = new JLabel("\u4E3B\u952E\u751F\u6210\u7B56\u7565\uFF1A");
        final JComboBox tablekeyfld = new JComboBox(planets);
        
        JLabel sequence_lb = new JLabel("\u4E3B\u952ESEQUENCE\uFF1A(oracle\u5E8F\u5217\u540D)");
        final JTextField sequence_fld = new JTextField(20);
        
        JLabel titlelbl = new JLabel("\u529F\u80FD\u63CF\u8FF0\uFF1A");
        final JTextField titlefld = new JTextField();
        
        JLabel fieldRowNumlbl = new JLabel("\u884C\u5B57\u6BB5\u6570\u76EE\uFF1A");
        JTextField fieldRowNumfld = new JTextField();
        fieldRowNumfld.setText((new StringBuilder(String.valueOf(fieldRowNum))).toString());
		
        ButtonGroup bg = new ButtonGroup();
        final JRadioButton jsp = new JRadioButton("Table\u98CE\u683C(form)");
        jsp.setSelected(true);
        final JRadioButton div = new JRadioButton("Div\u98CE\u683C(form)");
        bg.add(jsp);
        bg.add(div);
        
        final JCheckBox actionButton = new JCheckBox("Action");
        actionButton.setSelected(true);
        final JCheckBox jspButton = new JCheckBox("Jsp");
        jspButton.setSelected(true);
        final JCheckBox serviceIButton = new JCheckBox("ServiceI");
        serviceIButton.setSelected(true);
        final JCheckBox serviceImplButton = new JCheckBox("ServiceImpl");
        serviceImplButton.setSelected(true);
        final JCheckBox daoIButton = new JCheckBox("DaoI");
        daoIButton.setSelected(true);
        final JCheckBox daoImplButton = new JCheckBox("DaoImpl");
        daoImplButton.setSelected(true);
        JCheckBox pageButton = new JCheckBox("Page");
        pageButton.setSelected(true);
        final JCheckBox entityButton = new JCheckBox("Entity");
        entityButton.setSelected(true);
		
		jp.add(infolbl);
		jp.add(showlbl);
		jp.add(packagebl);
		jp.add(packField);
		jp.add(entitylbl);
		jp.add(entityField);
		
		jp.add(tablejbl);
		jp.add(tablefld);
		jp.add(tablekeyjbl);
		jp.add(tablekeyfld);
		jp.add(sequence_lb);
		jp.add(sequence_fld);
		
		jp.add(titlelbl);
		jp.add(titlefld);
		jp.add(fieldRowNumlbl);
		jp.add(fieldRowNumfld);
		
		jp.add(actionButton);
        jp.add(jspButton);
        jp.add(serviceIButton);
        jp.add(serviceImplButton);
        
        jp.add(daoIButton);
        jp.add(daoImplButton);
        
        jp.add(pageButton);
        jp.add(entityButton);
        
        jp.add(jsp);
        jp.add(div);
        
        JButton confirmbtn = new JButton("\u751F\u6210");
        confirmbtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(!"".equals(packField.getText())){
					CodeWindow.entityPackage  = packField.getText().trim();
				}else{
					showlbl.setForeground(Color.red);
					showlbl.setText("\u5305\u540D\u4E0D\u80FD\u4E3A\u7A7A\uFF01");
					return;
				}
				
				if(StringUtil.isNotEmpty(entityField.getText())){
					CodeWindow.entityName  = entityField.getText().trim();
				}else{
					showlbl.setForeground(Color.red);
					showlbl.setText("\u5B9E\u4F53\u7C7B\u540D\u4E0D\u80FD\u4E3A\u7A7A\uFF01");
					return;
				}
				
				if(StringUtil.isNotEmpty(titlefld.getText())){
					CodeWindow.funDescription  = titlefld.getText().trim();
				}else{
					showlbl.setForeground(Color.red);
					showlbl.setText("\u63CF\u8FF0\u4E0D\u80FD\u4E3A\u7A7A\uFF01");
					return;
				}
				
				if(StringUtil.isNotEmpty(tablefld.getText())){
					CodeWindow.tableName  = tablefld.getText().trim();
				}else{
					showlbl.setForeground(Color.red);
					showlbl.setText("\u8868\u540D\u4E0D\u80FD\u4E3A\u7A7A\uFF01");
					return;
				}
				
				CodeWindow.primaryKeyPolicy = (String)tablekeyfld.getSelectedItem();
				if(CodeWindow.primaryKeyPolicy.equals("sequence")){
					if(StringUtil.isNotEmpty(sequence_fld.getText())){
						CodeWindow.sequneceCode = sequence_fld.getText();
					}else{
						showlbl.setForeground(Color.red);
						showlbl.setText("\u4E3B\u952E\u751F\u6210\u7B56\u7565\u4E3Asequence\u65F6\uFF0C\u5E8F\u5217\u53F7\u4E0D\u80FD\u4E3A\u7A7A\uFF01");
						return;
					}
				}
				CreateFileFlag createFileFlag = new CreateFileFlag();
				if(jsp.isSelected()){
					createFileFlag.setJspMode("01"); //table风格
				}
				if(div.isSelected()){
					createFileFlag.setJspMode("02");
				}
				if(actionButton.isSelected()){
					createFileFlag.setActionFlag(true);
				}
				if(jspButton.isSelected()){
					createFileFlag.setJspFlag(true);
				}
				if(serviceIButton.isSelected()){
					createFileFlag.setServiceIFlag(true);
				}
				if(serviceImplButton.isSelected()){
					createFileFlag.setServiceImplFlag(true);
				}
				if(daoIButton.isSelected()){
					createFileFlag.setDaoIFlag(true);
				}
				if(daoImplButton.isSelected()){
					createFileFlag.setDaoImplFlag(true);
				}
				if(entityButton.isSelected()){
					createFileFlag.setEntityFlag(true);
				}
				//判断表是否存在
				try {
					boolean tableexist = (new DatabaseTools()).checkTableExist(CodeWindow.tableName);
					if(tableexist){
						new CodeGenerate(CodeWindow.entityPackage, CodeWindow.entityName, CodeWindow.tableName, CodeWindow.funDescription, CodeWindow.primaryKeyPolicy, CodeWindow.sequneceCode, createFileFlag, CodeWindow.fieldRowNum).generateToFile();
						showlbl.setForeground(Color.red);
                        showlbl.setText((new StringBuilder("\u6210\u529F\u751F\u6210\u589E\u5220\u6539\u67E5->\u529F\u80FD\uFF1A")).append(CodeWindow.funDescription).toString());
					}else{
						showlbl.setForeground(Color.red);
						showlbl.setText("\u8868["+CodeWindow.tableName+"]\u5728\u6570\u636e\u5e93\u4e2d\u4e0d\u5b58\u5728");
					}
				} catch (Exception e2) {
					showlbl.setForeground(Color.red);
					showlbl.setText(e2.getMessage());
				}
				
				
			}
		});
        JButton extbtn = new JButton("\u9000\u51FA");
        extbtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
			
		});
        
        jp.add(confirmbtn);
        jp.add(extbtn);
		setTitle("\u4EE3\u7801\u751F\u6210\u5668[\u5355\u8868\u6A21\u578B]");
        setVisible(true);
        setDefaultCloseOperation(3);
        setSize(new Dimension(1000, 800));
        setResizable(true);
        setLocationRelativeTo(getOwner());
	}
	
}
