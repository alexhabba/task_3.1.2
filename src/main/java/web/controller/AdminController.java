package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.repository.RoleDao;
import web.repository.UserDao;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/{page}")
    public String adminPage(ModelMap modelMap,
                            Authentication authentication, @PathVariable(required = false) Integer page) {
        User user = (User) authentication.getPrincipal();
        Map<String, Object> map = new HashMap<>();
        map.put("users", userDao.findAll(PageRequest.of(page, 4)));
        map.put("user", user);
        map.put("roles", user.getRoles());
        map.put("newUser", new User());
        map.put("currentPage", page);
        modelMap.addAllAttributes(map);
        return "admin";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @PostMapping("/add")
    public String registerNewUser(@ModelAttribute User user) {
        if (userDao.findByEmail(user.getEmail()) == null) {
            if (user.getPassword().equals(user.getPasswordRepeat())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setRoles(Collections.singletonList(roleDao.findById(1L).get()));
                userDao.save(user);
            }
        }
        return "redirect:/admin";
    }


//
    @PostMapping("/edit/{page}")
    public String edit(@ModelAttribute User user, @PathVariable Integer page) {
        userDao.save(user);
        return "redirect:/admin/" + page;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpServletRequest request) {
        userDao.deleteById(id);
        return "redirect:/admin/" + 0;
    }


}
