package com.company.exhibitions.web.servlet;

import com.company.exhibitions.utils.Mapper;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.controller.Controller;
import com.company.exhibitions.web.controller.impl.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final ControllerFactory factory = Mapper.getControllerFactory();
        Controller controller = factory.getExpositionController()
                .linkWith(factory.getPaymentController())
                .linkWith(factory.getRoleController())
                .linkWith(factory.getShowroomController())
                .linkWith(factory.getTicketController())
                .linkWith(factory.getUserController());

        Command command = controller.defineCommand(request.getParameter("command"));
        String page = command.execute(request, response);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
