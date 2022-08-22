package com.example.pathfinderproject.service;

import com.example.pathfinderproject.model.service.RouteServiceModel;
import com.example.pathfinderproject.model.view.RouteDetailsVM;
import com.example.pathfinderproject.model.view.RouteVM;

import java.util.List;

public interface RouteService {
    List<RouteVM> findAllRoutesView();

    void addNewRoute(RouteServiceModel routeServiceModel);

    RouteDetailsVM findRouteById(Long id);
}
