package com.mrm.Drive_Backend.Service;

import com.mrm.Drive_Backend.Entity.File;
import com.mrm.Drive_Backend.Repository.FileRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FIleStorageService {

    @Value("${file.upload-dir}")
    private String fileUploadDir;

    private final FileRepo fileRepo;

    public FIleStorageService(FileRepo fileRepo) {
        this.fileRepo = fileRepo;
    }

    public String saveFile(MultipartFile file, Long parentFolderId) throws IOException {
        Path uploadPath = Paths.get(fileUploadDir);
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);


        //meta data for database
        File f = new File();
        f.setName(fileName);
        f.setPath(filePath.toString());
        f.setSize(file.getSize());
        f.setType("file");
        f.setCreatedAt(LocalDateTime.now());
        f.setParentFolderId(parentFolderId);

        fileRepo.save(f);

        return "File uploaded successfully";

    }

    public List<File> getFilesInFolder(Long parentFolderId){
        if(parentFolderId==null){
            return fileRepo.findAll()
                    .stream()
                    .filter(f->f.getParentFolderId()==null)
                    .collect(Collectors.toList());
        }
        else{
            return fileRepo.findAll()
                    .stream()
                    .filter(f->f.getParentFolderId().equals(parentFolderId))
                    .collect(Collectors.toList());
        }
    }

    public File getFileById(Long id){
        return fileRepo.findById(id).orElseThrow(()->new RuntimeException("File not found exception"));
    }

    public void deleteById(Long id){
        fileRepo.deleteById(id);
    }
}
