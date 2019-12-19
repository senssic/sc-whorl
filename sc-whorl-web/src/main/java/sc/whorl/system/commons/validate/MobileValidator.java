package sc.whorl.system.commons.validate;

import org.springframework.util.ObjectUtils;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MobileValidator implements ConstraintValidator<Mobile, String> {

    private String moneyReg = "^1\\d{10}$";
    private Pattern moneyPattern = Pattern.compile(moneyReg);

    @Override
    public void initialize(Mobile mobile) {


    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext arg1) {
        if (ObjectUtils.isEmpty(value)) {
            return true;
        }
        return moneyPattern.matcher(value).matches();
    }

}  