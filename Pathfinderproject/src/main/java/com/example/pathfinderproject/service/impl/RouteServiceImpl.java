package com.example.pathfinderproject.service.impl;

import com.example.pathfinderproject.model.entity.Route;
import com.example.pathfinderproject.model.service.RouteServiceModel;
import com.example.pathfinderproject.model.view.RouteDetailsVM;
import com.example.pathfinderproject.model.view.RouteVM;
import com.example.pathfinderproject.repository.RouteRepository;
import com.example.pathfinderproject.service.CategoryService;
import com.example.pathfinderproject.service.RouteService;
import com.example.pathfinderproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;

    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }
    public List<RouteVM> findAllRoutesView()
    {
        return routeRepository.findAll().stream().map(r -> {
            RouteVM routeVM = modelMapper.map(r, RouteVM.class);
            if(r.getPictures().isEmpty())
            {
                routeVM.setPictureUrl("/images/pic4.jpg");
            }
            else
            {
                routeVM.setPictureUrl(r.getPictures().stream().findFirst().get().getUrl());
            }
            return routeVM;
        }).collect(Collectors.toList());
    }

    @Override
    public void addNewRoute(RouteServiceModel routeServiceModel) {
        Route route = modelMapper.map(routeServiceModel, Route.class);
        //route.setAuthor(userService.findUserEntity());
        route.setCategories(routeServiceModel
                .getCategories()
                .stream()
                .map(categoryService::findCategoryByName)
                .collect(Collectors.toSet())); // with reference method
        routeRepository.save(route);
    }

    @Override
    public RouteDetailsVM findRouteById(Long id) {
        return routeRepository.findById(id).map( r -> modelMapper.map(r, RouteDetailsVM.class)).orElse(null);
    }
}