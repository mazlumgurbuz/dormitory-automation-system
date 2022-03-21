/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


/**
 *
 * @author Muhammed ARSLAN
 */

abstract class BaseBean{
    
    private String searchTerm;
	
	public void search() {
		this.setPage(1);
	}
	
	public void clearSearch() {
		this.setSearchTerm(null);
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	

    protected int page = 1; //birinci sayfada başlamsı içün 
    protected  static  int pageSize = 3; // kaç tane nesne her sayfada 
    protected int pageCount; //sayfa sayısını göstermek için

    public void next() {
        if (this.page == this.getPageCount())// eğer son sayfaya eşitse 
        {
            this.page = 1;
        } else {
            this.page++;
        }

    }

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }
    abstract int getPageCount();
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public static void setPageSize(int pageSize) {
        BaseBean.pageSize = pageSize;
    }
}
