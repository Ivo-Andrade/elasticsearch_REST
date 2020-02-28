package test.elasticsearch_rest.demo.controller;

import java.util.List;

import javax.validation.Valid;

import br.com.looplex.ElasticsearchSecurityDAOService;
import br.com.looplex.models.ElasticsearchUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.looplex.data_access_object.DataAccessObjectService;
import test.elasticsearch_rest.demo.model.*;
import test.elasticsearch_rest.demo.aspects.annotations.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

//    @Autowired @Qualifier("http-final")
//    UserDao userRepo;

    @Autowired
    ElasticsearchSecurityDAOService userDaoService;

//    public UserController() {

//        this.userDaoService = new DataAccessObjectService(userRepo);

//    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody @Valid ElasticsearchUser newInstance) {

        userDaoService.create(newInstance);

        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("User successfully created!"));

    }

    @GetMapping("")
    public ResponseEntity listAllUsers(){

        List<User> users = (List<User>)(List<?>) userDaoService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse(users.size() + " users found!", users));

    }

    @GetMapping("/id/{id}")
    public ResponseEntity findUser(@PathVariable(value="id") String id) {

        ElasticsearchUser user = (ElasticsearchUser) userDaoService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("User found!", user));

    }

    @PutMapping("/update/{id}")
    @IdEqualsSentObjectIdAdvice
    public ResponseEntity updateUser(@PathVariable(value="id") String id, @RequestBody @Valid ElasticsearchUser transientObject) {

        ElasticsearchUser updatedUser = (ElasticsearchUser) userDaoService.update(transientObject);

        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("User successfully updated!", updatedUser));
            
    }

    @DeleteMapping("/delete/{id}")
    @SentObjectMatchesIdObjectAdvice
    public ResponseEntity deleteUser(@PathVariable(value="id") String id, @RequestBody @Valid ElasticsearchUser persistentObject) {

        userDaoService.delete(persistentObject);

        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("User successfully deleted!"));

    }

}