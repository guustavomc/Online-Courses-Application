package org.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private FindCourse service;

    public CourseController(FindCourse service){
        this.service=service;
    }

    @GetMapping
    public List<Course> getCourses(){
        return service.findAllCourses();
    }
}
