package com.roman.shilov.hw10.travelagency.user.service.impl;

import com.roman.shilov.hw10.travelagency.common.buisness.application.sequencecreator.SequenceCreator;
import com.roman.shilov.hw10.travelagency.user.domain.User;
import com.roman.shilov.hw10.travelagency.user.repo.UserRepo;
import com.roman.shilov.hw10.travelagency.user.search.UserSearchCondition;
import com.roman.shilov.hw10.travelagency.user.service.UserService;

import java.util.List;

public class UserDefaultService implements UserService {
    private final UserRepo repo;

    public UserDefaultService(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public void insert(User user) {
        repo.insert(user);
    }

    @Override
    public void update(User user) {
        repo.update(user);
    }


    @Override
    public User findById(Long id) {
        if(id != null){
            return repo.findById(id);
        }else {
            return null;
        }
    }

    @Override
    public void delete(User user) {
        if(user.getId() != null){
            repo.deleteById(user.getId());
        }
    }

    @Override
    public List<User> search(UserSearchCondition searchCondition) {
        return repo.search(searchCondition);
    }

    @Override
    public void deleteById(Long id) {
        if(id != null){
            repo.deleteById(id);
        }
    }

    @Override
    public void printAll() {
        repo.printAll();
    }
}
