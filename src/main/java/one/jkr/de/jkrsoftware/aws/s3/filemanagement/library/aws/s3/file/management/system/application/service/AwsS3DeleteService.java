package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.delete.files.AwsS3DeleteUseCase;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.delete.files.DeleteFileCommand;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.utils.AbsoluteS3KeyCalculator;

@RequiredArgsConstructor
@Slf4j
public class AwsS3DeleteService implements AwsS3DeleteUseCase {

    private static final String LOG_PREFIX = "[AWS S3 Delete Service]: ";

    @NonNull
    private final AmazonS3 amazonS3Client;

    @Override
    public void deleteFile(@NonNull DeleteFileCommand command) {
        log.info(LOG_PREFIX + "Delete S3-File: \"{}\".", command);
        amazonS3Client.deleteObject(
                new DeleteObjectRequest(
                        command.getObjectToDelete().getS3BucketName().getBucketName(),
                        AbsoluteS3KeyCalculator.getAbsolutS3Key(command.getObjectToDelete())
                )
        );
    }

}
