package co.edu.javeriana.eas.patterns.products.exceptions;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.common.exceptions.QuotationCoreException;

public class ProductServiceManagementException extends QuotationCoreException {

    public ProductServiceManagementException(EExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ProductServiceManagementException(EExceptionCode exceptionCode, String causeMessage) {
        super(exceptionCode, causeMessage);
    }

}