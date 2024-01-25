package com.nothing.lab3.validator;


import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;


@FacesValidator("validatorX")
public class ValidatorX implements Validator<Double> {

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Double x) throws ValidatorException {
        if (x == null) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "X не может быть пустым."));
        }

        if (x > 5 || x < -3) {
            throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "X неверен. " +
                                    "Проверьте, является ли он числом или входит промежуток от -3 до 5."));
        }
    }
}
