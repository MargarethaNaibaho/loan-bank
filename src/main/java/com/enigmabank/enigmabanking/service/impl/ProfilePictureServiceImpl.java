package com.enigmabank.enigmabanking.service.impl;

import com.enigmabank.enigmabanking.entity.ProfilePicture;
import com.enigmabank.enigmabanking.repository.ProfilePictureRepository;
import com.enigmabank.enigmabanking.service.ProfilePictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
@RequiredArgsConstructor
public class ProfilePictureServiceImpl implements ProfilePictureService {
    private final ProfilePictureRepository profilePictureRepository;
    private final Path directoryPath = Paths.get("/home/enigma/Documents/pelajaran/week 8/liveCode/asset/image");

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProfilePicture createFile(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File isn't found");

        try{
            Files.createDirectories(directoryPath);
            String filename = String.format("%d-%s", System.currentTimeMillis(), multipartFile.getOriginalFilename());
            Path filePath = directoryPath.resolve(filename);
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            ProfilePicture profilePicture = ProfilePicture.builder()
                    .name(filename)
                    .contentType(multipartFile.getContentType())
                    .size(multipartFile.getSize())
                    .path(filePath.toString())
                    .build();

            return profilePictureRepository.saveAndFlush(profilePicture);
        } catch(IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
