package org.agcs.lucene.demo;

import org.apache.lucene.facet.FacetsConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

public class AssociationsFacetsExample {
	
	private final Directory indexDir = new RAMDirectory();
	private final Directory taxDir = new RAMDirectory();
	private final FacetsConfig config;
	
	public AssociationsFacetsExample(){
		config = new FacetsConfig();
		config.setMultiValued("tags", true);
		config.setIndexFieldName("tags", "$tags");
		config.setMultiValued("genre", true);
		config.setIndexFieldName("genre", "$genre");
	}
	
	

}
