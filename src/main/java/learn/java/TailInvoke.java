package learn.java;

import java.util.stream.Stream;

/**
 * 尾递归
 * 依赖的是编译器对尾递归写法的优化，在很多语言中编译器都对尾递归有优化，然而这些语言中并不包括java，
 * 因此在这里我们使用lambda的懒加载(惰性求值)机制来延迟递归的调用，从而实现栈帧的复用。
 *
 * 因此我们需要设计一个这样的函数接口来代替递归中的栈帧，通过apply这个函数方法(取名叫apply是因为该方法的参数是一个栈帧，
 * 返回值也是一个栈帧,类比function接口的apply)完成每个栈帧之间的连接，除此之外，我们栈帧还需要定义几个方法来丰富这个尾递归的接口。
 *
 *     1.apply（连接栈帧，惰性求值）
 *     2.判断递归是否结束
 *     3.得到递归最后的结果
 *     4.执行递归(及早求值)
 */
public class TailInvoke {

    /**
     * 统一结构的方法,获得当前递归的下一个递归
     *
     * @param nextFrame 下一个递归
     * @param <T>       T
     * @return 下一个递归
     */
    public static <T> TailRecursion<T> call(final TailRecursion<T> nextFrame) {
        return nextFrame;
    }

    /**
     * 结束当前递归，重写对应的默认方法的值,完成状态改为true,设置最终返回结果,设置非法递归调用
     *
     * @param value 最终递归值
     * @param <T>   T
     * @return 一个isFinished状态true的尾递归, 外部通过调用接口的invoke方法及早求值, 启动递归求值。
     */
    public static <T> TailRecursion<T> done(T value) {
        return new TailRecursion<T>() {
            @Override
            public TailRecursion<T> apply() {
                throw new Error("递归已经结束,非法调用apply方法");
            }

            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public T getResult() {
                return value;
            }
        };
    }

    /**
     * 尾递归函数接口
     * @param <T>
     */
    interface TailRecursion<T> {
        /**
         * 用于递归栈帧之间的连接,惰性求值
         * @return 下一个递归栈帧
         */
        TailRecursion<T> apply();

        /**
         * 判断当前递归是否结束
         * @return 默认为false,因为正常的递归过程中都还未结束
         */
        default boolean isFinished(){
            return false;
        }

        /**
         * 获得递归结果,只有在递归结束才能调用,这里默认给出异常,通过工具类的重写来获得值
         * @return 递归最终结果
         */
        default T getResult()  {
            throw new Error("递归还没有结束,调用获得结果异常!");
        }

        /**
         * 及早求值,执行者一系列的递归,因为栈帧只有一个,所以使用findFirst获得最终的栈帧,接着调用getResult方法获得最终递归值
         * @return 及早求值,获得最终递归结果
         */
        default T invoke() {
            return Stream.iterate(this, TailRecursion::apply)
                         .filter(TailRecursion::isFinished)
                         .findFirst()
                         .get()
                         .getResult();
        }
    }

    /**
     * 阶乘计算 -- 使用尾递归接口完成
     * @param factorial
     * @param number
     * @return
     */
    public static TailRecursion<Long> factorialTailRecursion(final long factorial, final long number) {
        if (number == 1)
            return TailInvoke.done(factorial);
        else
            return TailInvoke.call(() -> factorialTailRecursion(factorial + number, number - 1));
    }

    public static void main(String[] args) {
        System.out.println(factorialTailRecursion(1,10000000).invoke());
    }

}
