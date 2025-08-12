package questions.practical;

public class PracticalQuestions11 {

    public static void main(String[] args) {

    }
}

class ExceptionQuestion {
    public static void main(String[] args) {
        RuntimeException e = null;
        try {
            throwsEx();
        } catch (Exception ex) {
            throw e;
        }
    }

    private static void throwsEx() throws Exception { //MUST THROW
        Exception ex = null;
        throw ex;
    }
}

class ExceptionSuppressQuestion {
    public static void main(String[] args) { //Exception in thread "main" java.lang.RuntimeException: Finally
        try {
            throw new RuntimeException("Ex");
        } catch (Exception ex) {
            throw new RuntimeException("Catch");
        } finally {
            throw new RuntimeException("Finally");
        }
    }
}
