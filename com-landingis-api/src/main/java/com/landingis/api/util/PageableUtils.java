package com.landingis.api.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Pageable;

import java.util.stream.Collectors;

@UtilityClass
public class PageableUtils {

    public String pageableToString(Pageable pageable) {
        String pageableParam = "size=" + pageable.getPageSize() + "&page=" + pageable.getPageNumber();

        if (pageable.getSort().isSorted()) {
            String sortParam = pageable.getSort().stream()
                    .map(order -> order.getProperty() + "," + order.getDirection().name().toLowerCase())
                    .collect(Collectors.joining("&sort="));
            pageableParam += "&sort=" + sortParam;
        }

        return pageableParam;
    }
}
