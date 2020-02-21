package test.elasticsearch_rest.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.looplex.data_access_object.DataAccessObjectService;
import test.elasticsearch_rest.demo.dao.user.UserDao;
import test.elasticsearch_rest.demo.model.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

//    @Autowired @Qualifier("http-final")
//    UserDao userRepo;

    @Autowired
    DataAccessObjectService userDaoService;



    public UserController() {

//        this.userDaoService = new DataAccessObjectService(userRepo);
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createUser(@RequestBody @Valid User newInstance) {

        userDaoService.create(newInstance);
        
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("User successfully created!"));

    }

    @GetMapping("")
    public List<User> listAllUsers(){

        return (List<User>)(List<?>) userDaoService.findAll();

    }

    @GetMapping("/id/{id}")
    public User findUser(@PathVariable(value="id") String id) {

        return (User) userDaoService.findById(id);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<APIResponse> updateUser(@PathVariable(value="id") String id, @RequestBody @Valid User transientObject) {

        // TODO: Turn Id - Object check an aspect
        if(id.equals(transientObject.getUsername())) {

            userDaoService.update(transientObject);

            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("User successfully updated!"));

        } else 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("Id doesn't match payload!"));
            
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable(value="id") String id, @RequestBody @Valid User persistentObject) {

        if(id.equals(persistentObject.getUsername())) {

            userDaoService.delete(persistentObject);

            return ResponseEntity.status(HttpStatus.OK).body(new APIResponse("User successfully deleted!"));

        } else 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse("Id doesn't match payload!"));

    }

}