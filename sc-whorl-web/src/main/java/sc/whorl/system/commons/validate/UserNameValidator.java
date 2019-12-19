package sc.whorl.system.commons.validate;

import org.springframework.util.ObjectUtils;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 用户名相关验证
 * 4到16位（字母，数字，下划线，减号）
 */
public class UserNameValidator implements ConstraintValidator<UserName, String> {

    private String moneyReg = "^[a-zA-Z0-9_-]{4,16}$";
    private Pattern moneyPattern = Pattern.compile(moneyReg);

    @Override
    public void initialize(UserName userName) {


    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext arg1) {
        if (ObjectUtils.isEmpty(value)) {
            return true;
        }
        return moneyPattern.matcher(value).matches();
    }

}  