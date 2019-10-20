package com.santatostada.restservice.controller;

import com.santatostada.restservice.connectors.SOAPConnector;
import com.santatostada.restservice.model.GetUser;
import com.santatostada.restservice.model.UserException;
import com.santatostada.restservice.model.AddUserStatus;
import localhost._1050.users.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;



@RestController
@Validated
public class AdapterController {

    private SOAPConnector soapConnector;

    @Autowired
    public void setSoapConnector(SOAPConnector soapConnector) {
        this.soapConnector = soapConnector;
    }

    @RequestMapping("/addUser")
    public AddUserStatus addUser(@Valid @NotBlank @RequestParam String id, @Valid @NotBlank @RequestParam String name) {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setId(id);
        addUserRequest.setName(name);
        AddUserResponse addUserResponse = (AddUserResponse) soapConnector.callWebService("http://localhost:1050/ws", addUserRequest);
        return new AddUserStatus(addUserResponse.getIsAdded());
    }

    @RequestMapping("/deleteUser")
    public AddUserStatus deleteUser(@Valid @NotBlank @RequestParam String id) {
        DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
        deleteUserRequest.setId(id);
        DeleteUserResponse deleteUserResponse = (DeleteUserResponse) soapConnector.callWebService("http://localhost:1050/ws", deleteUserRequest);
        return new AddUserStatus(deleteUserResponse.getIsDeleted());
    }

    @RequestMapping("/getUser")
    public GetUser getUser(@Valid @NotBlank @RequestParam String id) {
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setId(id);
        GetUserResponse getUserResponse = (GetUserResponse) soapConnector.callWebService("http://localhost:1050/ws", getUserRequest);
        return new GetUser(getUserResponse.getId(), getUserResponse.getName());
    }

    @ExceptionHandler(Exception.class)
    public UserException userException(Exception ex) {
        UserException userException = new UserException();
        userException.setCause(ex.getMessage());
        return userException;
    }


}
