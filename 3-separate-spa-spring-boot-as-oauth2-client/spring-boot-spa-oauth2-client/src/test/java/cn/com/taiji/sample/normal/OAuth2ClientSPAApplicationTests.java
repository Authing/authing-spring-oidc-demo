package com.example.sample.normal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OAuth2ClientSPAApplicationTests {

    @Test
    public void contextLoads() {
        Assert.assertTrue(1==1);
        System.out.println("可运行");
    }

}
