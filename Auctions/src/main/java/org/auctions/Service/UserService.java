package org.auctions.Service;


import org.auctions.Model.User;

public interface UserService {
    public User findUserByEmail(String email);
    public void createUserAccount(User user);
}
