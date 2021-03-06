package com.gladguys.alucoapi.services.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.gladguys.alucoapi.exception.ApiResponseException;
import com.gladguys.alucoapi.services.AmazonS3ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class AmazonS3ClientServiceImpl implements AmazonS3ClientService {
	private String awsS3AudioBucket;
	private AmazonS3 amazonS3;

	@Autowired
	public AmazonS3ClientServiceImpl(Region awsRegion, AWSCredentialsProvider awsCredentialsProvider, String awsS3AudioBucket) {
		this.amazonS3 = AmazonS3ClientBuilder.standard()
				.withCredentials(awsCredentialsProvider)
				.withRegion(awsRegion.getName()).build();
		this.awsS3AudioBucket = awsS3AudioBucket;
	}

	@Async
	public void uploadFileToS3Bucket(MultipartFile multipartFile, boolean enablePublicReadAccess) throws IOException {
		String fileName = multipartFile.getOriginalFilename();

		try {
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(multipartFile.getBytes());
			fos.close();

			PutObjectRequest putObjectRequest = new PutObjectRequest(this.awsS3AudioBucket, fileName, file);

			if (enablePublicReadAccess) {
				putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
			}
			this.amazonS3.putObject(putObjectRequest);
			file.delete();
		} catch (IOException | AmazonServiceException ex) {
			throw new ApiResponseException("Erro ao fazer upload da imagem no aws s3");
		}
	}

	@Async
	public void deleteFileFromS3Bucket(String fileName) {
		try {
			amazonS3.deleteObject(new DeleteObjectRequest(awsS3AudioBucket, fileName));
		} catch (AmazonServiceException ex) {
			throw new ApiResponseException("Erro ao deletar imagem no aws s3");
		}
	}
}
