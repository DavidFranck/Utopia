package com.david.pattern.iterator;

public class IteratorTest {
    public static void main(String[] args) {
        NameRepository repository = new NameRepository();
        Iterator<String> iterator = repository.getIterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next);
        }
    }
}
