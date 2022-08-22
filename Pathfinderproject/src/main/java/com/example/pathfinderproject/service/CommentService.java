package com.example.pathfinderproject.service;

import com.example.pathfinderproject.model.service.CommentServiceModel;
import com.example.pathfinderproject.model.view.CommentVM;

import java.util.List;

public interface CommentService {
    List<CommentVM> getComments(Long routeId);
    CommentVM CreateComment(CommentServiceModel commentServiceModel);
}
