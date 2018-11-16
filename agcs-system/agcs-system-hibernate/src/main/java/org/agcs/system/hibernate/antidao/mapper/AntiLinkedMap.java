package org.agcs.system.hibernate.antidao.mapper;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class AntiLinkedMap extends LinkedHashMap<String, Object>{
	
	private static final long serialVersionUID = 1L;
	private final Locale locale;
	
	public AntiLinkedMap(){
	    this(null);
	}
	
	public AntiLinkedMap(Locale locale) {
	    this.locale = (locale == null ? Locale.getDefault() : locale);
	}
	
	public AntiLinkedMap(int initialCapacity) {
	    this(initialCapacity, null);
	}
	
	public AntiLinkedMap(int initialCapacity, Locale locale) {
	    super(initialCapacity);
	    this.locale = (locale == null ? Locale.getDefault() : locale);
	}
	
	public boolean containsKey(Object key){
	    return ((key instanceof String)) && 
	      (super.containsKey(convertKey((String)key)));
	}
	
	public Object put(String key, Object value) {
	    return super.put(convertKey(key), value);
	}
	
	public void putAll(Map map) {
	    if (map.isEmpty()){
	      return;
	    }
	    Map.Entry entry;
	    for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); put(
	      convertKey((String)entry.getKey()), entry.getValue())){
	      entry = (Map.Entry)iterator.next();
	    }
	}
	
	public Object get(Object key) {
	    if ((key instanceof String)) {
	      return super.get(convertKey((String)key));
	    }
	    return null;
	}
	
	protected String convertKey(String key) {
	    return key.toLowerCase(this.locale);
	}
	
	public Object remove(Object key) {
	    if ((key instanceof String)) {
	      return super.remove(convertKey((String)key));
	    }
	    return null;
	}
	
	public void clear() {
	    super.clear();
	}

}
