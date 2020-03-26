package domain;

public class Name {
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static NameBuilder builder(){
        return new NameBuilder();
    }

    public static class NameBuilder{
        private String firstName;
        private String lastName;

        public NameBuilder setFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public NameBuilder setLastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Name build(){
            return new Name(firstName, lastName);
        }
    }

}
