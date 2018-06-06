package assembly.utils;

import java.util.Objects;

/**
 * Objects类测试
 */
public class ObjectsSample {

    public static void main(String[] args) {
        String s = Objects.toString(new User()); //String.valueOf(xxx)
        System.out.println(s);
        System.out.println(Objects.equals("123", "456"));
    }
    static class User {
        private String name;
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
