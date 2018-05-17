package com.company.exhibitions.dto;

public class Role {
    private final int id;
    private final String name;

    public Role(RoleBuilder roleBuilder) {
        this.id = roleBuilder.id;
        this.name = roleBuilder.name;
    }

    public static class RoleBuilder{
        private int id;
        private final String name;

        public RoleBuilder(String name){
            this.name = name;
        }
        public RoleBuilder setId(int id){
            this.id = id;
            return this;
        }
        public Role build(){
            return new Role(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
