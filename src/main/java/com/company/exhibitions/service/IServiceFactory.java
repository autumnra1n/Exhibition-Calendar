package com.company.exhibitions.service;

import com.company.exhibitions.service.impl.*;

public interface IServiceFactory {

    IExpositionService getExpositionService();
    IPaymentService getPaymentService();
    IRoleService getRoleService();
    IShowroomService getShowroomService();
    ITicketService getTicketService();
    IUserService getUserService();
}
