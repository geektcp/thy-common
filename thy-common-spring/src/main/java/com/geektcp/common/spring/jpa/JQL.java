package com.geektcp.common.spring.jpa;

import lombok.NonNull;
import org.springframework.data.domain.Sort;

/**
 * @author Created by tanghaiyang on 2019/1/3.
 */
public class JQL {

    public static String likeWrap(@NonNull String value) {
        return "%" + value + "%";
    }

    public static String likeWrapLeft(@NonNull String value) {
        return "%" + value;
    }

    public static String likeWrapRight(@NonNull String value) {
        return value + "%";
    }

    public static class SORT{
        public static final Sort UPDATED_DT_DESC = new Sort(new Sort.Order(Sort.Direction.DESC, "updateDate"));
        public static final Sort CREATED_DT_DESC = new Sort(new Sort.Order(Sort.Direction.DESC, "createDate"));
    }
}
