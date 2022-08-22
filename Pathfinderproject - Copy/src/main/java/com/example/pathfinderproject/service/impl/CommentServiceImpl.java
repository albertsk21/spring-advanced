package com.example.pathfinderproject.service.impl;

import com.example.pathfinderproject.model.entity.Comment;
import com.example.pathfinderproject.model.service.CommentServiceModel;
import com.example.pathfinderproject.model.view.CommentVM;
import com.example.pathfinderproject.repository.RouteRepository;
import com.example.pathfinderproject.service.CommentService;

import com.example.pathfinderproject.util.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final RouteRepository routeRepository;

    public CommentServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public List<CommentVM> getComments(Long routeId) {
        var routeOpt= routeRepository.findById(routeId);

        if(routeOpt.isEmpty()) {
            throw new ObjectNotFoundException("Route with id " + routeId + " was not found!");
        }

        return routeOpt.get().getComments().stream().map(this::mapAsComment).collect(Collectors.toList());
    }

    @Override
    public CommentVM CreateComment(CommentServiceModel commentServiceModel) {
        throw new UnsupportedOperationException("TO DO:");
    }

    private CommentVM mapAsComment(Comment comment) {
        CommentVM commentVM = new CommentVM();
        commentVM.setId(comment.getId());
        commentVM.setCreated(comment.getCreated());
        commentVM.setAuthor(comment.getAuthor().getFullName());
        commentVM.setCanApprove(true);
        commentVM.setCanDelete(true);
        commentVM.setTextContent(comment.getTextContent());

        return commentVM;
    }
}
