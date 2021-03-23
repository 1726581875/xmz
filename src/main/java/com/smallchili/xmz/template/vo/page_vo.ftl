package ${packageName};
import java.util.List;
import java.io.Serializable;
/**
* @author ${Author.author}
* @date ${Author.date}
* 封装分页结果
*/
public class PageVO<T>  implements Serializable{
	// 当前第几页
	private Integer pageIndex;
	// 每页大小
	private Integer pageSize;
    // 总页数
	private Integer pageCount;
	// 页数据
	private List<T> content;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
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
				"pageIndex=" + pageIndex +
				", pageSize=" + pageSize +
				", pageCount=" + pageCount +
				", content=" + content +
				'}';
	}
	
}
 