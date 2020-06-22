package exception;

/**
 * @ClassName UserException
 * @Description TODO
 * @Author kiss
 * @Date 2020/6/18 10:36
 * @Version 1.0
 */
public class UserException extends  Exception
{
    public UserException()
    {
        super();
    }

    public UserException(String message)
    {
        super(message);
    }
}
