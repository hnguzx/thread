package com.guzx.section6;

import java.util.*;
import java.util.function.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/8 14:49
 * @describe
 */
public class StreamDemo {

    public static void main(String[] args) throws Throwable {
//        testConsumer();
//        functionTest();
//        predicateTest();
//        streamTest();
//        streamAmongTest();
//        streamTerminalTest();
//        optionalTest();
//        biFunctionTest();
//        reduceTest();
        collectTest();
    }

    public static void testConsumer() {
        // 函数式接口只有一个抽象方法
        /*Consumer consumer = (o) -> {
            System.out.println(o+"123");
        };*/
        Consumer consumer = System.out::println;
//        consumer.accept("hello");
        Consumer consumer2 = n -> System.out.println(n + "222");
        // 先执行外层函数，再执行andThen中的函数
        consumer.andThen(consumer2).accept("t1");
    }

    public static void functionTest() {
        Function<Integer, Integer> function = s -> ++s;
        Function<Integer, Integer> function2 = s -> s * 2;

        System.out.println(function.apply(3));
        System.out.println(function2.apply(3));
        // 先执行compose中的函数，再以其输出当做外层函数的输入
        System.out.println(function.compose(function2).apply(3));
        System.out.println(function.andThen(function2).apply(3));
        // 返回一个不进行任何处理的Function
        Function<Object, Object> identity = Function.identity();
        Object apply = Function.identity().apply("123");
        System.out.println(apply);

    }

    public static void predicateTest() {
        Predicate<String> predicate1 = o -> o.equals("guzx");
        Predicate<String> predicate2 = o -> o.startsWith("l");

        // 直接执行
        System.out.println(predicate1.test("guzhixiong"));
        System.out.println(predicate2.test("lh"));
        // 执行后结果取反
        System.out.println(predicate1.negate().test("guzx"));
        // 多个测试都通过则返回true
        System.out.println(predicate1.and(predicate2).test("lgu"));
        // 多个测试只要有一个通过则返回true
        System.out.println(predicate1.or(predicate2).test("lgu"));
    }

    public static void streamTest() {
        Stream<Object> empty = Stream.empty();
        List<Integer> list = new ArrayList<>();
        // 串行stream
        Stream<Integer> stream = list.stream();
        // 并行stream
        Stream<Integer> integerStream = list.parallelStream();
        // 流中单个元素
        Stream<String> stream1 = Stream.of("guzx");
        stream1.forEach(System.out::println);
        // 流中多个元素
        Stream<String> stream2 = Stream.of("guzx", "lianghong");
        stream2.forEach(System.out::println);

        /*Stream<Integer> iterate = Stream.iterate(1, new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return ++integer;
            }
        });*/
        User user = new User(1, "guzx");
        Stream<User> iterate = Stream.iterate(user, new UnaryOperator<User>() {
            @Override
            public User apply(User t) {
                User user1 = new User(t.getAge() + 2, t.getName() + t.getAge());
                return user1;
            }
        });
        iterate.limit(10).forEach(System.out::println);

        /*Stream<Double> generate = Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        });*/
        Stream<Double> generate = Stream.generate(Math::random);
//        generate.limit(10).forEach(System.out::print);

//        Stream<? extends Number> concat = Stream.concat(iterate, generate);
//        concat.limit(10).forEach(System.out::print);
    }

    // 流中数据只能使用一次
    // 中间方法，返回Stream
    public static void streamAmongTest() {
        Stream<Integer> iterate = Stream.iterate(1, integer -> ++integer);
        System.out.println(iterate.isParallel());

        Stream<Integer> sequential = iterate.sequential();
        System.out.println(sequential.isParallel());

        Stream<Integer> parallel = iterate.parallel();
        System.out.println(parallel.isParallel());

        System.out.println(sequential.equals(parallel));

        Stream<Integer> unordered = iterate.unordered();
        System.out.println(unordered.isParallel());

        System.out.println(iterate.equals(unordered));
//        sequential.limit(10).forEach(System.out::print);

        // 获取n个元素组成的流
//        Stream<Integer> limit = sequential.limit(10);
//        limit.forEach(System.out::print);

        // 跳过前n个元素组成的流
//        Stream<Integer> skip = limit.skip(5);
//        skip.forEach(System.out::print);

        // 过滤
        /*Stream<Integer> integerStream = sequential.limit(10).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 4;
            }
        });*/
//        Stream<Integer> integerStream = sequential.limit(10).filter(integer -> integer > 4);
//        integerStream.forEach(System.out::print);

        // 一对一转换
        /*Stream<Object> objectStream = sequential.limit(10).map(new Function<Integer, Object>() {
            @Override
            public Object apply(Integer integer) {
                return integer * integer;
            }
        });*/
//        Stream<Object> objectStream = sequential.limit(10).map(integer -> integer * integer);
//        objectStream.forEach(System.out::print);

        // 一对一转换
        /*IntStream intStream = sequential.limit(10).mapToInt(new ToIntFunction<Integer>() {
            @Override
            public int applyAsInt(Integer value) {
                return value + 1;
            }
        });*/
//        IntStream intStream = sequential.limit(10).mapToInt(value -> value + 1);
//        intStream.forEach(System.out::print);

        // 元素一对多转换：对原Stream中的所有元素进行操作，每个元素会有一个或者多个结果，
        // 然后将返回的所有元素组合成一个统一的Stream并返回；
//        Stream<Integer> integerStream = sequential.limit(10).flatMap(n -> Stream.of(n * 2));
//        integerStream.forEach(System.out::print);

        // 去重
//        Stream<Integer> distinct = sequential.distinct();
//        distinct.forEach(System.out::print);

        // 排序
//        Stream<Integer> sorted = sequential.sorted();
//        sorted.forEach(System.out::print);

        // 对流中的数据处理后返回一个新的与原来的流相同的流
//        Stream<Integer> peek = sequential.peek(integer -> {
//            int i = integer * 2;
//        });
//        peek.forEach(System.out::print);
    }

