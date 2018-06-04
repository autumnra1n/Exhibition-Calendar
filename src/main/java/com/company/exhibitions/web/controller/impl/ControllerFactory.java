package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.controller.Controller;
import com.company.exhibitions.web.controller.IControllerFactory;

public class ControllerFactory implements IControllerFactory {

    private final ApplicationController applicationController;

    public ControllerFactory(){
        ExpositionController expositionController = new ExpositionController(null);
        PaymentController paymentController = new PaymentController(expositionController);
        RoleController roleController = new RoleController(paymentController);
        ShowroomController showroomController = new ShowroomController(roleController);
        TicketController ticketController = new TicketController(showroomController);
        UserController userController = new UserController(ticketController);
        this.applicationController = new ApplicationController(userController);
    }

    @Override
    public Controller getControllerChain(){
        return applicationController;
    }
}
