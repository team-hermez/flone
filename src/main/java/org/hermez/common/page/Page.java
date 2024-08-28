package org.hermez.common.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 페이지네이션된 결과를 담는 제네릭 클래스입니다. 이 클래스는 페이지당 콘텐츠와
 * 페이징 관련 정보를 포함합니다.
 *
 * @param <T> 페이지의 콘텐츠 타입을 나타내는 제네릭 타입
 * @author 김혁진
 */
@Getter
@Setter
public class Page<T> {

    /**
     * 페이지에 포함된 콘텐츠 리스트입니다.
     */
    private List<T> contents;

    /**
     * 전체 아이템 수입니다.
     */
    private int totalElements;

    /**
     * 총 페이지 수입니다.
     */
    private int totalPages;

    /**
     * 현재 페이지 번호입니다.
     */
    private int currentPage;

    /**
     * 생성자. 페이지 정보를 초기화합니다.
     *
     * @param contents 페이지에 포함된 콘텐츠 리스트
     * @param totalElements 전체 아이템 수
     * @param totalPages 총 페이지 수
     * @param currentPage 현재 페이지 번호
     */
    public Page(List<T> contents, int totalElements, int totalPages, int currentPage) {
        this.contents = contents;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }
}
