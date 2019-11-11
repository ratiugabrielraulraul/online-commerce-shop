package org.fasttrackit.onlinecommerceshop.web;


import org.fasttrackit.onlinecommerceshop.domain.User;
import org.fasttrackit.onlinecommerceshop.service.UserService;
import org.fasttrackit.onlinecommerceshop.transfer.user.SaveUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/User")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User>createUser(@RequestBody @Valid SaveUserRequest request) throws ValidationException {
        User user = userService.createUser(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User>getUser(@PathVariable ("id") long id ){
        User user = userService.getUser(id);
        return  new ResponseEntity<>(user,HttpStatus.OK);

    }

}
