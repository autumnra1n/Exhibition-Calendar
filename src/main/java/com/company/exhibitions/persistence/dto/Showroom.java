package com.company.exhibitions.dto;

public class Showroom {
    private final int id;
    private final String name;
    private final String location;
    private final String description;

    private Showroom(ShowroomBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.location = builder.location;
        this.description = builder.description;
    }

    public static class ShowroomBuilder{
        private final int id;
        private final String name;
        private String location;
        private String description;

        public ShowroomBuilder(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public ShowroomBuilder setLocation(String location){
            this.location = location;
            return this;
        }
        public ShowroomBuilder setDescription(String description){
            this.description = description;
            return this;
        }
        public Showroom build(){
            return new Showroom(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

}
