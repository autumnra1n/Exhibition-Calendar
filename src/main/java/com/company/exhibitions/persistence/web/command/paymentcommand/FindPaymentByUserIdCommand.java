package com.company.exhibitions.web.command.paymentcommand;

import com.company.exhibitions.exception.ServiceException;
import com.company.exhibitions.service.IPaymentService;
import com.company.exhibitions.service.impl.ServiceFactory;
import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.utils.PageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindPaymentByUserIdCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        ServiceFactory serviceFactory = Mapper.getServiceFactory();
        IPaymentService paymentService = serviceFactory.getPaymentService();
        try {
            request.setAttribute("payment", paymentService.findPaymentByUserId(extractParameters(request)));
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return PageManager.getProperty("page.admin");
    }
}
