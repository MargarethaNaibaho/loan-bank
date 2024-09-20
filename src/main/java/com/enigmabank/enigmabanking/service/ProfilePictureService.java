package com.enigmabank.enigmabanking.service;

import com.enigmabank.enigmabanking.entity.ProfilePicture;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ProfilePictureService {
    ProfilePicture createFile(MultipartFile multipartFile);
}
