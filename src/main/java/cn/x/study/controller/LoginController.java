package cn.x.study.controller;

import cn.x.study.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class LoginController {

    //登录
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            return "请输入用户名和密码！";
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            throw e;
        }
        return "login success";
    }

    //只允许有update权限的用户访问
    @RequiresRoles("update")
    @GetMapping("/update")
    public String update() {
        return "update success!";
    }

    //只允许有select权限的用户访问
    @RequiresPermissions("select")
    @GetMapping("/select")
    public String select() {
        return "select success!";
    }

    //只允许有delete权限的用户访问
    @RequiresPermissions("delete")
    @GetMapping("/delete")
    public String delete() {
        return "delete success!";
    }

    //只允许有add权限的用户访问
    @RequiresPermissions("add")
    @GetMapping("/add")
    public String add() {
        return "add success!";
    }

    //只允许有admin角色的用户访问
    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin() {
        return "admin success!";
    }

    //只要登录，谁都可以访问（如果想不用登录就访问请在ShiroConfig.shiroFilterFactoryBean中配置anon）
    @GetMapping("/user")
    public String user() {
        return "user success!";
    }


}
