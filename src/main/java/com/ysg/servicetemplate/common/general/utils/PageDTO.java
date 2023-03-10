package com.ysg.servicetemplate.common.general.utils;

import com.querydsl.jpa.JPQLQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO<T> {
    private long total;
    private List<T> data;

    public <U> PageDTO<U> map(Function<? super T, ? extends U> converter) {
        return new PageDTO<>(this.total, this.data.stream().map(converter).collect(Collectors.toList()));
    }

    public static <T> PageDTO<T> build(Page<T> page) {
        return PageDTO.<T>builder()
                .data(page.getContent())
                .total(page.getTotalElements())
                .build();
    }

    public static <T> PageDTO<T> getPage(JPQLQuery<T> query, PageRequest pageRequest) {
        long totalCount = query.fetch().size();
        List<T> resultList = query
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        return PageDTO.<T>builder()
                .total(totalCount)
                .data(resultList)
                .build();
    }
}
