package ${servicePkName};

import ${entityPkName}.${Domain};
import ${dtoPkName}.${Domain}DTO;
import ${daoPkName}.${Domain}Repository;
import ${utilPkName}.CopyUtil;
import ${voPkName}.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import java.util.List;
import java.util.Optional;

/**
 * @author ${Author.author}
 * @date: ${Author.date}
 */
@Service
public class ${Domain}Service {

    @Resource
    private ${Domain}Repository ${domain}Repository;

    /**
     * 根据Id查找
     * @param id
     * @return 如果找不到返回null
     */
    public ${Domain}DTO findById(Integer id){
        Optional<${Domain}> optional = ${domain}Repository.findById(id);
        if(!optional.isPresent()){
            return null;
        }
        return CopyUtil.copy(optional.get(), ${Domain}DTO.class);
    }

    public List<${Domain}DTO> findAll(){
        return CopyUtil.copyList(${domain}Repository.findAll(), ${Domain}DTO.class);
    }

    /**
     * 条件分页查询
     * @param matchObject 匹配条件
     * @param pageIndex 第几页
     * @param pageSize 每页大小
     * @return
     */
    public PageVO<${Domain}> findPage(${Domain} matchObject, Integer pageIndex, Integer pageSize){
        // 构造条件
        Example<${Domain}> example = Example.of(matchObject);
        // 构造分页参数 ,第几页,每页大小，排序
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize, Sort.Direction.DESC ,"createTime");
        // 传入条件，调用方法
        Page<${Domain}> ${domain}Page = ${domain}Repository.findAll(example, pageable);
        //获取page对象里的list
        List<${Domain}> ${domain}List = ${domain}Page.getContent();
        /* 封装到自定义分页结果 */
        PageVO<${Domain}> pageVO = new PageVO<>();
        pageVO.setContent(${domain}List);
        pageVO.setPageIndex(pageIndex);
        pageVO.setPageSize(pageSize);
        pageVO.setPageCount(${domain}Page.getTotalPages());
        return pageVO;
    }

    /**
     * 插入数据
     * @param ${domain}
     * @return 返回成功数
     */
    public Integer insert(${Domain} ${domain}){
        if (${domain} == null) {
            throw new IllegalArgumentException("插入表的对象不能为null");
        }
        ${Domain} new${Domain} = ${domain}Repository.save(${domain});
        return new${Domain} == null ? 0 : 1;
    }

    /**
     *  选择性更新
     * @param ${domain}
     * @return 返回成功条数
     */
    public Integer update(${Domain} ${domain}){
        
        if(${domain} == null || ${domain}.get${entityKey}() == null){
            throw new IllegalArgumentException("更新的对象不能为null");
        }
        Optional<${Domain}> optional = ${domain}Repository.findById(${domain}.get${entityKey}());
        if(!optional.isPresent()){
            throw new RuntimeException("找不到id为"+ ${domain}.get${entityKey}() +"的用户");
        }
        
        ${Domain} db${Domain} = optional.get();
        //把不为null的属性拷贝过去
        CopyUtil.notNullCopy(${domain}, db${Domain});
        //执行保存操作
        ${Domain} update${Domain} = ${domain}Repository.save(db${Domain});
        return update${Domain} == null ? 0 : 1;
    }


    public Integer deleteById(Integer id){
        ${domain}Repository.deleteById(id);
        ${Domain}DTO ${domain}DTO = findById(id);
        return ${domain}DTO == null ? 1 : 0;
    }


}
