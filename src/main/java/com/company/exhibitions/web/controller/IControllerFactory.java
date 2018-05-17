package com.company.exhibitions.web.controller;

import com.company.exhibitions.web.controller.impl.*;

public interface IControllerFactory {

    ExpositionController getExpositionController();
    PaymentController getPaymentController();
    RoleController getRoleController();
    ShowroomController getShowroomController();
    TicketController getTicketController();
    UserController getUserController();
    ApplicationController getApplicationController();
}
