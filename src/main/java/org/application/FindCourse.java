package org.application;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindCourse {

    private List<Course> courseList;
    @PostConstruct
    public void findCourses(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ClassPathResource resource = new ClassPathResource("combined_dataset.json");
            JsonNode rootNode = objectMapper.readTree(resource.getInputStream());

            for (JsonNode courseNode:rootNode){
                String name = courseNode.get("course_name").asText();
                String organization = courseNode.get("organization").asText();
                List<String> skills = new ArrayList<>();
                for(JsonNode skillNode:rootNode.get("skills")){
                    skills.add(skillNode.asText());
                }
                String level= courseNode.get("level").asText();
                courseList.add(new Course(name, organization, skills, level));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Course> findAllCourses(){
        return courseList;
    }
}
