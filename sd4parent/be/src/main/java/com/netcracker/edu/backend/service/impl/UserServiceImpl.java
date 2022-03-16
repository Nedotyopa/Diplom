package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.DebtModel;
import com.netcracker.edu.backend.entity.LoginStringModel;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.CommentRepository;
import com.netcracker.edu.backend.repository.WalletRepository;
import com.netcracker.edu.backend.service.UserService;
import com.netcracker.edu.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static com.netcracker.edu.backend.repository.specification.UserSpecification.userFindByLogin;

@Component
public class UserServiceImpl implements UserService {

    private UserRepository repository;
    private WalletRepository walletRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User saveUser(User account)
    {
        if(account.getRole() == null)
            account.setRole("USER");

        if(account.getLogin() == null)
            account.setLogin(account.getEmail());

        return repository.save(account);
    }

    @Override
    public Optional<User> getUserById(Long id)
    {
        return repository.findById(id);
    }

    @Override
    public Iterable<User> getAllUsers()
    {
        return repository.findAll();
    }

    @Override
    public void deleteUser(Long id)
    {
//        commentRepository.changeAuthor(id);

        repository.deleteById(id);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return repository.findOne(userFindByLogin(login));
    }

    @Override
    public void banUser(Long id) {
        repository.banUser(id);
    }

    @Override
    public void checkUser(String login) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        repository.checkUser(login, date);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserByResetToken(String resetToken) {
        return repository.findByResetToken(resetToken);
    }

    @Override
    public void updateToken(LoginStringModel resetToken) {
        repository.updateToken(resetToken.getLogin(), resetToken.getStringVariable());
    }

    @Override
    public void updatePassword(LoginStringModel password) {
        repository.updatePassword(password.getLogin(), password.getStringVariable());
    }

    @Override
    public void updateDebt(DebtModel debt) {
        repository.updateDebt(debt.getDebt(), debt.getId());
    }

}
