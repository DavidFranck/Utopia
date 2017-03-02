package com.david.guava;

import com.google.common.base.*;
import com.google.common.base.Objects;
import com.google.common.collect.*;
import com.google.common.hash.*;
import com.google.common.io.*;
import com.google.common.math.IntMath;
import com.google.common.reflect.*;
import com.sun.istack.internal.Nullable;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by David
 * on 2017/3/2
 */
public class TestGuava {
    @Test
    public void testObjects() {
        boolean aNull = Objects.equal("null", null);
        System.out.println(aNull);
    }

    @Test
    public void testCompare() {
        Comparable a = ComparisonChain.start().compare("", "", Ordering.<String>natural().nullsLast()).result();
    }

    @Test
    public void testReflection() {
//        System.out.println(Reflection.getPackageName(TestGuava.class));

    }

    @Test
    public void test111() {
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));
//        returns true, even though ArrayList<String> is not assignable from ArrayList<Integer>
    }

    //获取泛型的运行时类型
    @Test
    public void testTypeToken() {
        TypeToken<String> stringTok = TypeToken.of(String.class);
        TypeToken<Integer> intTok = TypeToken.of(Integer.class);
        System.out.println(stringTok);
        System.out.println(intTok);
    }

    @Test
    public void testTypeToken1() {
        TypeToken<List<String>> stringListTok = new TypeToken<List<String>>() {
        };
        System.out.println(stringListTok);

    }

    static <K, V> TypeToken<Map<K, V>> mapToken(TypeToken<K> keyToken, TypeToken<V> valueToken) {
        return new TypeToken<Map<K, V>>() {
        }
                .where(new TypeParameter<K>() {
                }, keyToken)
                .where(new TypeParameter<V>() {
                }, valueToken);
    }

    @Test
    public void testMapToken() {
        TypeToken<Map<String, BigInteger>> mapToken = mapToken(
                TypeToken.of(String.class),
                TypeToken.of(BigInteger.class)
        );
        TypeToken<Map<Integer, Queue<String>>> complexToken = mapToken(
                TypeToken.of(Integer.class),
                new TypeToken<Queue<String>>() {
                }
        );
        System.out.println(mapToken);
        System.out.println(complexToken);
    }

    /////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testInvokable() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = this.getClass().getDeclaredMethod("add", new Class<?>[]{Integer.class, Integer.class});
//        System.out.println(method.invoke(new TestGuava(),1,2));
        Invokable<?, Object> invokable = Invokable.from(method);
        System.out.println(invokable.getName());
        System.out.println(invokable.getParameters());
//        System.out.println();
        System.out.println(invokable.getParameters().get(0).isAnnotationPresent(Nullable.class));
    }

    @Test
    public void testAbstractinvocationHandler() {

//        AbstractInvocationHandler
    }

    @Test
    public void testClassPath() throws IOException {
        ClassPath classpath = ClassPath.from(this.getClass().getClassLoader()); // scans the class path used by classloader
        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses("com.david.guava")) {
            System.out.println(classInfo.getName());
        }
    }

    @Test
    public void testIntMath() {
        IntMath.checkedAdd(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    //合并字符串
    @Test
    public void testJoiner() {
        String join = Joiner.on(",").skipNulls().join(1, null, 2, 3, 4);
        System.out.println(join);
    }

    //切割字符串
    @Test
    public void testSpiler() {
        Iterable<String> split = Splitter.on(',')
//        Iterable<String> split = Splitter.on(CharMatcher.whitespace())
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux,XX");
        for (String s : split) {
            System.out.println(s);
        }
    }

    @Test
    public void testCharMatcher() {
        String string = "1234abcdABCD";
        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string); //移除control字符
        String theDigits = CharMatcher.DIGIT.retainFrom(string); //只保留数字字符
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');
//去除两端的空格，并把中间的连续空格替换成单个空格
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*"); //用*号替换所有数字
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string);
//        System.out.println(CharMatcher.ANY.retainFrom(string));
//        System.out.println(theDigits);
//        System.out.println(lowerAndDigit);
        System.out.println(CharMatcher.anyOf("aeiou").removeFrom(string));
    }
    @Test
    public void testCharSet(){
        String string = "SSS";
        Charset utf8 = Charsets.UTF_8;
        System.out.println(utf8);
    }
    @Test
    public void testCaseFormat(){
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_UNDERSCORE,"CONSTANT_NAME"));
        String constant_name = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME");// returns "constantName"
        System.out.println(constant_name);
    }
    @Test
    public void testByteStream() throws IOException {
//        byte[] bytes = ByteStreams.toByteArray(new FileInputStream(new File("C:\\Users\\wanghh\\Desktop\\hello.txt")));
        byte[] bytes = Files.toByteArray(new File("C:\\Users\\wanghh\\Desktop\\hello.txt"));
        for (byte aByte : bytes) {
            System.out.println((char) aByte);
        }
    }
    @Test
    public void testCharStream() throws IOException {
        String string = CharStreams.toString(new FileReader(new File("C:\\Users\\wanghh\\Desktop\\hello.txt")));
        System.out.println(string);
//        Files.asCharSource(new File(""),Charsets.UTF_8);
//        ByteSource wrap = ByteSource.wrap(new byte[]{1,2,3});
    }
    @Test
    public void testSourceAndSink() throws Exception {
        File file = new File("C:\\Users\\wanghh\\Desktop\\hello.txt");
        //Read the lines of a UTF-8 text file
        ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
//Count distinct word occurrences in a file
        Multiset<String> wordOccurrences = HashMultiset.create(
                Splitter.on(CharMatcher.WHITESPACE)
                        .trimResults()
                        .omitEmptyStrings()
                        .split(Files.asCharSource(file, Charsets.UTF_8).read()));

//SHA-1 a file
        HashCode hash = Files.asByteSource(file).hash(Hashing.sha1());
        HashCode md5 = Files.asByteSource(file).hash(Hashing.md5());

        System.out.println(lines);
        System.out.println(wordOccurrences);
        System.out.println(hash);
        System.out.println(md5);
//Copy the data from a URL to a file
//        Resources.asByteSource( new URL("")).copyTo(Files.asByteSink(file));
    }
    @Test
    public void testSource() throws IOException {
        ByteSource byteSource = Files.asByteSource(new File("C:\\Users\\wanghh\\Desktop\\hello.txt"));
//        InputStream inputStream = byteSource.openStream();
//        InputStream inputStream = byteSource.openBufferedStream();
    }


    public static Integer add(Integer a, Integer b) {
        return a + b;
    }
    @Test
    public void testRange (){

    }


}
