package schedule;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.io.IOException;

@ContextConfiguration(locations={"classpath:spring/applicationContext.xml"})
public class OrderScuedule extends AbstractJUnit4SpringContextTests {
	@Test
	public void test1(){
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
    @Test
    public void test2(){
        String str="20140914-165";
        System.out.println(str.split("-")[1]);
    }
}
