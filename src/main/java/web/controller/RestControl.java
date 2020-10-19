package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.User;
import web.repository.UserDao;



@RestController
public class RestControl {

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findOne(Long id) {
        User user = userDao.findById(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
