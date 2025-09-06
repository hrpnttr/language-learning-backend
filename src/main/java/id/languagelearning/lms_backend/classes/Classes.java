package id.languagelearning.lms_backend.classes;

import org.bson.types.ObjectId;

import java.time.Instant;
import java.util.List;

public class Classes {
    private String id;
    private String course_id;
    private String class_name;
    private String description;
    private List<id.languagelearning.lms_backend.classes.Materials> materials;

    //    getters
    public String getId() {return id;}
    public String getCourse_id() {return course_id;}
    public String getClass_name() {return this.class_name;}
    public String getDescription() {return this.description;}
    public List<id.languagelearning.lms_backend.classes.Materials> getMaterials () {return this.materials;}
}

class Materials {
    private String type;
    private String fileName;
    private String title;

    public String getType() {return type;}
    public String getFileName() {return fileName;}
    public String getTitle() {return title;}
}