package ${packageName};
import java.util.List;

/**
* @author ${author}
* @date ${nowDate}
* 封装分页结果
*/
public class PageVO <T> {
	// 当前第几页
	private Integer page;
	// 每页大小
	private Integer pageSize;
    // 总页数
	private Integer totalPages;
	// 页数据
	private List<T> content;

	public Integer getPage() {
		return page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "PageVO{" +
				"page=" + page +
				", pageSize=" + pageSize +
				", totalPages=" + totalPages +
				", content=" + content +
				'}';
	}
	
}
 