package testCase;

/**
 * Created by danielwood on 21/03/2017.
 */
public class ImplClz extends AbsClz{
    @Override
    protected void absMethod(String value) {
        System.out.println("absMethod impl----"+value);
    }
}
