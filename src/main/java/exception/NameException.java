package exception;

/**
 * @ClassName NameException
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/18 10:35
 * @Version 1.0
 */
public class NameException extends  UserException
{
    public NameException()
    {
        super();
    }

    public NameException(String message)
    {
        super(message);
    }
}
