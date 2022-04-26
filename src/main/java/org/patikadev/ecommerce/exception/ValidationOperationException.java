package org.patikadev.ecommerce.exception;

public final class ValidationOperationException {

    private ValidationOperationException() {
    }

    public static class CustomerNotValidException extends BaseException {
        public CustomerNotValidException(String message) {
            super(message);
        }
    }

    public static class ProductNotValidException extends BaseException {
        public ProductNotValidException(String message) {
            super(message);
        }
    }

    public static class BrandNotValidException extends BaseException {
        public BrandNotValidException(String message) {
            super(message);
        }
    }

    public static class CategoryNotValidException extends BaseException {
        public CategoryNotValidException(String message) {
            super(message);
        }
    }


    public static class CustomerIDNotValidException extends BaseException {
        public CustomerIDNotValidException(String message) {
            super(message);
        }
    }

    public static class ProductPriceNotLessZeroValidException extends BaseException {
        public ProductPriceNotLessZeroValidException(String message) {
            super(message);
        }


    }

}
