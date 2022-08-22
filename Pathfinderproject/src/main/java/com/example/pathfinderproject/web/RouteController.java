package com.example.pathfinderproject.web;

import com.example.pathfinderproject.model.binding.CommentCreateBM;
import com.example.pathfinderproject.model.binding.RouteCreateBM;
import com.example.pathfinderproject.model.service.CommentServiceModel;
import com.example.pathfinderproject.model.service.RouteServiceModel;
import com.example.pathfinderproject.model.view.CommentVM;
import com.example.pathfinderproject.service.CommentService;
import com.example.pathfinderproject.service.RouteService;
import com.example.pathfinderproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final CommentService commentService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public RouteController(RouteService routeService, CommentService commentService,  UserService userService, ModelMapper modelMapper)
    {
        this.userService = userService;
        this.commentService = commentService;
        this.routeService = routeService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public String allRoutes(Model model)
    {
        model.addAttribute("routes", routeService.findAllRoutesView());
        return "routes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model)
    {
        model.addAttribute("route", routeService.findRouteById(id));
        return "route-details";
    }

    @PostMapping("/details/{id}")
    public String detailsPost(@AuthenticationPrincipal UserDetails principal,
                              @PathVariable Long routeId,
                              @RequestBody @Valid CommentCreateBM commentCreateBM)
    {
        CommentServiceModel commentSM = modelMapper.map(commentCreateBM, CommentServiceModel.class);
        commentSM.setId(routeId);
        CommentVM commentVM = commentService.CreateComment(commentSM);
        URI locationOfComment = URI.create(String.format("/api/comments/%s", routeId, commentVM.getId()));
        return "redirect:/details/{id}";
    }

    @ModelAttribute
    public RouteCreateBM routeCreateBM() {
        return new RouteCreateBM();
    }

    @GetMapping("/add")
    public String addGET()
    {
        if(!userService.isLogged())
        {
            return "redirect:/users/login";
        }

        return "add-route";
    }

    @PostMapping("/add")
    public String addPOST(@Valid RouteCreateBM routeCreateBM, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("routeCreateBM", routeCreateBM);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.routeCreateBM", bindingResult);

            return "redirect:add";
        }

        RouteServiceModel routeServiceModel = modelMapper.map(routeCreateBM, RouteServiceModel.class);
        routeServiceModel.setGpxCoordinates(new String(routeCreateBM.getGpxCoordinates().getBytes()));

        routeService.addNewRoute(routeServiceModel);

        return "redirect:/";
    }


}