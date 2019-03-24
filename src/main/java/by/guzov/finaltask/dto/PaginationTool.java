package by.guzov.finaltask.dto;

public class PaginationTool {
    private int currentPage = 1;
    private int amountOnPage;
    private int recordNumber;

    public PaginationTool(int currentPage, int amountOnPage, int recordNumber) {
        this.currentPage = currentPage;
        this.amountOnPage = amountOnPage;
        this.recordNumber = recordNumber;
    }

    public PaginationTool() {
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getAmountOnPage() {
        return amountOnPage;
    }

    public void setAmountOnPage(int amountOnPage) {
        this.amountOnPage = amountOnPage;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
}
