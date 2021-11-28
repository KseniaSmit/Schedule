package com.mirea.schedule.controllers;

import com.mirea.schedule.models.User;
import com.mirea.schedule.models.Record;
import com.mirea.schedule.services.RecordService;
import com.mirea.schedule.services.UserService;
import com.mirea.schedule.services.WeekDayService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final RecordService recordService;
    private final UserService userService;
    private final WeekDayService weekDayService;

    public UserController(RecordService recordService, UserService userService,
                          WeekDayService weekDayService){
        this.recordService = recordService;
        this.userService = userService;
        this.weekDayService = weekDayService;

    }

    private String getUserRole(Authentication authentication) {
        if (authentication == null)
            return "GUEST";
        else
            return ((User) userService.loadUserByUsername(authentication.getName())).getRole();
    }
    private int getUserId(Authentication authentication) {
        if (authentication == null)
            return 0;
        else
            return ((User) userService.loadUserByUsername(authentication.getName())).getId();
    }

    @GetMapping
    public String index(Model model, Authentication authentication){
        String userRole = getUserRole(authentication);
        model.addAttribute("userRole", userRole);
        model.addAttribute("days", weekDayService.getAllWeekDays());
        return "/index";

    }

    @GetMapping("/sign")
    public String sign() {
        return "/registration";
    }
    @PostMapping("/sign")
    public String signCreate(HttpServletRequest request,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "username") String username,
                             @RequestParam(name = "password") String password,
                             Model model) {
        if (userService.loadUserByUsername(username) != null) {
            model.addAttribute("Status", "user_exists");
            return "/registration";
        }
        else {
            userService.create(email,username,password);
            authWithHttpServletRequest(request, username, password);
            return "redirect:/";
        }
    }
    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) { }
    }

    @GetMapping("/wdays")
    public String products(@RequestParam(name = "typeId", required = false) Integer typeId,

                           Model model, Authentication authentication){
        String userRole = getUserRole(authentication);
        if (typeId == null) typeId = 0;
        int userId = getUserId(authentication);
        model.addAttribute("userRole", userRole);
        model.addAttribute("days", weekDayService.getAllWeekDays());
        model.addAttribute("records", recordService.getAllByTypeIdAndUserId(typeId, userId));
        //List<Record> records = recordService.getAllByTypeIdAndUserId(typeId, userId);
        //model.addAttribute("records", records);
        model.addAttribute("typeId",typeId);
        return "/wdays";
    }

    @PostMapping("/wdays/create")
    public String CreateRecord(@RequestParam(name = "typeId", required = false) Integer typeId,
                               @RequestParam(name = "name") String name, Authentication authentication){
        Record newRecord = new Record();
        int userId = getUserId(authentication);
        newRecord.setTypeId(typeId);
        newRecord.setUserId(userId);
        newRecord.setName(name);
        recordService.saveRecord(newRecord);
        return "redirect:/wdays";
    }

    @PostMapping("/wdays/delete")
    public String deleteRecord(@RequestParam(name = "id", required = false) Integer id){
        recordService.deleteById(id);
        return "redirect:/wdays";
    }


}
