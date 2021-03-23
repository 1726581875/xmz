package ${packageName};

import javax.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import lombok.Data;
<#list javaTypeSet as type>
<#if type=='Date'>
import java.util.Date;
</#if>
<#if type=='BigDecimal'>
import java.math.BigDecimal;
</#if>
</#list>

@Data
@DynamicUpdate
@DynamicInsert
@Entity(name = "${tableName}")
public class ${className}{
    <#list fieldList as field>
    // ${field.comment}   
    <#if field.keyType=='PRI'>
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    </#if>
    private ${field.javaType} ${field.nameHump};
    </#list>
    
}