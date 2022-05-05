package org.patikadev.ecommerce.validator;

import org.patikadev.ecommerce.exception.BaseValidationException;
import org.patikadev.ecommerce.exception.ValidationOperationException;
import org.patikadev.ecommerce.model.enums.CampaignType;
import org.patikadev.ecommerce.model.request.CreateCampaignRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Objects;

@Component
public class CreateCampaignRequestValidator implements Validator<CreateCampaignRequest> {
    @Override
    public void validate(CreateCampaignRequest request) throws BaseValidationException {

        if (Objects.isNull(request)) {
            throw new ValidationOperationException.CampaignNotValidException("Campaign can not be null or empty");
        }
        if (!(StringUtils.hasLength(request.name()))) {
            throw new ValidationOperationException.CampaignNotValidException("Campaign name can not be null or empty");
        }
        if (!(StringUtils.hasLength(request.code()))) {
            throw new ValidationOperationException.CampaignNotValidException("Campaign code can not be null or empty");
        }
        if (Objects.isNull(request.type())) {
            throw new ValidationOperationException.CampaignNotValidException("Campaign type can not be null or empty");
        }
        if (Objects.isNull(request.discount())) {
            throw new ValidationOperationException.CampaignNotValidException("Campaign discount can not be null or empty");
        }
        if (Objects.isNull(request.limit())) {
            throw new ValidationOperationException.CampaignNotValidException("Campaign limit can not be null or empty");
        }
        if (request.type().equals(CampaignType.RATE)) {
            if (request.discount().compareTo(BigDecimal.ZERO) < 0) {
                throw new ValidationOperationException.CampaignDiscountValidException("Discount can not be negative");
            }
            if (!((request.discount().compareTo(BigDecimal.ONE) < 1) && (request.discount().compareTo(BigDecimal.ZERO) > 0))) {
                throw new ValidationOperationException.CampaignDiscountValidException("Discount can not be bigger than one.");
            }
        }
        if (request.type().equals(CampaignType.AMOUNT)) {
            if (request.discount().compareTo(BigDecimal.ONE) < 1) {
                throw new ValidationOperationException.CampaignDiscountValidException("Discount can not be less than one.");
            }
        }
    }

}
