package com.company.exhibitions.dto;

public class Ticket {
    private final int id;
    private final String description;
    private final int value;
    private final int amount;
    private final Exposition exposition;
    
    private Ticket(TicketBuilder ticketBuilder) {
        this.id = ticketBuilder.id;
        this.description = ticketBuilder.description;
        this.exposition = ticketBuilder.exposition;
        this.value = ticketBuilder.value;
        this.amount = ticketBuilder.amount;
    }

    public static class TicketBuilder{
        private int id;
        private final String description;
        private final Exposition exposition;
        private final int value;
        private int amount;

        public TicketBuilder(String description, int value, Exposition exposition) {
            this.description = description;
            this.value = value;
            this.exposition = exposition;
        }
        public TicketBuilder setId(int id){
            this.id = id;
            return this;
        }
        public TicketBuilder setAmount(int amount){
            this.amount = amount;
            return this;
        }
        public Ticket build(){
            return new Ticket(this);
        }
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }

    public Exposition getExposition() {
        return exposition;
    }

    public int getAmount() {
        return amount;
    }
}
