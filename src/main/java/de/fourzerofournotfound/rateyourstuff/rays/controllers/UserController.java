package de.fourzerofournotfound.rateyourstuff.rays.controllers;

import de.fourzerofournotfound.rateyourstuff.rays.models.Book;
import de.fourzerofournotfound.rateyourstuff.rays.models.Login;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.services.FileUploadService;
import de.fourzerofournotfound.rateyourstuff.rays.services.UserSecurityService;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.LoginRepository;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSecurityService uss;

    @Autowired
    FileUploadService fus;


    @GetMapping("/all")
    ResponseEntity<List<User>> getAllUsers() {return ResponseEntity.ok(this.userRepository.findAll());}

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id) throws UserNotFoundException
    {
        return ResponseEntity.ok(this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("No user found for id" + id)));
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path="/add", consumes = "application/json", produces = "application/json")
    ResponseEntity<User> addUser(@RequestBody User user)
    {
        uss.hashPasswordOfSignUp(user);
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    ResponseEntity<User> updateUser(@RequestBody User user)
    {
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    @PutMapping(path ="/changePassword", consumes = "application/json", produces = "application/json")
    ResponseEntity<User> changePassword(@RequestBody User user, String currentPassword, String newPassword)
    {
        uss.changePassword(currentPassword, newPassword, user);
        return ResponseEntity.ok(this.userRepository.save(user));
    }

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {this.userRepository.deleteById(id);}

    @PutMapping("/images/{id}")
    ResponseEntity<User> addImage(@RequestParam("image") MultipartFile multipartFile, @PathVariable Long id) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Optional<User> user = this.userRepository.findById(id);
        //check if the given movie exists
        if(user.isPresent()) {
            user.get().setProfilePicturePath(user.get().getId() + "/" + fileName);
            //define the target path
            String uploadDir = User.IMAGE_PATH_PREFIX + id.toString();
            //upload the file
            fus.saveFile(uploadDir, fileName, multipartFile);
            return ResponseEntity.ok(this.userRepository.save(user.get()));
        }
        return ResponseEntity.badRequest().build();
    }


    //TODO: Methods

    //TODO: Update User

    //TODO: Update Password
    //TODO: Reset Password


}
