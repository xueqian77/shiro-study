package cn.x.study.service;


import cn.x.study.entity.User;
import cn.x.study.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
   private UserMapper userMapper;

    public User getUserByName(String getMapByName) {
        return getMapByName(getMapByName);
    }

    private User getMapByName(String userName) {
        User user =new User();
        user.setUserName(userName);

        return userMapper.selectUser(user);
    }
}
