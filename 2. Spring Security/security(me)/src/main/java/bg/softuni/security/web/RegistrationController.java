package bg.softuni.security.web;

import bg.softuni.security.model.dto.UserRegistrationDTO;
import bg.softuni.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
  private UserService userService;

  public RegistrationController(UserService userService) {
    this.userService = userService;
  }


  @ModelAttribute("userRegisterDTO")
  public UserRegistrationDTO userRegistrationDTO(){
    return new UserRegistrationDTO();
  }

  @GetMapping("/users/register")
  public String register() {
    return "auth-register";
  }

  @PostMapping("/users/register")
  public String registerPOST(UserRegistrationDTO userRegistrationDTO) {


    this.userService.createAccount(userRegistrationDTO);
    return "redirect:/";
  }


}
