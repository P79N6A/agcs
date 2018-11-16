package org.agcs.system.hibernate.antidao.factory;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PackagesToScanUtil {
	private static final String SUB_PACKAGE_SCREEN__SUFFIX = ".*";
	
	/**
	 * 从包package中获取所有的Class 
	 * @param pack
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Set<Class<?>> getClasses(String pack){
		// 第一个class类的集合  
		Set<Class<?>> classSet = new LinkedHashSet();
		// 是否循环迭代  
		boolean recursive = false;
		if(pack.lastIndexOf(SUB_PACKAGE_SCREEN__SUFFIX) != -1){
			pack = pack.replace(SUB_PACKAGE_SCREEN__SUFFIX, "");
			recursive = true;
		}
		// 获取包的名字 并进行替换  
		String packageName = pack;
		String packageDirName = pack.replace(".", "/");
		try {
			// 定义一个枚举的集合 并进行循环来处理这个目录下的things  
			Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			 // 循环迭代下去  
			while(dirs.hasMoreElements()){
				 // 获取下一个元素 
				URL url = (URL) dirs.nextElement();
				// 得到协议的名称  
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上  
				if("file".equals(protocol)){
					// 获取包的物理路径  
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中  
					findAndAddClassesInPackageByFile(packageName, filePath, 
				            recursive, classSet);
				}else if("jar".equals(protocol)){
					// 如果是jar包文件  定义一个JarFile  获取jar  
					JarFile jar = ((JarURLConnection)url.openConnection()).getJarFile();
					// 从此jar包 得到一个枚举类  
					Enumeration entries = jar.entries();
					// 同样的进行循环迭代  
					while(entries.hasMoreElements()){
						// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件  
						JarEntry entry = (JarEntry)entries.nextElement();
						String name = entry.getName();
						// 如果是以/开头的 
						if(name.charAt(0) == '/'){
							// 获取后面的字符串  
							name = name.substring(1);
						}
						// 如果前半部分和定义的包名相同  
						if (name.startsWith(packageDirName)) {
							// 如果以"/"结尾 是一个包  
							int idx = name.lastIndexOf('/');
							if (idx != -1){
								// 获取包名 把"/"替换成"." 
								packageName = name.substring(0, idx).replace('/', '.');
							}
							// 如果可以迭代下去 并且是一个包  
							if ((idx != -1) || (recursive)){
								 // 如果是一个.class文件 而且不是目录  
								 if ((name.endsWith(".class")) && (!entry.isDirectory())){
									// 去掉后面的".class" 获取真正的类名  
									 String className = name.substring(packageName.length() + 1, name.length() - 6);
									// 添加到classes  
									 classSet.add(Class.forName(packageName + '.' +className));
								 }
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classSet;
	}

	private static void findAndAddClassesInPackageByFile(String packageName,
			String packagePath, final boolean recursive, Set<Class<?>> classes) {
		// 获取此包的目录 建立一个File  
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回  
		if((!dir.exists()) || (!dir.isDirectory())){
		   return;
		}
		// 如果存在 就获取包下的所有文件 包括目录  
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件
			public boolean accept(File file) {
				return (recursive && file.isDirectory())  
	                        || (file.getName().endsWith(".class"));  
			}
	    });
		for (File file : dirfiles){
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(),
						file.getAbsolutePath(), recursive, classes);
			}else{
				String className = file.getName().substring(0, file.getName().length() - 6);
				try {
					String classUrl = packageName + '.' + className;
					if (classUrl.startsWith(".")) {
			            classUrl = classUrl.replaceFirst(".", "");
			        }
					classes.add(Thread.currentThread().getContextClassLoader()
				            .loadClass(classUrl));
				} catch (ClassNotFoundException e) {
			         e.printStackTrace();
			    }
			}
		}
	}

}
