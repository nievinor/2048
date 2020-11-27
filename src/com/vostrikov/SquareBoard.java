package com.vostrikov;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SquareBoard extends Board {

    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    void fillBoard(List<Integer> list) {

        if (list.size() != weight * height) throw new RuntimeException();
        board.clear();
        for (int i = 0; i < weight; i++) {
            for (int j = 0; j < height; j++) {
                board.put(new Key(i, j), list.get(i * height + j));
            }
        }
    }

    @Override
    List<Key> availableSpace() {

        List<Key> keys = new ArrayList<>();
        for (Map.Entry<Key, Integer> entry : board.entrySet()) {
            if (entry.getValue() == null) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    @Override
    void addItem(Key key, Integer value) {
        board .put(key, value);
    }

    @Override
    Key getKey(int i, int j) {

        return board
                .keySet()
                .stream()
                .filter(key -> key.getI() == i && key.getJ() == j)
                .findFirst()
                .orElseGet(() -> null);
    }

    @Override
    Integer getValue(Key key) {
        return board.get(key);
    }

    @Override
    List<Key> getColumn(int j) {
        ArrayList<Key> list = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            list.add(getKey(i, j));
        }
        return list;
    }

    @Override
    List<Key> getRow(int i) {
        ArrayList<Key> list = new ArrayList<>();
        for (int j = 0; j < weight; j++) {
            list.add(getKey(i, j));
        }
        return list;
    }

    @Override
    boolean hasValue(Integer value) {
        return board.containsValue(value);
    }

    @Override
    List<Integer> getValues(List<Key> keys) {
        ArrayList<Integer> list = new ArrayList<>();
        for (Key key : keys) list.add(board.get(key));

        return list;
    }
}

