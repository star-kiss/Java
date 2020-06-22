package domain;

import java.util.Scanner;

/**
 * @ClassName TestFinal
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/15 11:47
 * @Version 1.0
 */
public class TestFinal
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.println("请输入你父亲的名子");
        String fatherName = s.next();
        System.out.println(fatherName);
        //Father 中的有参构造方法时三个参数
        Father f1 = new Father(fatherName,54,"您辛苦了.");
        System.out.println("我的父亲："+f1.getName()+"今年"+f1.getAge()+".我想对他说："+f1.str);
        Father f2 = new Daughter();//向上转型
        //通过父类的引用指向子类的对象实体，当调用方法时，实际执行的是子类重写父类的方法
        f2.say();
        Daughter d = (Daughter) f2;//向下转型，使用强制符:()
        d.shopping();
        Daughter d1 = new Daughter("张娟", 22, "专科", new Cat("Tom",1));
        d1.toString();
        Daughter d2 = new Daughter("张娟", 22, "专科", new Cat("Tom",1));
        System.out.println("d1对象==d2对象吗？"+d1.equals(d2));//false
        //调用方法验证字符串操作
        f1.testString();
        //调用方法打印九九乘法口诀表
        f1.recite();
    }

}
