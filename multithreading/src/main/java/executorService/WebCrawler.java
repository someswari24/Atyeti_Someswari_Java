package executorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class WebCrawler {
    private static final int THREADPOOLSIZE=10;
    private static final int TIMEOUTSEC=10;

    public static void main(String[] args) throws InterruptedException {
        List<String>urls=List.of(
                "https://www.google.com",
                "https://www.wikipedia.org",
                "https://www.github.com",
                "https://www.oracle.com",


                "https://www.baeldung.com",
                "https://www.microsoft.com",

                "https://www.baeldung.com/java-completablefuture-threadpool"
        );

        ExecutorService executor= Executors.newFixedThreadPool(THREADPOOLSIZE);
        Queue<String>titles=new ConcurrentLinkedQueue<>();
        List<Future<String>> futures=new ArrayList<>();

        for(String url:urls){
            futures.add(executor.submit(new FetchTitle(url)));
        }

        for (Future<String>future:futures){
            try {
                String title=future.get(TIMEOUTSEC,TimeUnit.SECONDS);
                if (title != null) {
                    titles.add(title);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        if (!executor.awaitTermination(30, TimeUnit.SECONDS)){
            executor.shutdownNow();
        }

        System.out.println("Titles");
        System.out.println("============");
        titles.forEach(System.out::println);
    }

}
