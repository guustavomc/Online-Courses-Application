package org.application;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindCourse {

    private List<Course> courseList = new ArrayList<>();;
    @PostConstruct
    public void findCourses(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ClassPathResource resource = new ClassPathResource("combined_dataset.json");
            JsonNode rootNode = objectMapper.readTree(resource.getInputStream());

            for (JsonNode courseNode:rootNode){
                String name = courseNode.get("course_name").asText().trim();


                List<String> organization = new ArrayList<>();
                JsonNode organizationsNode = courseNode.get("organization");
                if (organizationsNode != null) {
                    for (JsonNode organizatiosNode : organizationsNode) {
                        organization.add(organizatiosNode.asText().trim());
                    }
                }

                List<String> skills = new ArrayList<>();
                JsonNode skillsNode = courseNode.get("skills");
                if (skillsNode != null) {
                    for (JsonNode skillNode : skillsNode) {
                        skills.add(skillNode.asText().trim());
                    }
                }
                String level= courseNode.get("level").asText().trim();
                courseList.add(new Course(name, organization, skills, level));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading courses JSON", e);
        }
    }

    public List<Course> findAllCourses(){
        return courseList;
    }

    public Course findCourseWithName(String name){
        return courseList.stream().filter(course -> course.getName().equalsIgnoreCase(name.trim())).findFirst().orElse(null);
    }

    public List<Course> findCourseWithOrganization(String organization){
        return courseList.stream().filter(course -> course.getOrganization().stream().anyMatch( t -> t.trim().equalsIgnoreCase(organization))).collect(Collectors.toList());
    }

    public List<Course> findCourseWithSkill(String skill){
        return courseList.stream()
                .filter(course -> course.getSkills().stream()
                        .anyMatch( t -> t.trim().equalsIgnoreCase(skill)))
                .collect(Collectors.toList());
    }
}
