package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.adapters.in;

import com.amazonaws.services.s3.AmazonS3;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.delete.files.AwsS3DeleteUseCase;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.delete.files.DeleteFileCommand;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.download.files.AwsS3DownloadUseCase;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.download.files.CreatePresignedDownloadUrlCommand;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.download.files.DownloadFileCommand;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.upload.files.AwsS3UploadUseCase;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.upload.files.CreatePresignedUploadUrlCommand;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.upload.files.UploadFileCommand;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.upload.files.UploadFileWithAdditionalMetaInformationsCommand;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.service.AwsS3DeleteService;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.service.AwsS3DownloadService;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.service.AwsS3UploadService;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.upload.and.download.meta.informations.FileUploadMetaInformations;

import java.io.File;
import java.net.URL;
import java.util.Optional;

@Slf4j
public class AwsS3FileManagerAdapter {

    @NonNull
    private final AwsS3DownloadUseCase awsS3DownloadUseCase;

    @NonNull
    private final AwsS3UploadUseCase awsS3UploadUseCase;

    @NonNull
    private final AwsS3DeleteUseCase awsS3DeleteUseCase;

    public AwsS3FileManagerAdapter(@NonNull AmazonS3 amazonS3Client) {
        this.awsS3DownloadUseCase = new AwsS3DownloadService(amazonS3Client);
        this.awsS3UploadUseCase = new AwsS3UploadService(amazonS3Client);
        this.awsS3DeleteUseCase = new AwsS3DeleteService(amazonS3Client);
    }

    public Optional<File> downloadFile(@NonNull DownloadFileCommand command) {
        return awsS3DownloadUseCase.downloadFile(command);
    }

    public URL createPresignedDownloadUrl(@NonNull CreatePresignedDownloadUrlCommand command) {
        return awsS3DownloadUseCase.createPresignedDownloadUrl(command);
    }

    public FileUploadMetaInformations uploadFile(@NonNull UploadFileCommand command) {
        return awsS3UploadUseCase.uploadFile(command);
    }

    public FileUploadMetaInformations uploadFile(@NonNull UploadFileWithAdditionalMetaInformationsCommand command) {
        return awsS3UploadUseCase.uploadFile(command);
    }

    public URL createPresignedUploadUrl(@NonNull CreatePresignedUploadUrlCommand command) {
        return awsS3UploadUseCase.createPresignedUploadUrl(command);
    }

    public void deleteFile(@NonNull DeleteFileCommand command) {
        awsS3DeleteUseCase.deleteFile(command);
    }

}
