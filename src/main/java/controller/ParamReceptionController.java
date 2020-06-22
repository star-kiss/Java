package controller;


import domain.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ParamReceptionController
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/12 14:14
 * @Version 1.0
 */

@Controller
@ResponseBody
@RequestMapping("param")
public class ParamReceptionController
{
    @RequestMapping(value = "requestParam",method = RequestMethod.GET)
    public String getRequestParam(@RequestParam("name") String name,@RequestParam("age") Integer age)
    {
        System.out.println("name:"+name+"||"+"age:"+age);
        return name+","+age;
    }

    @RequestMapping(value = "requestParam",method = RequestMethod.POST)
    public String postRequestParam(@RequestParam("name") String name,@RequestParam("age") Integer age)
    {
        System.out.println("name:"+name+"||"+"age:"+age);
        return name+","+age;
    }
    /**************************************************************/
    @RequestMapping(value = "pathVariable/{name}/{age}",method = RequestMethod.GET)
    public String getPathVariable(@PathVariable("name") String name,@PathVariable("age") Integer age)
    {
        System.out.println("name:"+name+"||"+"age:"+age);
        return name+","+age;
    }
    @RequestMapping(value = "pathVariable/{name}/{age}",method = RequestMethod.POST)
    public String posPathVariable(@PathVariable("name") String name,@PathVariable("age") Integer age)
    {
        System.out.println("name:"+name+"||"+"age:"+age);
        return name+","+age;
    }
    /**************************************************************/
    @RequestMapping(value = "autoMVC",method = RequestMethod.GET)
    public String getAutomaticMVC(String name,Integer age)
    {
        System.out.println("name:"+name+"||"+"age:"+age);
        return name+","+age;
    }
    @RequestMapping(value = "autoMVC",method = RequestMethod.POST)
    public String postAutomaticMVC(String name,Integer age)
    {
        System.out.println("name:"+name+"||"+"age:"+age);
        return name+","+age;
    }
    /**************************************************************/
    @RequestMapping(value = "autoPerson",method = RequestMethod.GET)
    public String getAutomaticPerson(Person person)
    {
        System.out.println("name:"+person.getName()+"||"+"age:"+person.getAge());
        return person.getName()+","+person.getAge();
    }
    @RequestMapping(value = "autoPerson",method = RequestMethod.POST)
    public String postAutomaticPerson(Person person)
    {
        System.out.println("name:"+person.getName()+"||"+"age:"+person.getAge());
        return person.getName()+","+person.getAge();
    }
}
