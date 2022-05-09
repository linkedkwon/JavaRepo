package data_type.object;

public class ObjectCase {

    static class User{
        private int age, id;
        private String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        /** 1. Equals Different Instance for Field */
        @Override
        public boolean equals(Object o){
            if(!(o instanceof User) || o == null)
                return false;
            User user = (User)o;
            return user.name.equalsIgnoreCase(name)
                    && user.age == age;
        }

        @Override
        public String toString(){
            return "Member Info" + "\n" +
                   "Name:" + name + "\n" +
                   "Age:" + age;
        }

        /** 2. Equals Same Instance With HashCode() version */
        /**
        @Override
        public boolean equals(Object o){
            if(!(o instanceof User) || o == null)
                return false;
            return this.getId() == ((User)o).getId();
        }
        */
        public int getId(){
            return id;
        }

        @Override
        public int hashCode(){
            return getId();
        }

    }

}