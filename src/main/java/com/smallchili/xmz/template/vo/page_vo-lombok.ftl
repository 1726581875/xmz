package ${packageName};

import java.util.List;
import java.io.Serializable;
import lombok.Data;
/**
* @author ${author}
* @date ${nowDate}
* 封装分页结果
*/
@Data
public class PageVO <T> implements Serializable{
    // 当前第几页
    private Integer pageIndex;
    // 每页大小
    private Integer pageSize;
    // 总页数
    private Integer pageCount;
    // 数据总条数
    private Integer pageTotal;
    // 页数据
    private List<T> content;

}