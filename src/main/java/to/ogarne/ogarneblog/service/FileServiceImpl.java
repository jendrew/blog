package to.ogarne.ogarneblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import to.ogarne.ogarneblog.dao.FileDao;
import to.ogarne.ogarneblog.dao.StorageDao;
import to.ogarne.ogarneblog.model.File;
import to.ogarne.ogarneblog.service.tools.BufferedImageThumbnailer;

import java.io.IOException;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileDao fileDao;

    @Autowired
    StorageDao storageDao;

    @Autowired
    BufferedImageThumbnailer thumbnailer;

    @Override
    public File findById(Long id) {
        File myFile = fileDao.findById(id);
        return null;
    }

    @Override
    public Resource loadAsResource(Long id) {
        File file = fileDao.findById(id);
        return storageDao.loadAsResource(file.getFilename());
    }


    @Override
    public void save(File file) {

        try {
            // Create filename
            String filename = String.valueOf(new Date().getTime()) + "_" + StringUtils.cleanPath(file.getOriginalFilename());


            file.setFilename(filename);
            storageDao.store(file);

            if (file.getContentType().contains("image")) {
                thumbnailer.convertToThumbnail(file, 250);
                String thumbFilename = ("thumb_" + filename);
                file.setFilename(thumbFilename);
                storageDao.store(file);
            }


            file.setFilename(filename);
            fileDao.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void delete(File file) {

    }
}
