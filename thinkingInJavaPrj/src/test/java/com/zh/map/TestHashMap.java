package com.zh.map;

import java.util.HashMap;
import java.util.Map;

public class TestHashMap {

    public static void main(String[] args) {
        Map<User, Integer> map = new HashMap<>();
        User user1 = new User();
        user1.setName("1");
        user1.setName1("2");

        User user2 = new User();
        user2.setName("1");
        user2.setName1("2");
        map.put(user1,1);
        System.out.println(map.containsKey(user2));
    }

    static class User {
        private String name;

        private String name1;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public int hashCode() {
            return 0;
        }

        public boolean equals(Object o) {
            User user = (User) o;
            if (!user.getName().equals(this.getName())) {
                return false;
            }
            if (!user.getName1().equals(this.getName1())) {
                return false;
            }
            return true;
        }
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1; // 减一，为了对已经是2的次幂的数正确求值，对大于0且不是2的次幂的数不会对最高位的1有影响
        n |= n >>> 1; // 将最高位的1后面一位变为1，其他位不管
        n |= n >>> 2; // 将最高两位的1，后面两位变为1，其他位不管
        n |= n >>> 4; // 按上面类推
        n |= n >>> 8; // 按上面类推
        n |= n >>> 16; // 按上面类推，此时最高位的1到最末尾都是1，及001111....11这种格式
        int result = (n < 0) ? 1 : (n >= 2 << 30) ? 2 << 30 : n + 1; // 最后加1，得到2的次幂
        return result;
    }
}
