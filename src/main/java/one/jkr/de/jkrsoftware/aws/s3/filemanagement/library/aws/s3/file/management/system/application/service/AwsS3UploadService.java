package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.google.common.base.Stopwatch;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.upload.files.AwsS3UploadUseCase;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.upload.files.CreatePresignedUploadUrlCommand;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.upload.files.UploadFileCommand;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.upload.files.UploadFileWithAdditionalMetaInformationsCommand;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.utils.AbsoluteS3KeyCalculator;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.s3.file.key.S3ObjectFileKey;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.upload.and.download.meta.informations.AdditionalUploadMetaInformations;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.upload.and.download.meta.informations.FileUploadMetaInformations;

import java.io.File;
import java.net.URL;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
public class AwsS3UploadService implements AwsS3UploadUseCase {

    private static final String LOG_PREFIX = "[AWS S3 Upload Service]: ";

    @NonNull
    private final AmazonS3 amazonS3Client;

    @NonNull
    private final Clock clock;

    public AwsS3UploadService(@NonNull AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
        this.clock = Clock.systemUTC();
    }

    @Override
    public FileUploadMetaInformations uploadFile(@NonNull UploadFileCommand command) {
        return uploadFile(
                new UploadFileWithAdditionalMetaInformationsCommand(
                        command.getDestination(), command.getFileToUpload(),
                        new AdditionalUploadMetaInformations(Optional.empty(), Optional.empty())
                )
        );
    }

    @Override
    public FileUploadMetaInformations uploadFile(@NonNull UploadFileWithAdditionalMetaInformationsCommand command) {
        log.info(LOG_PREFIX + "Upload File: \"{}\".", command);
        Stopwatch durationOfUploadTimer = Stopwatch.createStarted();
        S3ObjectFileKey destinationS3ObjectFileKey = command.getDestination();
        File fileToUpload = command.getFileToUpload();

        PutObjectRequest putObjectRequest = new PutObjectRequest(
                destinationS3ObjectFileKey.getS3BucketName().getBucketName(), AbsoluteS3KeyCalculator.getAbsolutS3Key(
                destinationS3ObjectFileKey), fileToUpload
        );

        AdditionalUploadMetaInformations additionalUploadMetaInformations = command.getAdditionalUploadMetaInformations();
        if (additionalUploadMetaInformations.getAccessControlList().isPresent()) {
            putObjectRequest.withAccessControlList(additionalUploadMetaInformations.getAccessControlList().get());
        }
        if (additionalUploadMetaInformations.getCannedAccessControlList().isPresent()) {
            putObjectRequest.withCannedAcl(additionalUploadMetaInformations.getCannedAccessControlList().get());
        }

        PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);
        durationOfUploadTimer.stop();

        return new FileUploadMetaInformations(
                destinationS3ObjectFileKey, fileToUpload.length(), durationOfUploadTimer.elapsed(TimeUnit.MILLISECONDS)
        );
    }

    @Override
    public URL createPresignedUploadUrl(@NonNull CreatePresignedUploadUrlCommand command) {
        log.info(LOG_PREFIX + "Create Presigned-Upload URL: \"{}\".", command);

        S3ObjectFileKey destinationS3ObjectKey = command.getDestination();
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(
                destinationS3ObjectKey.getS3ObjectName(), AbsoluteS3KeyCalculator.getAbsolutS3Key(destinationS3ObjectKey)
        ).withMethod(HttpMethod.PUT).withExpiration(Date.from(Instant.now(clock).plus(1, ChronoUnit.HOURS)));
        return amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);
    }
}
