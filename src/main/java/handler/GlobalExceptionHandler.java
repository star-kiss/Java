package handler;

import exception.AgeException;
import exception.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/18 11:04
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler
{

    @ExceptionHandler(NameException.class)
    public ModelAndView doNameException(Exception exception)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","姓名必须为tom才可访问！");
        modelAndView.addObject("exception",exception);
        modelAndView.setViewName("/view/exception/nameException");

        return  modelAndView;
    }

    @ExceptionHandler(AgeException.class)
    public ModelAndView doAgeException(Exception exception)
    {
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("message","年龄必须小于80！");
        modelAndView.addObject("exception",exception);
        modelAndView.setViewName("/view/exception/ageException");

        return  modelAndView;
    }

    @ExceptionHandler()
    public ModelAndView doDefaultException(Exception exception)
    {
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.addObject("message","未知异常！");
        modelAndView.addObject("exception",exception);
        modelAndView.setViewName("/view/exception/defaultException");

        return  modelAndView;
    }
}
