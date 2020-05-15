import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import set.MySet;

import java.io.*;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class MySetTest {

    private static HashSet<String> hashSet = new HashSet<>();
    private MySet<String> mySet = new MySet<>();

    @BeforeAll
    public static void setUpHashSet() {
        hashSet.add("One");
        hashSet.add("Two");
        hashSet.add("Three");
    }

    @BeforeEach
    @Test
    public void setUpMySet() {
        mySet.addAll(hashSet);
        assertEquals(hashSet, mySet);
    }

    @Test
    public void addTest() {
        mySet.add("For");
        assertEquals(4, mySet.size());
        assertFalse(mySet.add("For"));
    }

    @Test
    public void containsTest() {
        assertTrue(mySet.contains("One"));
    }

    @Test
    public void containsAllTest() {
        assertTrue(mySet.containsAll(hashSet));
    }
    @Test
    public void removeTest() {
        mySet.remove("Three");
        assertEquals(2, mySet.size());
        assertFalse(mySet.remove("Three"));
    }

    @Test
    public void clearAndIsEmptyTest() {
        mySet.clear();
        assertTrue(mySet.isEmpty());
    }

    @Test
    public void cloneTest() {
        MySet cloneSet = (MySet) mySet.clone();
        assertNotSame(mySet, cloneSet);
        assertEquals(mySet, cloneSet);

        System.out.println("From cloned Set:");
        cloneSet.forEach(System.out::println);
    }

    @Test
    public void serialTest() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(mySet);
            oos.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            MySet loadedSet = (MySet) ois.readObject();

            assertEquals(mySet, loadedSet);
            System.out.println(mySet);
            System.out.println(loadedSet);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
