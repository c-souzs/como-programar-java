package course.generics.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintService<T> {
    private List<T> list = new ArrayList<>();

    public void addValue(T value) {
        list.add(value);
    }

    public T first() {
        if(list.isEmpty()) {
            throw new IllegalStateException("List is empty");
        }

        return list.get(0);
    }

    public void print() {
        if(list.isEmpty()) {
            System.out.println("List is empty.");
        } else {
            System.out.print("[ ");
            for (T value: list) {
                System.out.print(value + ", ");
            }
            System.out.print("] \n");
            System.out.printf("First: %s", first());
        }
    }
}
