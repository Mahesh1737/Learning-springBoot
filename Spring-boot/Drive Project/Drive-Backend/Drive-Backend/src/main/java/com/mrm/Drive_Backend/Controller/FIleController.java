package com.mrm.Drive_Backend.Controller;

import com.mrm.Drive_Backend.Entity.File;
import com.mrm.Drive_Backend.Service.FIleStorageService;
import jakarta.annotation.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "http://localhost:3000")
public class FIleController {

    private final FIleStorageService fIleStorageService;


    public FIleController(FIleStorageService fIleStorageService) {
        this.fIleStorageService = fIleStorageService;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file,
                                             @RequestParam(value="parentFolderId", required = false) Long parentFolderId){
        try{
            String response = fIleStorageService.saveFile(file, parentFolderId);
            return ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(500).body("File upload failed!");
        }
    }



    @GetMapping("/download/{id}")
    public ResponseEntity<UrlResource> downloadFile(@PathVariable Long id){
        try{
            //mysql meta deta
            File file = fIleStorageService.getFileById(id);
            Path path = Paths.get(file.getPath());
            UrlResource resource = new UrlResource(path.toUri());
            return ResponseEntity.ok()
                    .header("content-Disposition", "attachment; filename=\""+file.getName()+"\"")
                            .body(resource);
        }catch(Exception e){
            return ResponseEntity.status(404).build();

        }
    }


    @GetMapping("/list")
    public ResponseEntity<List<File>> listFiles(@RequestParam(value = "parentFolderId", required = false) Long parentFolderId){
        List<File> files;
        if(parentFolderId==null){
            files = fIleStorageService.getFilesInFolder(null);
        }
        else{
            files = fIleStorageService.getFilesInFolder(parentFolderId);
        }
        return ResponseEntity.ok(files);

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFile    (@PathVariable Long id){
        try{
            File file = fIleStorageService.getFileById(id);
            Path path = Paths.get(file.getPath());
            Files.deleteIfExists(path);
            fIleStorageService.deleteById(id);
            return ResponseEntity.ok("File deleted successfully!");

        }catch (Exception e){
            return ResponseEntity.status(500).body("Failed to delete file");

        }
    }
}
