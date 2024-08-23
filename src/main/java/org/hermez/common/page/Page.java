package org.hermez.common.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Page<T> {

    private List<T> contents;
    private int totalElements;
    private int totalPages;
    private int currentPage;

    public Page(List<T> contents, int totalElements, int totalPages, int currentPage) {
        this.contents = contents;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }
}
