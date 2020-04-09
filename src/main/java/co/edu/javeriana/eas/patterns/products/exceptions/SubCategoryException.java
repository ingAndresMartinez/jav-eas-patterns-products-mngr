package co.edu.javeriana.eas.patterns.products.exceptions;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.common.exceptions.QuotationCoreException;

public class SubCategoryException extends QuotationCoreException {

    public SubCategoryException(EExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public SubCategoryException(EExceptionCode exceptionCode, String causeMessage) {
        super(exceptionCode, causeMessage);
    }

}