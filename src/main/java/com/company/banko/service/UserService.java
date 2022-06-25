package com.company.banko.service;

import com.company.banko.domain.User;
import com.company.banko.model.UserDTO;
import com.company.banko.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {


    @Autowired
    private UserRepository userRepository;

/*    @Autowired
    private PasswordEncoder passwordEncoder;*/

    @Override
    public User registerNewUserAccount(final UserDTO accountDto) {
       /* if (emailExists(accountDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + accountDto.getEmail());
        }*/
        final User user = new User();

//        user.setFirstName(accountDto.getFirstName());
        user.setUserName(accountDto.getUserName());
        //user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
//        user.setEmail(accountDto.getEmail());
//        user.setUsing2FA(accountDto.isUsing2FA());
//        user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
        user.setRole(("ROLE_USER"));
        return userRepository.save(user);
    }

}
    /*private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    @CustomLog
    public List<User> findAll() {
        return userRepository.findAll().stream()
                .collect(Collectors.toCollection(LinkedList::new));

    }

    @CustomLog
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @CustomLog
    public UserDTO insert(UserDTO  userDTO){

        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return userDTO;
    }

    @CustomLog
    public UserDTO registerUser(UserDTO  userDTO){

        User user = new User();
        user.setUserName("admin");
        user.setPassword(passwordEncoder.encode("$2a$12$nQIeG4kCLhiOjogkatzau.LKfiUTAFHPHYNuAwtvtFYY7L/TJ1Ttm"));
        userRepository.save(user);
        return userDTO;
    }

    @CustomLog
    public UserDTO update(UserDTO  userDTO){

        User user = userRepository.findByUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        return userDTO;
    }

    @CustomLog
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @CustomLog
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
*/


