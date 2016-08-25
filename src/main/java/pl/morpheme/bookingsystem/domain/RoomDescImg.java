package pl.morpheme.bookingsystem.domain;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by sylwek on 2016-06-20.
 *
 * Obrazy przetrzymwyane będą w file-systemie, a do bazy danych trafi tylko url.
 * Przydał się ten @Transient.
 **/
@Entity
@Table(name = "ROOM_DESC_IMG")
public class RoomDescImg implements Serializable {

    @Transient
    private MultipartFile image;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ID")
    private Integer id;

    @Column(name="IMAGE_URL", unique=false, nullable=true)
    private String imageUrl;

    @NotEmpty
    @Column(name="DESCRIPTION", unique=false, nullable=true)
    private String description;

    @NotNull
    @Column(name="ROOM_TYPE", unique=false, nullable=false)
    private String roomType;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomDescImg image1 = (RoomDescImg) o;

        if (image != null ? !image.equals(image1.image) : image1.image != null) return false;
        if (id != null ? !id.equals(image1.id) : image1.id != null) return false;
        if (imageUrl != null ? !imageUrl.equals(image1.imageUrl) : image1.imageUrl != null) return false;
        if (description != null ? !description.equals(image1.description) : image1.description != null) return false;
        return roomType != null ? roomType.equals(image1.roomType) : image1.roomType == null;
    }

    @Override
    public int hashCode() {
        int result = image != null ? image.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Image{" +
                "image=" + image +
                ", id=" + id +
                ", imageUrl='" + imageUrl + '\'' +
                ", description='" + description + '\'' +
                ", roomType='" + roomType + '\'' +
                '}';
    }
}
