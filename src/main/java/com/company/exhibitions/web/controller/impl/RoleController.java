package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.command.enums.RolesCommand;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.role.*;
import com.company.exhibitions.web.controller.Controller;
import com.company.exhibitions.web.controller.validation.application.CommandExistenceValidator;
import org.apache.commons.lang3.EnumUtils;

public class RoleController extends Controller {

    private final InsertRoleCommand insertRoleCommand;
    private final UpdateRoleCommand updateRoleCommand;
    private final DeleteRoleCommand deleteRoleCommand;
    private final FindAllRolesCommand findAllRolesCommand;
    private final FindRoleByIdCommand findRoleByIdCommand;
    private final CommandExistenceValidator commandExistenceValidator;

    public RoleController(Controller controller){
        super(controller);
        this.insertRoleCommand = new InsertRoleCommand();
        this.updateRoleCommand = new UpdateRoleCommand();
        this.deleteRoleCommand = new DeleteRoleCommand();
        this.findAllRolesCommand = new FindAllRolesCommand();
        this.findRoleByIdCommand = new FindRoleByIdCommand();
        this.commandExistenceValidator = new CommandExistenceValidator();
    }

    @Override
    public Command defineCommand(String command) {
        if (commandExistenceValidator.checkCommandExistence(command)&&
                EnumUtils.isValidEnum(RolesCommand.class, command.toUpperCase())) {
            switch (RolesCommand.valueOf(command.toUpperCase())) {
                case INSERT_ROLE:
                    return insertRoleCommand;
                case UPDATE_ROLE:
                    return updateRoleCommand;
                case DELETE_ROLE:
                    return deleteRoleCommand;
                case FIND_ALL_ROLES:
                    return findAllRolesCommand;
                case FIND_ROLE_BY_ID:
                    return findRoleByIdCommand;
            }
        }
        return defineNextCommand(command);
    }
}
