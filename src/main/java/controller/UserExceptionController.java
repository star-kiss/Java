package controller;

import exception.AgeException;
import exception.NameException;
import exception.UserException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName UserExceptionController
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/18 10:39
 * @Version 1.0
 */
@Controller
@RequestMapping("exception")
public class UserExceptionController
{
    @RequestMapping("userException")
    public ModelAndView userException(String name,Integer age) throws UserException
    {

        if(!name.equals("tom")){throw new NameException("姓名不正确！");}
        if(age>80){throw new AgeException("年龄过大");}
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name",name);
        modelAndView.addObject("age",age);
        modelAndView.setViewName("/view/success");

        return modelAndView;
    }
}
