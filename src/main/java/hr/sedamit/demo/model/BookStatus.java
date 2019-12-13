package hr.sedamit.demo.model;

import lombok.Getter;

@Getter
public enum BookStatus {
    RENTED("Rented"),
    AVAILABLE("Available");

    private String label;

    BookStatus(String label) {
        this.label = label;
    }
}
