package com.example.bigevent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class UuidTest {

    @Test
    public void testUuid(){

        UUID uuid = UUID.randomUUID();
        String string = uuid.toString().replace("-","");
        System.out.println("string = " + string);
    }
}
