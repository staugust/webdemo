package cn.augusto.web.stb.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

//@SpringBootApplication
//@ComponentScan("cn.augusto.web.stb")
//@Import({cn.augusto.web.stb.controller.LoginController.class, cn.augusto.web.stb.controller.TestController.class})
public class TestApp {

  static AtomicInteger activeTask = new AtomicInteger();

  static class MyTask implements Runnable{
    public MyTask(CompletableFuture<Long> future){
      super();
      this.future = future;
    }
    public CompletableFuture<Long> future;
    @Override
    public void run() {
      try {
        Thread.sleep(ThreadLocalRandom.current().nextInt(100));
        future.complete(System.currentTimeMillis());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  static int POOL_SIZE = 20;
  public static void main(String args[]) throws InterruptedException {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, 0, TimeUnit.SECONDS,
        new SynchronousQueue<>(), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
      @Override
      public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(String.format("Active: %s, task count: %s, r addr: %s ", executor.getActiveCount(),
            executor.getTaskCount(), r.hashCode()));
      }
    });

    for(int idx = 0; idx < POOL_SIZE; idx++) {
      Thread th = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < 0xFFFFFFF; i++) {
            if (activeTask.incrementAndGet() > POOL_SIZE) {
              activeTask.decrementAndGet();
              continue;
            }
            CompletableFuture<Long> future = new CompletableFuture<>();
            try{
              executor.submit(new MyTask(future));
              future.whenComplete((val, t) -> {
                activeTask.decrementAndGet();
                try {
                  Thread.sleep(10);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                if (t != null) {
                  t.printStackTrace();
                }
              });
            }catch(Exception e){
              e.printStackTrace();
            }
          }
        }
      });
      th.start();
    }
    executor.awaitTermination(100, TimeUnit.SECONDS);
  }
}
