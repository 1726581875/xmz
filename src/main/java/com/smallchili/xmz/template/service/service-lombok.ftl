package ${servicePkName};

import ${entityPkName}.${Domain};
import ${daoPkName}.${Domain}Repository;
import ${utilPkName}.CopyUtil;
import ${voPkName}.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.*;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author ${author}
 * @date: ${nowDate}
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
    public ${Domain} findById(Integer id){
        Optional<${Domain}> optional = ${domain}Repository.findById(id);
        if(!optional.isPresent()){
            return null;
        }
        return optional.get();
    }

    public List<${Domain}> findAll(){
        return ${domain}Repository.findAll();
    }

    /**
     * 根据匹配条件查询所有
     * @param matchObject
     * @return
     */
    public List<${Domain}> findAllByCondition(${Domain} matchObject){
        return ${domain}Repository.findAll(Example.of(matchObject));
    }

    /**
     * 条件分页查询
     * @param matchObject 匹配对象
     * @param pageIndex 第几页
     * @param pageSize 每页大小
     * @return
     */
    public PageVO<${Domain}> findPage(${Domain} matchObject, Integer pageIndex, Integer pageSize){
        // 1、构造条件
         // 1.1 设置匹配策略，name属性模糊查询
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", match -> match.startsWith());//startsWith右模糊(name%)/contains全模糊(%name%)
         // 1.2 构造匹配条件Example对象
        Example<${Domain}> example = Example.of(matchObject,matcher);

        // 2、 构造分页参数 ,第几页,每页大小
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        // 3、 传入条件、分页参数，调用方法
        Page<${Domain}> ${domain}Page = ${domain}Repository.findAll(example, pageable);
        //获取page对象里的list
        List<${Domain}> ${domain}List = ${domain}Page.getContent();
        /* 4. 封装到自定义分页结果 */
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
     * 插入或更新数据
     * 说明:如果参数带id表示是更新，否则就是插入
     * @param ${domain}
     * @return 返回成功数
     */
    public Integer insertOrUpdate(${Domain} ${domain}){
        if (${domain} == null) {
            throw new IllegalArgumentException("插入表的对象不能为null");
        }
        // id不为空，表示更新操作
        if(${domain}.get${entityKey}() != null){
          return this.update(${domain});
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
        // 入参校验
        if(${domain} == null || ${domain}.get${entityKey}() == null){
            throw new IllegalArgumentException("更新的对象不能为null");
        }
        // 是否存在
        Optional<${Domain}> optional = ${domain}Repository.findById(${domain}.get${entityKey}());
        if(!optional.isPresent()){
            throw new RuntimeException("找不到id为"+ ${domain}.get${entityKey}() +"的${Domain}");
        }
        ${Domain} db${Domain} = optional.get();
        //把不为null的属性拷贝到db${Domain}
        CopyUtil.notNullCopy(${domain}, db${Domain});
        //执行保存操作
        ${Domain} update${Domain} = ${domain}Repository.save(db${Domain});
        return update${Domain} == null ? 0 : 1;
    }


    public Integer deleteById(Integer id){
        ${domain}Repository.deleteById(id);
        return  findById(id) == null ? 1 : 0;
    }

    /**
     * 批量删除
     * @param ${domain}IdList  id list
     * @return 删除条数
     */
    public Integer deleteAllByIds(List<Integer> ${domain}IdList){
        List<${Domain}> del${Domain}List = ${domain}Repository.findAllById(${domain}IdList);
        ${domain}Repository.deleteInBatch(del${Domain}List);
        return del${Domain}List.size();
    }


}
