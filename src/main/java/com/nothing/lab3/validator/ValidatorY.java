package com.nothing.lab3.validator;


import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

@FacesValidator("validatorY")
public class ValidatorY implements Validator<Double> {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Double y) throws ValidatorException {
        if (y == null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                            "Y не может быть пустым."));
        }
        if(y < -3 || y > 5){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null,
                    "Y неверен. " +
                            "Проверьте, является ли он числом или входит промежуток от -3 до 5."));
        }
    }
}
