package set;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MySet<E> extends AbstractSet<E>
        implements Set<E>, Cloneable, Serializable {

    private static final long serialVersionUID = 6469698853560660658L;
    private transient HashMap<E, Object> map;
    private static final Object PRESENT = new Object();

    public MySet() {
        this.map = new HashMap<>();
    }

    public MySet(Collection<? extends E> c) {
        int capacity = Math.max(16, (int) (c.size() / .75f) + 1);
        map = new HashMap<>(capacity);
        addAll(c);
    }

    public MySet(int initialCapacity, float loadFactor) {
        map = new HashMap<>(initialCapacity, loadFactor);
    }

    public MySet(int initialCapacity) {
        map = new HashMap<>(initialCapacity);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) == PRESENT;
    }

    @Override
    public Object clone() {
        try {
            MySet<E> newSet = (MySet<E>) super.clone();
            newSet.map = (HashMap<E, Object>) this.map.clone();
            return newSet;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        int capacity = callHiddenMethod("capacity");
        float loadFactor = callHiddenMethod("loadFactor");

        out.writeInt(capacity);
        out.writeFloat(loadFactor);
        out.writeInt(map.size());

        for (E e : map.keySet()) {
            out.writeObject(e);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        int capacity = in.readInt();
        if (capacity < 0) {
            throw new InvalidObjectException("Illegal capacity: " + capacity);
        }

        float loadFactor = in.readFloat();
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new InvalidObjectException("Illegal load factor: " + loadFactor);
        }

        int size = in.readInt();
        if (size < 0) {
            throw new InvalidObjectException("Illegal size: " + size);
        }

        map = new HashMap<>(capacity, loadFactor);
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked")
            E e = (E) in.readObject();
            map.put(e, PRESENT);
        }
    }

    private <T> T callHiddenMethod(String methodName) {
        try {
            Method method = map.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);
            @SuppressWarnings("unchecked")
            T result = (T) method.invoke(map);
            return result;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        throw new RuntimeException("serialization problem");
    }
}
