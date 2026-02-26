package k26.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Kirjan nimi ei voi olla tyhjä.")
    @Size(min = 3, max = 250)
    private String title;

    private String author;

    private int publicationYear;

   @JsonIgnoreProperties("books")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category; 



    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        
    }
    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    } 

    

    public Book(@NotEmpty(message = "Kirjan nimi ei voi olla tyhjä.") @Size(min = 3, max = 250) String title,
            String author, int publicationYear, Category category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.category = category;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
   

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear + "]";
    }




}
