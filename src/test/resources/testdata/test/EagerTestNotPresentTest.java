package test;

import main.EagerTestNotPresent;
import org.junit.Test;

public class EagerTestNotPresentTest {

  public void setup(){

  }

  public void teardown(){

  }

  @Test
  public void test1() {
      new EagerTestNotPresent().doSomething(1);
  }

  @Test
  public void test2() {
      new EagerTestNotPresent().doSomething(2);
  }

   @Test
  public void test3(){
      new EagerTestNotPresent().doSomething(22);
  }

}
