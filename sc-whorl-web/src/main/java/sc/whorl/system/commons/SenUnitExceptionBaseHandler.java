package sc.whorl.system.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 继承AbstractErrorController 实现对于参数校验的处理
 *
 * @author sen
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class SenUnitExceptionBaseHandler extends AbstractErrorController {
    public SenUnitExceptionBaseHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    private static final Logger logger = LoggerFactory.getLogger(SenUnitExceptionBaseHandler.class);
    @Value("${server.error.path:${error.path:/error}}")
    private static String errorPath = "/error";


    /**
     * 请求参数校验错误
     *
     * @param req
     * @param rsp
     * @param ex
     * @return
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public MsgResponseBody methodArgumentNotValidException(HttpServletRequest req, HttpServletResponse rsp, MethodArgumentNotValidException ex) throws Exception {
        logger.error("SenUnitExceptionBaseHandler.badRequestException !", ex);
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuffer msg = new StringBuffer();
        fieldErrors.stream().forEach(fieldError -> {
            msg.append("[" + fieldError.getField() + "," + fieldError.getDefaultMessage() + "]");
        });
        return MsgResponseBody.error().setResultCode(String.valueOf(SenUnitDic.RESULT_CODE_VALIDATE_FAILED)).setResult(msg);
    }

    @Override
    public String getErrorPath() {
        return errorPath;
    }
}
