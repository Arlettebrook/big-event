package com.example.bigevent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThreadLocalTest {

    /**
     * ThreadLocal对象存储的变量是线程安全
     */
    @Test
    public void testThreadLocalSetAndGet(){
//        提供ThreadLocal对象
        ThreadLocal<Object> tl = new ThreadLocal<>();
//        开启两个线程
        new Thread(()->{
            tl.set("萧炎");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"蓝色").start();
        
        new Thread(()->{
            tl.set("药尘");
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
            System.out.println(Thread.currentThread().getName()+":"+tl.get());
        },"绿色").start();
    }
}
