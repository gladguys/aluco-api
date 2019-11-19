package com.gladguys.alucoapi.controllers;

import com.gladguys.alucoapi.services.AmazonS3ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/profile")
public class FileHandlerController {

	@Autowired
	private AmazonS3ClientService amazonS3ClientService;

	@PostMapping
	public ResponseEntity<?> uploadFile(@RequestPart(value = "photoProfile") MultipartFile file) throws IOException {
		this.amazonS3ClientService.uploadFileToS3Bucket(file, true);

		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteFile(@RequestParam("file_name") String fileName) {
		this.amazonS3ClientService.deleteFileFromS3Bucket(fileName);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}