package domain;

/**
 * @ClassName Daughter
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/15 11:24
 * @Version 1.0
 */


//a)要求继承所有父类的属性 -- 子类继承父类 默认就是继承父类所有非私有的(以private声明的)属性和方法
public class Daughter extends Father
{
    //至少1个独立的属性 (该属性不是简单数据类型，要求自己编写一个类作为属性)
    private Cat cat;
    //子类私有方法
    public void shopping(){
        System.out.println("女儿爱逛街");
    }


    //b)实现至少1个方法的覆盖 -- 重写父类的say()方法
    @Override
    public void say()
    {
        System.out.println("I am Daughter Class.");
    }

    public Daughter(String name, int age, String str, Cat cat)
    {
        super(name, age, str);
        this.cat = cat;
    }

    @Override
    public String toString()
    {
        return "Daughter{" +
                "cat=" + cat +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", str='" + str + '\'' +
                '}';
    }

    public Daughter()
    {

    }
}
