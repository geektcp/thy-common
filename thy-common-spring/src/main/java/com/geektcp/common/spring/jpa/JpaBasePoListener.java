package com.geektcp.common.spring.jpa;

import com.geektcp.common.spring.model.po.BasePo;
import com.geektcp.common.spring.util.HttpRequestHeadUtils;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * BasePo 保存通用字段赋值监听
 *
 * @author tanghaiyang
 * @date 2021/8/30 11:18
 */
public class JpaBasePoListener {

    @PrePersist
    public void prePersist(BasePo basePo) {
        String id = HttpRequestHeadUtils.getValueByKey("userId");
        if (StringUtils.isNotBlank(id)) {
            basePo.setCreateBy(id);
            basePo.setUpdateBy(id);
        }
        String tenantId = HttpRequestHeadUtils.getValueByKey("tenantId");
        if (StringUtils.isNotBlank(tenantId)) {
            basePo.setTenantId(tenantId);
        }
        Date now = new Date();
        basePo.setCreateDate(now);
        basePo.setUpdateDate(now);
        String isEnable = basePo.getIsEnable();
        if (StringUtils.isBlank(isEnable)) {
            basePo.setIsEnable("1");
        }
    }

    @PreUpdate
    public void preUpdate(BasePo basePo) {
        String id = HttpRequestHeadUtils.getValueByKey("userId");
        if (StringUtils.isNotBlank(id)) {
            basePo.setUpdateBy(id);
        }

        Date now = new Date();
        basePo.setUpdateDate(now);
    }
}
