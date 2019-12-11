package com.gladguys.alucoapi.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AmazonS3ClientService {
	void uploadFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess) throws IOException;
	void deleteFileFromS3Bucket(String fileName);
}