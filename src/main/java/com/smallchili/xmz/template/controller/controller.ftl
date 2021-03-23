package ${controllerPkName};

import ${servicePkName}.${Domain}Service;
import ${entityPkName}.${Domain};
import ${voPkName}.PageVO;
import ${voPkName}.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author ${Author.author}
 * @date: ${Author.date}
 */
@RestController
@RequestMapping("/admin/${domain}s")
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true")
public class ${Domain}Controller {

    @Autowired
    private ${Domain}Service ${domain}Service;

    /**
     * 分页查询${domain}接口
     * get请求
     * url: /admin/${domain}s/list
     * @param matchObject
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public RespResult findByPage(${Domain} matchObject,
                                 @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        return RespResult.success(${domain}Service.findPage(matchObject, pageIndex, pageSize));
    }

    /**
     * 更新${domain}接口
     * 请求方法: put
     * url: /admin/${domain}s/{id}
     * @param ${domain}
     * @return
     */
    @PutMapping("/{id}")
    public RespResult update(@RequestBody ${Domain} ${domain}) {
        Integer flag = ${domain}Service.update(${domain});
        if (flag == 0) {
            return RespResult.fail("更新${Domain}失败");
        }
        return RespResult.success("更新${Domain}成功");
    }

    /**
     * 插入或更新${domain}
     * 请求方法: post
     * url: /admin/${domain}s/${domain}
     * @param ${domain}
     * @return
     */
    @PostMapping("/${domain}")
    public RespResult insertOrUpdate(@RequestBody ${Domain} ${domain}) {
        Integer flag = ${domain}Service.insertOrUpdate(${domain});
        if (flag == 0) {
            return RespResult.fail("新增${Domain}失败");
        }
        return RespResult.success("新增${Domain}成功");
    }

    /**
     * 删除${domain}接口
     * 请求方法: delete
     * url: /admin/${domain}s/{id}
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public RespResult delete(@PathVariable Integer id) {
        Integer flag = ${domain}Service.deleteById(id);
        if (flag == 0) {
            return RespResult.fail("删除${Domain}失败");
        }
        return RespResult.success("删除${Domain}成功");
    }

    /**
     * 批量删除${domain}接口
     * 请求方法: post
     * url: /admin/${domain}s/bacth/delete
     * @param ${domain}IdList
     * @return
     */
    @PostMapping("/batch/delete")
    public RespResult deleteMultiple(@RequestBody List<Integer> ${domain}IdList) {
        ${domain}Service.deleteAllByIds(${domain}IdList);
        return RespResult.success("批量删除${Domain}成功");
    }

}