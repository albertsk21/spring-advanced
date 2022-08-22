package com.example.pathfinderproject.web;

import com.example.pathfinderproject.model.binding.UserLoginBM;
import com.example.pathfinderproject.model.binding.UserRegisterBM;
import com.example.pathfinderproject.model.service.UserServiceModel;
import com.example.pathfinderproject.model.view.UserVM;
import com.example.pathfinderproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute
    public UserRegisterBM userRegisterBM(){
        return new UserRegisterBM();
    }

    @GetMapping("/register")
    public String registerGET(Model model){
        return "register";
    }

    @PostMapping("/register")
    public String registerPOST(@Valid UserRegisterBM userRegisterBM,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors() ||  !userRegisterBM.getPassword().equals(userRegisterBM.getConfirmPassword()))
        {
            redirectAttributes
                    .addFlashAttribute("userRegisterBM", userRegisterBM);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBM", bindingResult);

            return "redirect:register";
        }

        userService.registerUser(modelMapper
                .map(userRegisterBM, UserServiceModel.class));
        return "redirect:login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/";
    }

    @GetMapping("/profile/{id}")
    private String profile(@PathVariable Long id, Model model)
    {
        model.addAttribute("user", modelMapper
                .map(userService.findById(id), UserVM.class));
        return "profile";
    }
}
