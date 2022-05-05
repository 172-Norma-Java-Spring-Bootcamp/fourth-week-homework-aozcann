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

    public static class BasketNotFoundException extends BaseException {
        public BasketNotFoundException(String message) {
            super(message);
        }
    }

    public static class OrderNotFoundException extends BaseException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }

    public static class BasketItemQuantityCanNotBeLessThanZero extends BaseException {
        public BasketItemQuantityCanNotBeLessThanZero(String message) {
            super(message);
        }
    }

    public static class CampaignNotFoundException extends BaseException {
        public CampaignNotFoundException(String message) {
            super(message);
        }
    }

    public static class CampaignCodeNotFoundException extends BaseException {
        public CampaignCodeNotFoundException(String message) {
            super(message);
        }

    }
}