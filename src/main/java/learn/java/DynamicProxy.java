package learn.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 */
public class DynamicProxy {

    public static void main(String[] args) {
        // 生成动态代理
        // 被代理类
        CalculatorImpl calculatorImpl = new CalculatorImpl();
        CalculatorHandler calculatorHandler = new CalculatorHandler(calculatorImpl);
        // 代理类
        Calculator calculator = (Calculator) Proxy
                .newProxyInstance(calculatorImpl.getClass().getClassLoader(), calculatorImpl.getClass().getInterfaces(),
                                  calculatorHandler);
        System.out.println(calculator.add(1, 2));
        System.out.println(calculator.minus(1, 2));
    }

}

/**
 * 代理类和被代理类的公共接口
 */
interface Calculator {
    Integer add(Integer num1, Integer num2);

    Integer minus(Integer num1, Integer num2);
}

/**
 * 被代理类
 */
class CalculatorImpl implements Calculator {

    @Override
    public Integer add(Integer num1, Integer num2) {
        int ret = num1 + num2;
        System.out.println("in calculatorImpl, res: " + ret);
        return ret;
    }

    @Override
    public Integer minus(Integer num1, Integer num2) {
        int ret = num1 - num2;
        System.out.println("int calculatorImpl, res: " + ret);
        return ret;
    }

}

/**
 * 实现InvocationHandler接口的类，用于请求转发
 */
class CalculatorHandler implements InvocationHandler {

    private Object obj; //被代理类

    public CalculatorHandler(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("in calculatorhandler, before invocation");

        Object ret = method.invoke(obj, args);  //执行被代理类方法

        System.out.println("in calculationhandler, after invocation");
        return ret;
    }

}