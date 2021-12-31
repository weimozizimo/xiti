package lock;

import tests.User;

import java.util.ArrayList;
import java.util.List;

public class LockTest1 {
    public static void main(String[] args) {
        List<Long> existsIds = new ArrayList<>();
        Long a1 = 1L;
        Long a2 = 2L;
        Long a3 = 3L;
        existsIds.add(a1);
        existsIds.add(a2);
        existsIds.add(a3);
        long a4 = 1L;

        System.out.println(existsIds.contains(a4));

    }
}
