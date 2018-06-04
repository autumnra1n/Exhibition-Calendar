package com.company.exhibitions.dto;

public class User {
    private final int id;
    private final String login;
    private final String password;
    private final String email;
    private final String firstName;
    private final String lastName;
    private final Role role;

    private User(UserBuilder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.role = builder.role;
    }
    public static class UserBuilder{
        private int id;
        private final String login;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private Role role;

        public UserBuilder(String login) {
            this.login = login;
        }
        public UserBuilder setId(int id){
            this.id = id;
            return this;
        }
        public UserBuilder setPassword(String password){
            this.password = password;
            return this;
        }
        public UserBuilder setEmail(String email){
            this.email = email;
            return this;
        }
        public UserBuilder setFirstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        public UserBuilder setLastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        public UserBuilder setRole(Role role){
            this.role = role;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }
}
