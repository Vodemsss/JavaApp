package com.thirdSemester.services.interfaces;

import com.thirdSemester.domain.PlainObjects.UserPojo;
import com.thirdSemester.domain.User;

import java.util.List;

public interface IUserService {
    UserPojo createUser(User user);
    UserPojo getUser(long id);
    List<UserPojo> getAllUsers();
    UserPojo updateUser(User user, long id);
    String deleteUser(long id);
    UserPojo findUserByEmailAndPassword(String email, String password);

}
