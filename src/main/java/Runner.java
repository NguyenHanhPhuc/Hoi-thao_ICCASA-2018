
import com.test.until.*;
import java.io.IOException;
import java.util.*;

public class Runner extends RunnerHelper{
    static List<TestcaseInfo> list = new ArrayList<>();
    public Runner() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        Runner runner = new Runner();
        boolean file = true;
        if(file){
            runner.loadTestCaseFromFile(list);
        }else {
            list.add(new TestcaseInfo("Testsuite","tc01",1));
            list.add(new TestcaseInfo("Testsuite","tc09",1));
            list.add(new TestcaseInfo("Testsuite","tc03",1));
            list.add(new TestcaseInfo("Testsuite","tc04",1));
            list.add(new TestcaseInfo("Testsuite","tc05",1));
            list.add(new TestcaseInfo("Testsuite","tc06",1));
            list.add(new TestcaseInfo("Testsuite","tc07",1));
            list.add(new TestcaseInfo("TC02","login",1));
        }

        runner.run(list);
    }


}
