import net.class101.server1.resource.StaticResources;
import net.class101.server1.thread.ProjectThread;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ProjectTest {

    @Test
    public void test() throws InterruptedException, FileNotFoundException {
        File file = new File("./src/main/resources/out_" + Thread.currentThread().getId());
        PrintStream printStream = new PrintStream(new FileOutputStream(file));
        System.setOut(printStream);
        System.setErr(printStream);

        StaticResources.init();
        ProjectThread thread = new ProjectThread();
        thread.setIterator(new ArrayList<>(Arrays.asList("o", "91008", "6", " ", "q")).iterator());

        ProjectThread thread2 = new ProjectThread();
        thread2.setIterator(new ArrayList<>(Arrays.asList("o", "91008", "6", " ", "q")).iterator());

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        printStream.close();
    }
}
