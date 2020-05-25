import java.io.*;

public class Test {
    public static void main(String[] argv) throws Exception {
        ClassLoader loader;
        for (; ; ) {
            loader = new DynamicClassloader(new String[]{"."});
            // текущий каталог "." будет единственным
            // каталогом поиска
            Class clazz = Class.forName("DynamicModule", true, loader);

            TrueStaticModule tsm = (TrueStaticModule) clazz.getDeclaredConstructor().newInstance();

            // counter из True-Static класса
            System.out.println(tsm.getCounter());
            // А сам класс Dynamic
            System.out.println(tsm);

            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }
    }
}