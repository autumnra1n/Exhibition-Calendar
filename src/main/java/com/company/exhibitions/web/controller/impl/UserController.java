package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.command.enums.UsersCommand;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.user.*;
import com.company.exhibitions.web.controller.Controller;
import com.company.exhibitions.web.controller.validation.application.CommandExistenceValidator;
import org.apache.commons.lang3.EnumUtils;

public class UserController extends Controller {

    private final InsertUserCommand insertUserCommand;
    private final UpdateUserCommand updateUserCommand;
    private final DeleteUserCommand deleteUserCommand;
    private final FindAllUsersCommand findAllUsersCommand;
    private final FindUserByIdCommand findUserByIdCommand;
    private final FindUsersByRoleIdCommand findUsersByRoleIdCommand;
    private final CommandExistenceValidator commandExistenceValidator;

    public UserController(Controller controller){
        super(controller);
        this.insertUserCommand = new InsertUserCommand();
        this.updateUserCommand = new UpdateUserCommand();
        this.deleteUserCommand = new DeleteUserCommand();
        this.findAllUsersCommand = new FindAllUsersCommand();
        this.findUserByIdCommand = new FindUserByIdCommand();
        this.findUsersByRoleIdCommand = new FindUsersByRoleIdCommand();
        this.commandExistenceValidator = new CommandExistenceValidator();
    }

    @Override
    public Command defineCommand(String command) {
        if (commandExistenceValidator.checkCommandExistence(command)&&
                EnumUtils.isValidEnum(UsersCommand.class, command.toUpperCase())) {
            switch (UsersCommand.valueOf(command.toUpperCase())) {
                case INSERT_USER:
                    return insertUserCommand;
                case UPDATE_USER:
                    return updateUserCommand;
                case DELETE_USER:
                    return deleteUserCommand;
                case FIND_ALL_USERS:
                    return findAllUsersCommand;
                case FIND_USER_BY_ID:
                    return findUserByIdCommand;
                case FIND_USERS_BY_ROLE_ID:
                    return findUsersByRoleIdCommand;
            }
        }
        return defineNextCommand(command);
    }
}
