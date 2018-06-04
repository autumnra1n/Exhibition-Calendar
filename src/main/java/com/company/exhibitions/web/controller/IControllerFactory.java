package com.company.exhibitions.web.controller;

import com.company.exhibitions.web.controller.impl.*;

public interface IControllerFactory {

    Controller getControllerChain();
}
