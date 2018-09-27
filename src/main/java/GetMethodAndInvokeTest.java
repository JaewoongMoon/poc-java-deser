import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Jae-Woong Moon(mjw8585@gmail.com)
 * @brief
 * @Date 2018. 6. 25.
 */
public class GetMethodAndInvokeTest {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		String myName = "JaewoongMoon";
		Method lengthMethod = String.class.getMethod("length"); //스트링 클래스의 메서드를 가져온다.

		System.out.println("invoke : " + lengthMethod.invoke(myName)); // 동적으로 메서드를 실행(리플렉션)
		System.out.println("length : " + myName.length());
	}
}
