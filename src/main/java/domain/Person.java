package domain;

/**
 * @ClassName Person
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/12 16:24
 * @Version 1.0
 */
public class Person
{
    private String name;
    private Integer  age;

    public String getName()
    {
        return name;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
