package com.thirdSemester.services;
import com.thirdSemester.domain.PlainObjects.UserPojo;
import com.thirdSemester.domain.User;
import com.thirdSemester.exceptions.CustomEmptyDataException;
import com.thirdSemester.repositories.UserRepository;
import com.thirdSemester.services.interfaces.IUserService;
import com.thirdSemester.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private final Converter converter;
    private final UserRepository userRepository;

    @Autowired
    public UserService(Converter converter,
                       UserRepository userRepository) {
        this.converter = converter;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserPojo createUser(User user) {

        userRepository.save(user);

        return converter.userToPojo(user);
    }

    @Override
    @Transactional
    public UserPojo findUserByEmailAndPassword(String email, String password){
        Optional<User> userOptional = userRepository.findByEmailAndPassword(email, password);
        if(userOptional.isPresent()){
            return converter.userToPojo(userOptional.get());
        } else
            return null;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional (readOnly = true)
    public UserPojo getUser(long id) {

        Optional<User> foundUserOptional = userRepository.findById(id);

        if (foundUserOptional.isPresent()) {
            return converter.userToPojo(foundUserOptional.get());
        } else {
            return converter.userToPojo(new User());
        }
    }

    @Override
    @org.springframework.transaction.annotation.Transactional (readOnly = true)
    public List<UserPojo> getAllUsers() {
        List<User> listOfUsers = userRepository.findAll();
        if (!listOfUsers.isEmpty()) {
            return listOfUsers.stream().map(user -> converter.userToPojo(user)).collect(Collectors.toList());
        } else throw new EmptyResultDataAccessException("at least",1);

    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public UserPojo updateUser(User source, long id) {
        Optional<User> userForUpdateOptional = userRepository.findById(id);
        if (userForUpdateOptional.isPresent()) {
            User target = userForUpdateOptional.get();
            target.setEmail(source.getEmail());
            target.setPassword(source.getPassword());
            userRepository.save(target);
            return converter.userToPojo(target);
        } else {
            throw new CustomEmptyDataException("unable to update user");
        }
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public String deleteUser(long id) {
        Optional<User> userForDeleteOptional = userRepository.findById(id);

        if (userForDeleteOptional.isPresent()) {
            userRepository.delete(userForDeleteOptional.get());
            return "User with id:" + id + " was successfully remover";
        } else {
            throw new CustomEmptyDataException("unable to delete user");
        }
    }
}
