package ultilities;


public class ClsPagination {
	
	private int currentPage;
	private int perPage=5;
	private int totalCount;
	
	
	public ClsPagination(){
		this(1,10,0);
	}	
	
	public ClsPagination(int currentPage, int perPage, int totalCount){
		this.currentPage = currentPage;
		this.perPage = perPage;
		this.totalCount = totalCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}

	public int totalPages(){
		return (int) Math.ceil((double)this.totalCount/this.perPage);
	}
	
	public int nextPage(){
		return this.currentPage+1;
	}
	
	public int previousPage(){
		return this.currentPage-1;
	}
	
	public boolean hasNextPage(){
		return this.nextPage() <=this.totalPages()? true :false;
	}
	
	public boolean hasPreviousPage(){
		return this.previousPage()>=1 ? true : false;
	}	
	
	public int offset(){
		return (this.currentPage-1)* this.perPage;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public  int getPerPage() {
		return perPage;
	}

	public  void setPerPage(int perPage) {
		if(perPage > 100 ){
			perPage = 100;
		}
		this.perPage = perPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
