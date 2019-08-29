package com.example.myapplication;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class JavaDemoTest {

  @Test
  public void whenTwoNumberPassedReturnsSum(){
    JavaDemo javaDemo = new JavaDemo();
    Assert.assertEquals(4, javaDemo.add(2,2));
  }

}