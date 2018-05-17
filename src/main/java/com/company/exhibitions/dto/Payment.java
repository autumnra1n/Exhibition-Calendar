package com.company.exhibitions.dto;

import java.sql.Date;

public class Payment {
    private final int id;
    private final Date date;
    private final User user;
    private final Ticket ticket;

    private Payment(PaymentBuilder paymentBuilder) {
        this.id = paymentBuilder.id;
        this.date = paymentBuilder.date;
        this.user = paymentBuilder.user;
        this.ticket = paymentBuilder.ticket;
    }

    public static class PaymentBuilder{
        private int id;
        private final Date date;
        private final User user;
        private final Ticket ticket;

        public PaymentBuilder(Date date, User user, Ticket ticket){
            this.date = date;
            this.user = user;
            this.ticket = ticket;
        }
        public PaymentBuilder setId(int id){
            this.id = id;
            return this;
        }
        public Payment build(){
            return new Payment(this);
        }
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
