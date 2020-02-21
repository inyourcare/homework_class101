import net.class101.server1.resource.StaticResources;
import net.class101.server1.thread.ProjectThread;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class ProjectTest {

    @Test
    public void test() throws InterruptedException {
        StaticResources.init();
        ProjectThread thread = new ProjectThread();
        thread.setIterator(new ArrayList<>(Arrays.asList("o", "91008", "6", " ", "q")).iterator());

        ProjectThread thread2 = new ProjectThread();
        thread2.setIterator(new ArrayList<>(Arrays.asList("o", "42031", "6", " ", "q")).iterator());

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();
    }
}
