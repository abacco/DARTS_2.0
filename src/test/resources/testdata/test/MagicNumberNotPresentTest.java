package test;

import org.junit.Assert;
import org.junit.Test;

public class MagicNumberNotPresentTest {

    private static final int EXPECTED = 1;

    public MagicNumberNotPresentTest(){

    }

    public void setup(){
        System.out.println("SETUP");
    }

    public void teardown(){
        System.out.println("TEARDOWN");
    }

    @Test
    public void test1() {
        int numeroUno = 1;
        Assert.assertEquals(EXPECTED,numeroUno);
    }

    @Test
    public void test2() {
        int numeroUno = 1;
        numeroUno++;
        Assert.assertNotEquals(EXPECTED,numeroUno);
    }

    @Test
    public void test3() {
        int numeroUno = 1;
        Assert.assertEquals(EXPECTED,numeroUno);
    }

    @Test
    public void testNoAssert() {
        int numeroUno = 1;
    }

    @Test
    public void testNoAssert() {
        String numeroUno = "";
        boolean n = numeroUno.equals("");
        System.out.println("BOOL"+ n);
    }
}