package com.company.exhibitions.web.controller.impl;

import com.company.exhibitions.web.command.enums.UsersCommand;
import com.company.exhibitions.web.command.Command;
import com.company.exhibitions.web.command.usercommand.impl.*;
import com.company.exhibitions.web.controller.Controller;

public class UserController extends Controller {

    private final InsertUserCommand insertUserCommand;
    private final UpdateUserCommand updateUserCommand;
    private final DeleteUserCommand deleteUserCommand;
    private final FindAllUsersCommand findAllUsersCommand;
    private final FindUserByIdCommand findUserByIdCommand;
    private final FindUsersByRoleIdCommand findUsersByRoleIdCommand;

    public UserController(){
        this.insertUserCommand = new InsertUserCommand();
        this.updateUserCommand = new UpdateUserCommand();
        this.deleteUserCommand = new DeleteUserCommand();
        this.findAllUsersCommand = new FindAllUsersCommand();
        this.findUserByIdCommand = new FindUserByIdCommand();
        this.findUsersByRoleIdCommand = new FindUsersByRoleIdCommand();
    }

    @Override
    public Command defineCommand(String command) {
        switch(UsersCommand.valueOf(command.toUpperCase())){
            case INSERT_USER:
                return insertUserCommand;
            case UPDATE_USER:
                return updateUserCommand;
            case DELETE_USER:
                return deleteUserCommand;
            case FIND_ALL:
                return findAllUsersCommand;
            case FIND_USER_BY_ID:
                return findUserByIdCommand;
            case FIND_USERS_BY_ROLE_ID:
                return findUsersByRoleIdCommand;
        }
        return defineNextCommand(command);
    }
}
