package blog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
public class UserController {

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserDTO userDTO) {
        // username, password validation
        if (!isValidUsername(userDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username");
        }
        if (!isValidPassword(userDTO.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid password");
        }

        if (isUsernameTaken(userDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already taken");
        }

        saveUserToDatabase(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    private boolean isValidUsername(String username) {

        String regex = "^[a-z0-9]{4,10}$";
        return Pattern.matches(regex, username);
    }

    private boolean isValidPassword(String password) {


        String regex = "^[a-zA-Z0-9]{8,15}$";
        return Pattern.matches(regex, password);
    }

    private boolean isUsernameTaken(String username) {

        User existingUser = userRepository.findByUsername(username);
        return existingUser != null;
    }

    private void saveUserToDatabase(UserDTO userDTO) {

    }

    //로그인

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        // Check if user exists in the database
        if (!doesUserExist(userDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username");
        }

        // Validate user's password
        if (!isPasswordValid(userDTO.getUsername(), userDTO.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        // Generate and return JWT token
        String token = generateToken(userDTO.getUsername());
        return ResponseEntity.status(HttpStatus.OK).header("Authorization", "Bearer " + token).body("Login successful");
    }

    private boolean doesUserExist(String username) {

    }

    private boolean isPasswordValid(String username, String password) {

    }

    private String generateToken(String username) {

    }
}
