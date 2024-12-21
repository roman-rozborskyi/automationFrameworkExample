package com.rozborskyi.automation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    @Test(description = "Some api test")
    public void someTest() {

        Assert.assertTrue(true, "");
    }
}
