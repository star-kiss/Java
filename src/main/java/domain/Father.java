package domain;

/**
 * @ClassName Father
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/15 11:12
 * @Version 1.0
 */

public class Father
{
    // a)具有至少2个属性和2个方法，体现封装性
    protected String name;
    protected int age;
    protected String str;

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public String getStr()
    {
        return str;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public void setStr(String str)
    {
        this.str = str;
    }

    // b)构造器2个，带参和无参
    public Father(String name, int age, String str)
    {
        this.name = name;
        this.age = age;
        this.str = str;
    }

    public Father()
    {
        System.out.println("===无参构造器===");
    }

    //方法say 用于子类重写
    public void say()
    {
        System.out.println("I am Father Class");
    }

    //c)至少1个方法用于打印一个图形，不做限制（例如杨辉三角，例如乘法表）
    public void recite()
    {    //会背诵九九乘法口诀表
        for (int i = 1; i <= 9; i++)
        {   //一共九行
            for (int j = 1; j <= i; j++)
            {  //每行有i个等式
                System.out.print(i + "*" + j + "=" + i * j + "\t");
            }
            System.out.println();
        }
    }

    // d)至少1个方法用于测试字符串的相关函数（至少用到两个函数）
    public void testString()
    {   //会用字符串的相关函数
        System.out.println("该字符串" + this.str + "的长度为:" + this.str.length());
        System.out.println("返回字符串中第2个字符:" + this.str.charAt(2));
        String s = "我是计科专升本191班的学生，我的名字叫张静";
        String sr = s.replace('我', '你');
        System.out.println(sr);
    }

}
