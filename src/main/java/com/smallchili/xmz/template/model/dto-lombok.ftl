package ${packageName};

import lombok.Data;
<#list javaTypeSet as type>
<#if type=='Date'>
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
</#if>
<#if type=='BigDecimal'>
import java.math.BigDecimal;
</#if>
</#list>

@Data
public class ${className}DTO implements Serializable{

    <#list fieldList as field> 
    // ${field.comment}
    <#if field.javaType == 'Date'>
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    </#if>
    private ${field.javaType} ${field.nameHump};
    
    </#list>
}