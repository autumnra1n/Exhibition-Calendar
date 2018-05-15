package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.controller.IControllerFactory;

public class ControllerFactory implements IControllerFactory {

    private final ExpositionController expositionController;
    private final PaymentController paymentController;
    private final RoleController roleController;
    private final ShowroomController showroomController;
    private final TicketController ticketController;
    private final UserController userController;

    public ControllerFactory(){
        this.expositionController = new ExpositionController();
        this.paymentController = new PaymentController();
        this.roleController = new RoleController();
        this.showroomController = new ShowroomController();
        this.ticketController = new TicketController();
        this.userController = new UserController();
    }

    @Override
    public ExpositionController getExpositionController() {
        return expositionController;
    }

    @Override
    public PaymentController getPaymentController() {
        return paymentController;
    }

    @Override
    public RoleController getRoleController() {
        return roleController;
    }

    @Override
    public ShowroomController getShowroomController() {
        return showroomController;
    }

    @Override
    public TicketController getTicketController() {
        return ticketController;
    }

    @Override
    public UserController getUserController() {
        return userController;
    }
}
