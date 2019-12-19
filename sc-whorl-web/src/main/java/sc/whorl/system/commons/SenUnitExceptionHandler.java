package sc.whorl.system.commons;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * <一句话功能简述>
 * 拦截所有异常并返回
 *
 * @see: [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@ControllerAdvice
public class SenUnitExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(SenUnitExceptionHandler.class);

    @ExceptionHandler(value = SenUnitException.class)
    public
    @ResponseBody
    MsgResponseBody baseException(SenUnitException senUnitExp) {
        logger.error("SenunitExceptionHandler.senUnitException !", senUnitExp);
        return MsgResponseBody.error().setResultCode(senUnitExp.getCode()).setResult(senUnitExp.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public
    @ResponseBody
    MsgResponseBody allException(Exception exception) {
        logger.error("SenunitExceptionHandler.allException !", exception);
        return MsgResponseBody.error().setResultCode(String.valueOf(SenUnitDic.RESULT_CODE_UNKNOWN)).setResult(exception.getMessage());
    }


}
