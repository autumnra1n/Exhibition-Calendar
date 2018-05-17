package com.company.exhibitions.dto;

import java.sql.Date;
import java.sql.Time;

public class Exposition {
    private final int id;
    private final String theme;
    private final Date dateStart;
    private final Time startTime;
    private final String description;
    private final Showroom showroom;

    private Exposition(ExpositionBuilder builder) {
        this.id = builder.id;
        this.theme = builder.theme;
        this.dateStart = builder.dateStart;
        this.startTime = builder.startTime;
        this.description = builder.description;
        this.showroom = builder.showroom;
    }
    public static class ExpositionBuilder {
        private int id;
        private final String theme;
        private Date dateStart;
        private Time startTime;
        private String description;
        private Showroom showroom;

        public ExpositionBuilder(String theme) {
            this.theme = theme;
        }
        public ExpositionBuilder setId(int id) {
            this.id = id;
            return this;
        }
        public ExpositionBuilder setDateStart(Date dateStart) {
            this.dateStart = dateStart;
            return this;
        }
        public ExpositionBuilder setStartTime(Time startTime) {
            this.startTime = startTime;
            return this;
        }
        public ExpositionBuilder setDescription(String description) {
            this.description = description;
            return this;
        }
        public ExpositionBuilder setShowroom(Showroom showroom) {
            this.showroom = showroom;
            return this;
        }

        public Exposition build() {
            return new Exposition(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getTheme() {
        return theme;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Time getStartTime() {
        return startTime;
    }

    public String getDescription() {
        return description;
    }

    public Showroom getShowroom() {
        return showroom;
    }

}
