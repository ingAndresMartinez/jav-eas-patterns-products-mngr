package co.edu.javeriana.eas.patterns.products.enums;

public enum ECategory {

    PRODUCT(1),
    SERVICE(2);

    private int category;

    ECategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

}
