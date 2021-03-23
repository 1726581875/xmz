package ${packageName};

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Id;
<#list javaTypeSet as type>
<#if type=='Date'>
import java.util.Date;
</#if>
<#if type=='BigDecimal'>
import java.math.BigDecimal;
</#if>
</#list>

@DynamicInsert
@DynamicUpdate
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
    
    <#list fieldList as field>
    public ${field.javaType} get${field.nameBigHump}() {
        return ${field.nameHump};
    }

    public void set${field.nameBigHump}(${field.javaType} ${field.nameHump}) {
        this.${field.nameHump} = ${field.nameHump};
    }

    </#list>

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        <#list fieldList as field>
        sb.append(", ${field.nameHump}=").append(${field.nameHump});
        </#list>
        sb.append("]");
        return sb.toString();
    }

}