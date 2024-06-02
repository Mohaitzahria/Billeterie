package ma.emsi.ticketing.services;


import jakarta.persistence.EntityNotFoundException;
import ma.emsi.ticketing.entities.User;
import ma.emsi.ticketing.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User getUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new EntityNotFoundException("User not found with email: " + email);
        }
        return user;
    }

    public User createUser(User user){
        User newUser = userRepository.save(user);
        userRepository.flush();
        return newUser;
}
}