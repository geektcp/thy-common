package com.geektcp.common.spring.util;

import com.geektcp.common.core.exception.BaseException;
import com.geektcp.common.core.util.IdUtils;
import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.genid.GenId;

@Slf4j
public class DefaultGenId implements GenId<String> {

    @Override
    public String genId(String table, String column) {
        try {
            String prefix = table;
            Thread.sleep(1);
            return IdUtils.getId(prefix);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BaseException(e);
        }
    }

}
