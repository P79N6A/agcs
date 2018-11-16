package org.agcs.system.core.filter;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class KeyWordRequestWrapper extends HttpServletRequestWrapper implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2251098481683245134L;
	private Map keymap;

	public KeyWordRequestWrapper(HttpServletRequest request, Map keymap) {
		super(request);
		this.keymap = keymap;
	}

	@Override
	public Map getParameterMap() {
		super.getContextPath();
		Map<String, String[]> map = super.getParameterMap();
		if(!map.isEmpty()){
			Set<String> keySet = map.keySet();
			Iterator<String> keyIte = keySet.iterator();
			while(keyIte.hasNext()){
				String key = keyIte.next();
				String[] values = map.get(key);
				for(int i = 0; i < values.length; i++){
					map.get(key)[i] = this.replaceParam(values[i]);
				}
			}
		}
		return map;
	}
	
	@Override
	public String getParameter(String name) {
		String param = super.getParameter(name);
		param = this.replaceParam(param);
		return param;
	}

	public String replaceParam(String name){
		return PropertiesUtil.replaceCheck(keymap, name);
	}

}

