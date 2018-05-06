package com.company.exhibitions.service;

public class ServiceFactory {
    private final static ServiceFactory serviceFactory = new ServiceFactory();
    private ExpositionService expositionService = new ExpositionService();
    private PaymentService paymentService = new PaymentService();
    private RoleService roleService = new RoleService();
    private ShowroomService showroomService = new ShowroomService();
    private TicketService ticketService = new TicketService();
    private UserService userService = new UserService();

    private ServiceFactory(){}

    public static ServiceFactory getInstatice(){
        return serviceFactory;
    }

    public ExpositionService getExpositionService(){
        return expositionService;
    }

    public PaymentService getPaymentService(){
        return paymentService;
    }

    public RoleService getRoleService(){
        return roleService;
    }

    public ShowroomService getShowroomService(){
        return showroomService;
    }

    public TicketService getTicketService(){
        return ticketService;
    }

    public UserService getUserService(){
        return userService;
    }
}
