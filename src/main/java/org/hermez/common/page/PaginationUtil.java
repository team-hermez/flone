package org.hermez.common.page;

public class PaginationUtil {

    /**
     * 페이징 처리를 위한 계산을 수행합니다.
     *
     * @param totalItems 총 아이템 수
     * @param itemsPerPage 페이지당 아이템 수
     * @param currentPage 현재 페이지
     * @return {@link PageInfo} 객체로, 페이징 관련 정보를 담고 있습니다.
     */
    public static PageInfo calculatePagination(int totalItems, int itemsPerPage, int currentPage) {
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);
        int offset = (currentPage - 1) * itemsPerPage;

        return new PageInfo(totalItems, itemsPerPage, totalPages, currentPage, offset);
    }

    /**
     * 페이지 관련 정보를 담고 있는 내부 클래스입니다.
     */
    public static class PageInfo {
        private final int totalItems;
        private final int itemsPerPage;
        private final int totalPages;
        private final int currentPage;
        private final int offset;

        /**
         * 생성자. 페이지 정보를 초기화합니다.
         *
         * @param totalItems 총 아이템 수
         * @param itemsPerPage 페이지당 아이템 수
         * @param totalPages 총 페이지 수
         * @param currentPage 현재 페이지
         * @param offset 결과 집합의 시작 행 번호
         */
        public PageInfo(int totalItems, int itemsPerPage, int totalPages, int currentPage, int offset) {
            this.totalItems = totalItems;
            this.itemsPerPage = itemsPerPage;
            this.totalPages = totalPages;
            this.currentPage = currentPage;
            this.offset = offset;
        }


        public int getTotalItems() {
            return totalItems;
        }

        public int getItemsPerPage() {
            return itemsPerPage;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public int getOffset() {
            return offset;
        }
    }
}
