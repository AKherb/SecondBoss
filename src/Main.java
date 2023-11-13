import java.util.concurrent.*;
import java.time.Instant;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long before = System.currentTimeMillis();

        Callable<Integer> task = new CountCallable();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ArrayList<Future> futures = new ArrayList<>();
        for(int i = 0; i < 100; i++)
        {
            futures.add(executorService.submit(task));
        }
        int result = 0;
        for(int i = 0; i < 100; i++)
        {
            result += (Integer)(futures.get(i).get());
        }

        long after = System.currentTimeMillis();

        System.out.println(result);
        System.out.println("Counting finished in " + (after - before) / 1000.0 + " seconds.");

        executorService.shutdown();

    }
}