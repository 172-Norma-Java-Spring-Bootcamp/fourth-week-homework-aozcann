package org.patikadev.ecommerce.exception;

public final class BusinessServiceOperationException {

    private BusinessServiceOperationException() {
    }

    public static class CustomerNotFoundException extends BaseException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }

    public static class CustomerAddressIsNotEmptyException extends BaseException {
        public CustomerAddressIsNotEmptyException(String message) {
            super(message);
        }
    }

    public static class CustemerAlreadyDeletedException extends BaseException {
        public CustemerAlreadyDeletedException(String message) {
            super(message);
        }
    }

    public static class BrandNotFoundException extends BaseException {
        public BrandNotFoundException(String message) {
            super(message);
        }
    }

    public static class BrandAlreadyDeletedException extends BaseException {
        public BrandAlreadyDeletedException(String message) {
            super(message);
        }
    }

    public static class CategoryNotFoundException extends BaseException {
        public CategoryNotFoundException(String message) {
            super(message);
        }
    }

    public static class CategoryAlreadyDeletedException extends BaseException {
        public CategoryAlreadyDeletedException(String message) {
            super(message);
        }
    }

    public static class ProductNotFoundException extends BaseException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }

    public static class ProductAlreadyDeletedException extends BaseException {
        public ProductAlreadyDeletedException(String message) {
            super(message);
        }
    }

}
