package com.company.exhibitions.service.impl;

import com.company.exhibitions.service.*;

public class ServiceFactory implements IServiceFactory {

    private final IExpositionService expositionService;
    private final IPaymentService paymentService;
    private final IRoleService roleService;
    private final IShowroomService showroomService;
    private final ITicketService ticketService;
    private final IUserService userService;

    public ServiceFactory(){
        this.expositionService = new ExpositionService();
        this.paymentService = new PaymentService();
        this.roleService = new RoleService();
        this.showroomService = new ShowroomService();
        this.ticketService = new TicketService();
        this.userService = new UserService();
    }

    @Override
    public IExpositionService getExpositionService(){
        return expositionService;
    }

    @Override
    public IPaymentService getPaymentService(){
        return paymentService;
    }

    @Override
    public IRoleService getRoleService(){
        return roleService;
    }

    @Override
    public IShowroomService getShowroomService(){
        return showroomService;
    }

    @Override
    public ITicketService getTicketService(){
        return ticketService;
    }

    @Override
    public IUserService getUserService(){
        return userService;
    }
}
