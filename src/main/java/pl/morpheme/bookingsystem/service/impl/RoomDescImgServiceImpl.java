package pl.morpheme.bookingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.morpheme.bookingsystem.dao.RoomDescImgDao;
import pl.morpheme.bookingsystem.domain.RoomDescImg;
import pl.morpheme.bookingsystem.service.RoomDescImgService;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by sylwek on 2016-06-20.
 */
@Service
@Transactional(rollbackFor = IOException.class)
@PropertySource(value = { "classpath:application.properties" })
public class RoomDescImgServiceImpl implements RoomDescImgService {

    @Autowired
    RoomDescImgDao dao;
    @Autowired
    private Environment environment;

    public RoomDescImg findById(int id) {
        return dao.findById(id);
    }

    public RoomDescImg findByImgUrl(String url) {
        return dao.findByImgUrl(url);
    }

    public List<RoomDescImg> findByRoomType(String roomType) {
        return dao.findByRoomType(roomType);
    }

    public List<RoomDescImg> findAll() {
        return dao.findAll();
    }

    /**
     * Metoda najpierw zapisuje obiekt "roomDescImg" w bazie danych, tylko po to aby uzyskać id.
     * Id posłuży następnie do nazawania pliku wędrującego do filesystemu i ponwonie trafi do bazy
     * danych uzupełniając kolumnę IMAGE_URL. Wszystko w obrębie jednej transakcji, która zostanie
     * przerwana i wycofana, jeżeli pojawi się ku temu powód (@Transactional(rollbackFor = IOException.class))
     * (Rozważałem, też czy nie zapisywać całych obrazów w bazie danych jako bloby, albo tablice
     * bajtów, ale doszedłem do wniosku że tak jest najciekawiej.)
     **/
    public void saveAndUploadImage(RoomDescImg roomDescImg, HttpServletRequest request) throws IOException {
            dao.save(roomDescImg);
            String uploadUrl = environment.getRequiredProperty("upload.location");
            BufferedImage src = ImageIO.read(new ByteArrayInputStream(roomDescImg.getImage().getBytes()));
            File destination = new File(uploadUrl + roomDescImg.getId() + ".jpg");
            ImageIO.write(src, "jpg", destination);
            roomDescImg.setImageUrl(uploadUrl + roomDescImg.getId() + ".jpg");
            dao.save(roomDescImg);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }

}
