package com.company.exhibitions.dto;

import java.sql.Date;

public class Payment {
    private final int id;
    private final Date date;
    private final User user;
    private final Ticket ticket;

    public Payment(int id, Date date, User user, Ticket ticket) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public Ticket getTicket() {
        return ticket;
    }

}
