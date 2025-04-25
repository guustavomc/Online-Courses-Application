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

    @GetMapping("/name/{name}")
    public Course getCourseWithName(@PathVariable("name") String name){
        System.out.println(name);
        return service.findCourseWithName(name);
    }

    @GetMapping("/organization/{org}")
    public List<Course> getCourseWithOrganization(@PathVariable("org") String org){
        System.out.println(org);
        return service.findCourseWithOrganization(org);
    }

    @GetMapping("/skill/{skill}")
    public List<Course> getCourseWithSkill(@PathVariable("skill") String skill){
        System.out.println(skill);
        return service.findCourseWithSkill(skill);
    }


}
