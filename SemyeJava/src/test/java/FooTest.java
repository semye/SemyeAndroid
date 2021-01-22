import com.semye.base.target.Foo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class FooTest {


    @Mock
    Foo foo;

    @Before
    public void setUp() throws Exception {
        //如果使用Runwith PowerMockRunner 不需要使用下面这行
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFoo() {
        PowerMockito.when(foo.getName()).thenReturn("foo");
        Assert.assertEquals("foo", foo.getName());
    }
}
