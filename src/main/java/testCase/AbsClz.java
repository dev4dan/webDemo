package testCase;

/**
 * Created by danielwood on 21/03/2017.
 */
public abstract class AbsClz {
    protected abstract void absMethod(String value);
    public void invokeAbsMethod(String value){
        System.out.println("invokeAbsMethod...");
        this.absMethod(value);
    }
}