    // 终端方法
    public static void streamTerminalTest() {
        Stream<Integer> iterate = Stream.iterate(1, integer -> ++integer);
        Stream<Integer> limit = iterate.limit(10);

        // 获取元素的数量
//        System.out.println(limit.count());

        // 获取流中的元素的迭代器
        /*Iterator<Integer> iterator = limit.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }*/

        // 可分隔迭代器，可并行遍历元素
        /*Spliterator<Integer> spliterator = iterate.spliterator();
        spliterator.trySplit().forEachRemaining(System.out::println);*/

        // 对所有元素进行迭代，如果流是串行的则串行遍历，否则并行遍历
//        limit.forEach(System.out::print);
        Stream<Integer> parallel = limit.parallel();
//        Stream<Integer> parallel = limit.sequential();
//        parallel.forEach(System.out::print);

        // 按元素顺序遍历
//        parallel.forEachOrdered(System.out::print);

        // 将流转为数组
//        System.out.println(Arrays.toString(parallel.toArray()));

        // 获取流中按指定规则的最大的元素
        /*Optional<Integer> max = parallel.max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                Integer result = Integer.compare(o1, o2);
                return result;
            }
        });*/
//        System.out.println(Integer.max(1, 2));
//        System.out.println(Integer.compare(1, 2));
//        Optional<Integer> max = parallel.max(Integer::max);
//        System.out.println(max.get());

        // 获取流中按指定规则的最小的元素
//        Optional<Integer> min = parallel.min(Integer::compare);
//        System.out.println(min.get());

        // 只要其中有一个元素满足传入的Predicate时返回True，否则返回False
        /*boolean b = parallel.anyMatch(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer > 3;
            }
        });*/
//        boolean b = parallel.anyMatch(x -> x > 3);
//        System.out.println(b);

        // 所有元素均满足传入的Predicate时返回True，否则False
//        boolean b = parallel.allMatch(x -> x > 4);
//        System.out.println(b);

        // 所有元素均不满足传入的Predicate时返回True，否则False
//        boolean b = parallel.noneMatch(x -> x < 11);
//        System.out.println(b);

        // 返回第一个元素的Optioanl对象；如果无元素返回的是空的Optional； 如果Stream是无序的，那么任何元素都可能被返回。
//        Optional<Integer> first = parallel.findFirst();
//        System.out.println(first.get());

        // 返回任意一个元素的Optional对象，如果无元素返回的是空的Optioanl。
//        Optional<Integer> first = parallel.findAny();
//        System.out.println(first.get());

        // 判断是否当前Stream对象是并行的
//        boolean isParallel = parallel.isParallel();
//        System.out.println(isParallel);
    }

    public static void optionalTest() throws Throwable {
        // 用于创建一个空的Optional对象；其value属性为Null。
        Optional<String> empty = Optional.empty();
        // 根据传入的值构建一个Optional对象;
        // 传入的值必须是非空值，否则如果传入的值为空值，则会抛出空指针异常。
        Optional<String> optionalS = Optional.of("guzx");
        // 根据传入值构建一个Optional对象
        // 传入的值可以是空值，如果传入的值是空值，则与empty返回的结果是一样的。
        Optional<String> o = Optional.ofNullable(null);
        // 获取optional中的值
        System.out.println(optionalS.get());
        // 判断optional中的值是否为null
        System.out.println(optionalS.isPresent());
        // 当Value不为空时，执行传入的Consumer；
        optionalS.ifPresent(x -> {
            String ai_lianghong = x.concat(" ai lianghong");
            System.out.println(ai_lianghong);
        });

        // 当Value为空或者传入的Predicate对象调用test(value)返回False时，返回Empty对象；否则返回当前的Optional对象
//        Optional<String> optionalS1 = o.filter(s -> s.startsWith("g"));
//        System.out.println(optionalS1);

        // 一对一转换：当Value为空时返回Empty对象，否则返回传入的Function执行apply(value)后的结果组装的Optional对象；
//        Optional<Object> o1 = o.map(s -> s + ".txt");
//        System.out.println(o1);

        // 一对多转换：当Value为空时返回Empty对象，
        // 否则传入的Function执行apply(value)后返回的结果（其返回结果直接是Optional对象）
//        Optional<String> optionalS1 = optionalS.flatMap(s -> Optional.of(s + ".doc"));
//        System.out.println(optionalS1);

        // Value不为空则返回Value，否则返回传入的值；
//        String other = optionalS.orElse("other");
//        String other1 = o.orElse("other");
//        System.out.println(other);
//        System.out.println(other1);

        // Value不为空则返回Value，否则返回传入的Supplier生成的值；
        String orElseGet = optionalS.orElseGet(new Supplier<String>() {
            @Override
            public String get() {
                return "orElseGet";
            }
        });
        String orElseGet1 = o.orElseGet(() -> "orElseGet");
        System.out.println(orElseGet);
        System.out.println(orElseGet1);

        // Value不为空则返回Value，否则抛出Supplier中生成的异常对象；
//        String orElseThrow = optionalS.orElseThrow(() -> new Throwable("exception guzx"));
//        System.out.println(orElseThrow);
//        o.orElseThrow(() -> new Throwable("exception guzx"));
    }

