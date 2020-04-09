package co.edu.javeriana.eas.patterns.products.exceptions;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.common.exceptions.QuotationCoreException;

public class ProductServiceNotFoundException extends QuotationCoreException {

    public ProductServiceNotFoundException(EExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public ProductServiceNotFoundException(EExceptionCode exceptionCode, String causeMessage) {
        super(exceptionCode, causeMessage);
    }

}