package cisco.reactx.blogs.util;


import javax.ws.rs.QueryParam;

public class QueryFilterBean {

	private @QueryParam("offset") int offset;
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	private @QueryParam("size") int count;
		
}