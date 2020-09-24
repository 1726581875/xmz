package ${controllerPkName};

import ${servicePkName}.${Domain}Service;
import ${entityPkName}.${Domain};
import ${voPkName}.PageVO;
import ${voPkName}.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ${author}
 * @date: ${nowDate}
 */
@RestController
@RequestMapping("/admin")
public class ${Domain}Controller {

    @Autowired
    private ${Domain}Service ${domain}Service;

    @GetMapping("/${domain}/{id}")
    public RespResult findById(@PathVariable Integer id){
        return RespResult.success(${domain}Service.findById(id));
    }

    @GetMapping("/${domain}s")
    public RespResult findByPage(${Domain} matchObject , Integer page ,Integer pageSize){
        PageVO<${Domain}> pageResultVO = ${domain}Service.findPage(matchObject, page, pageSize);
        return  RespResult.success(pageResultVO);
    }

    @PutMapping("/${domain}/{id}")
    public RespResult update(${Domain} ${domain}){
        Integer flag = ${domain}Service.update(${domain});
        if(flag == 0){
            return RespResult.fail("更新${Domain}失败");
        }
        return  RespResult.success("更新${Domain}成功");
    }

    @PostMapping("/${domain}")
    public RespResult insert(${Domain} ${domain}){
        Integer flag = ${domain}Service.insert(${domain});
        if(flag == 0){
            return RespResult.fail("新增${Domain}失败");
        }
        return  RespResult.success("新增${Domain}成功");
    }

    @DeleteMapping("/${domain}/{id}")
    public RespResult delete(Integer id){
        Integer flag = ${domain}Service.deleteById(id);
        if(flag == 0){
            return RespResult.fail("删除${Domain}失败");
        }
        return  RespResult.success("删除${Domain}成功");
    }

}
