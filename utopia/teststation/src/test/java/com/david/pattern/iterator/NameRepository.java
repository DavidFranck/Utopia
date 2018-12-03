package com.david.pattern.iterator;

public class NameRepository implements Container<String> {

    String[] data = {"aaa", "bbb", "ccc"};

    @Override
    public Iterator<String> getIterator() {
        return new StringIterator();
    }

    private class StringIterator implements Iterator<String> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < data.length;
        }

        @Override
        public String next() {
            if (!hasNext()) return null;
            return data[index++];
        }
    }
}