    public static void biFunctionTest() {
        /*BiFunction<Integer, Double, String> biFunction = new BiFunction<Integer, Double, String>() {
            @Override
            public String apply(Integer integer, Double aDouble) {
                return integer + "--" + aDouble;
            }
        };*/
        BiFunction<Integer, Double, String> biFunction = (integer, aDouble) -> integer + "--" + aDouble;
        String apply = biFunction.apply(2, 3.0);
        System.out.println(apply);

        /*BinaryOperator<String> binaryOperator = new BinaryOperator<String>() {
            @Override
            public String apply(String s, String s2) {
                return s + s2;
            }
        };*/
        BinaryOperator<String> binaryOperator = (s, s2) -> s + s2;
        System.out.println(binaryOperator.apply("guzx", "lianghong"));

        /*BiConsumer<Integer,String> biConsumer = new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer integer, String s) {
                System.out.println(integer+s);
            }
        };*/
        BiConsumer<Integer, String> biConsumer = (integer, s) -> System.out.println(integer + s);
        biConsumer.accept(20, "guzhixiong");
    }

    public static void reduceTest() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 8, 7);
        /*Optional<Integer> reduce = stream.reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });*/
        // 对所有元素进行二合运算
//        Optional<Integer> reduce = stream.reduce(Integer::sum);
//        System.out.println(reduce.get());
        // 给定一个初始值，对所有元素进行二合运算
//        Integer reduce = stream.reduce(4, Integer::sum);
//        System.out.println(reduce);

        // 串行情况下与两个参数基本一致
        /*User user = new User(24, "guzx");
        User reduce = stream.reduce(user, new BiFunction<User, Integer, User>() {
            @Override
            public User apply(User user, Integer integer) {
                user.setAge(user.getAge() + integer);
                return user;
            }
        }, new BinaryOperator<User>() {
            @Override
            public User apply(User user, User user2) {
                return null;
            }
        });
        System.out.println(reduce);*/

        Stream<String> stream1 = Stream.of("aa", "ab", "ac", "dd").parallel();
        /*ArrayList<String> arrayList = stream1.reduce(new ArrayList<String>(), new BiFunction<ArrayList<String>, String, ArrayList<String>>() {
            @Override
            public ArrayList<String> apply(ArrayList<String> strings, String s) {
                strings.add(s);
                return strings;
            }
        }, new BinaryOperator<ArrayList<String>>() {
            @Override
            public ArrayList<String> apply(ArrayList<String> strings, ArrayList<String> strings2) {
                return strings;
            }
        });
        System.out.println(arrayList);*/
        /*Predicate<String> predicate = s -> s.contains("a");
        ArrayList<String> arrayList = stream1.reduce(new ArrayList<>(), (strings, s) -> {
            if (predicate.test(s)) {
                strings.add(s);
            }
            return strings;
        }, (strings, strings2) -> strings);
        System.out.println(arrayList);*/

        // 并行情况，初始值与Stream中的元素在biFunction进行并行处理，最后在binaryOperator中进行串行处理
        Stream<Integer> parallel = Stream.of(1, 2, 3).parallel();
        Integer reduce = parallel.reduce(4, Integer::sum, Integer::sum);
        System.out.println(reduce);

        Predicate<String> predicate = s -> s.contains("a");
        ArrayList<String> arrayList = stream1.reduce(new ArrayList<>(), (strings, s) -> {
            if (predicate.test(s)) {
                strings.add(s);
            }
            return strings;
        }, (strings, strings2) -> {
            System.out.println(strings == strings2);
            return strings;
        });

        System.out.println(arrayList);

    }

    public static void collectTest() {
        Stream<String> stream = Stream.of("aa", "ab", "ac", "dd");
        Predicate<String> predicate = t -> t.contains("a");

        // 并行
        Stream<String> parallel = stream.parallel();
        ArrayList<String> collect = parallel.collect(ArrayList::new, (array, s) -> {
            if (predicate.test(s)) {
                array.add(s);
            }
        }, ArrayList::addAll);
        System.out.println(collect);

    }

}
