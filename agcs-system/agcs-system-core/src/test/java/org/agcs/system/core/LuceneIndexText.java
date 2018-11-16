package org.agcs.system.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class LuceneIndexText {
	
	private static String Dest_Index_Path = "H:\\workshop\\TextIndex";//索引存放目录
	private static String Text_File_Path = "H:\\workshop\\ch2\\lucene.txt";
	static protected String[] keywords = {"001", "002", "003"};
	static protected String[] contents = {"记录一", "记录二", "记录三"};
	public static void main(String[] args) {
		//createIndex();
		//createTextIndex();
		createTextIndex();
	}
	
	/**
	 * 创建索引
	 */
	private static void createIndex(){
		try {
			Date start = new Date();
			File file = new File(Dest_Index_Path);
			Path path = FileSystems.getDefault().getPath(Dest_Index_Path);
			Directory directory = FSDirectory.open(path);
			Analyzer textAnalyzer = new StandardAnalyzer(); //使用Lucene提供的分词器
			IndexWriterConfig config = new IndexWriterConfig(textAnalyzer);
			IndexWriter textIndex = new IndexWriter(directory, config);
			for(int i = 0; i < 3; i++){
				Document document = new Document();//生成文档对象
				Field field_id = new Field("id", keywords[i], Store.YES, Index.ANALYZED);//生成关键字域
				document.add(field_id);
				Field field_cont = new Field("content", contents[i], Store.YES, Index.ANALYZED);//生成文本内容域
				document.add(field_cont);
				textIndex.addDocument(document);//把文档对象添加到索引中
				
			}
			//textIndex.optimize();//索引优化
			textIndex.close();//关闭索引
			Date end = new Date();
			long time = end.getTime()-start.getTime();
			System.out.println("Total Time:(ms)"+" "+time);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * 添加文本索引
	 */
	private static void createTextIndex(){
		try {
			Date start = new Date();
			File file = new File(Text_File_Path);
			//FileReader reader = new FileReader(file);
			Path path = FileSystems.getDefault().getPath(Dest_Index_Path);
			Directory directory = FSDirectory.open(path);
			Analyzer textAnalyzer = new StandardAnalyzer(); //使用Lucene提供的分词器
			IndexWriterConfig config = new IndexWriterConfig(textAnalyzer);
			IndexWriter textIndex = new IndexWriter(directory, config);
			Document document = new Document();
			Field field_name = new Field("name", file.getName(), Store.YES, Index.ANALYZED);
			document.add(field_name);
			
			FileInputStream inputfile = new FileInputStream(file);//生成文件输入流对象
			int len = inputfile.available();//文件长度
			byte[] buffer = new byte[len];
			inputfile.read(buffer);
			inputfile.close();
			String context = new String(buffer);
			Field field_context = new Field("content", context, Store.YES, Index.ANALYZED);
			document.add(field_context);
			textIndex.addDocument(document);
			textIndex.close();
			
			Date end = new Date();
			long time = end.getTime() - start.getTime();
			System.out.println("Total Time:(ms) "+time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 对已有lucene索引删除特定文档
	 * @param filename
	 */
	private static void delDocument(String filename){
		try {
//			File file = new File(Dest_Index_Path);
//			Path path = FileSystems.getDefault().getPath(Dest_Index_Path);
//			Directory directory = FSDirectory.open(path);
//			IndexReader r
//			Term term = new Term("name", filename);
//			reader.deleteDocuments(term);
//			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据关键字搜索
	 * @param keyword
	 */
	private static void queryLucene(String keyword){
		
	}

}
