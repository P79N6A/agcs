package org.agcs.system.hibernate.qbc;

public class PageUtil {
	
	public static int getCurPageNo(int total, int curPage, int pagesize){
		if(curPage > (int)Math.ceil((double)total/pagesize)){
			curPage = (int)Math.ceil((double)total/pagesize);
		}
		if(curPage <= 1){
			curPage = 1;
		}
		return curPage;
	}
	
	public static int getOffest(int total, int curPage, int pagesize){
		if(curPage > (int)Math.ceil((double)total/pagesize)){
			curPage = (int)Math.ceil((double)total/pagesize);
		}
		if(curPage <= 1){
			curPage = 1;
		}
		return (curPage-1)*pagesize;
	}

}
