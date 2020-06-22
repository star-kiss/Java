package controller;

import domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName ReturnValueController
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/15 10:30
 * @Version 1.0
 */
@Controller
public class ReturnValueController
{

    //1.返回ModelAndView
    @RequestMapping("/model")
    public ModelAndView modelAndView()
    {
        ModelAndView mv = new ModelAndView();
        mv.addObject("name", "kiss");
        mv.setViewName("/view/success");
        return mv;
    }

    //2.返回String
    @RequestMapping("/string")
    public String returnString(HttpServletRequest request)
    {
        request.setAttribute("name", "kiss");
        return "/view/success";
    }
    //3.返回void 了解即可

    //4.返回对象
    @ResponseBody
    @RequestMapping("/static/retrunPerson")
    public Person returnStudent()
    {
        Person person = new Person();
        person.setName("Jack");
        person.setAge(22);
        return person;
    }

    //5.@ResponseBody返回字符串
    @ResponseBody
    @RequestMapping(value = "/static/returnStr",produces="text/html; charset=utf-8")
    public String returnStr()
    {

        return "Hello 小丶kiss";
    }
}
