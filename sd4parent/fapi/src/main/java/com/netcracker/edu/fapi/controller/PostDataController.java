package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.PostViewModel;
import com.netcracker.edu.fapi.service.PostDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/p")
public class PostDataController {

    @Autowired
    private PostDataService postDataService;

    @RequestMapping
    public ResponseEntity<List<PostViewModel>> getAllPosts() {
        return ResponseEntity.ok(postDataService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostViewModel> getPostById(@PathVariable String id) {
        return ResponseEntity.ok(postDataService.getPostById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PostViewModel> savePost(@RequestBody PostViewModel post /*todo server validation*/) {
        if (post != null) {
            return ResponseEntity.ok(postDataService.savePost(post));
        }
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable String id) {
        postDataService.deletePost(Long.valueOf(id));
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<List<PostViewModel>> getPostsByLogin(@RequestParam("login") String login) {
        return ResponseEntity.ok(postDataService.getPostsByLogin(login));
    }

    @RequestMapping(value = "/page/{num}", method = RequestMethod.GET)
    public ResponseEntity<List<PostViewModel>> getPostsByPage(@PathVariable(name = "num") int num, @RequestParam("qt") int quantity) {
        return ResponseEntity.ok(postDataService.getPostsByPage(num, quantity));
    }

    @RequestMapping(value = "/total-pages", method = RequestMethod.GET)
    public int getTotalPages(@RequestParam("qt") int quantity) {
        return postDataService.getTotalPages(quantity);
    }

}
