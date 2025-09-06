package id.languagelearning.lms_backend.courses;

import java.time.Instant;
import java.util.List;

public class Courses {
    private String id;
    private String title;
    private String description;
    private String level;
    private String language;
    private Double price;
    private Integer durationHours;
    private List<String> tags;
    private boolean published = false;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();

    //    getters/setter
    public String getId() {return id;}
    public String getTitle() {return title;}
    public String getDescription() {return this.description;}
    public String getLevel() {return this.level;}
    public String getLanguage() {return this.language;}
    public Double getPrice() {return this.price;}
    public Integer getDurationHours() {return this.durationHours;}
    public boolean isPublished() {return published;}
    public Instant getCreatedAt() {return createdAt;}
    public Instant getUpdatedAt() {return updatedAt;}

    public void setId(String id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description = description;}
    public void setLevel(String level) {this.level = level;}
    public void setLanguage(String language) {this.language = language;}
    public void setPrice(Double price) {this.price = price;}
    public void setDurationHours(Integer durationHours) {this.durationHours = durationHours;}
    public void setTags(List<String> tags) {this.tags = tags;}
    public void setPublished(boolean published) {this.published = published;}
    public void setCreatedAt(Instant createdAt) {this.createdAt = createdAt;}
    public void setUpdatedAt(Instant updatedAt) {this.updatedAt = updatedAt;}
}