package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.download.files.AwsS3DownloadUseCase;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.download.files.CreatePresignedDownloadUrlCommand;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.download.files.DownloadFileCommand;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.utils.AbsoluteS3KeyCalculator;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.s3.file.key.S3ObjectFileKey;

import java.io.File;
import java.net.URL;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class AwsS3DownloadService implements AwsS3DownloadUseCase {

    private static final String LOG_PREFIX = "[AWS S3 Download Service]: ";

    @NonNull
    private final AmazonS3 amazonS3Client;

    @NonNull
    private final Clock clock;

    public AwsS3DownloadService(@NonNull AmazonS3 amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
        this.clock = Clock.systemUTC();
    }

    @Override
    public Optional<File> downloadFile(@NonNull DownloadFileCommand command) {
        // not implemented yet.
        return Optional.empty();
    }

    @Override
    public URL createPresignedDownloadUrl(@NonNull CreatePresignedDownloadUrlCommand command) {
        log.info(LOG_PREFIX + "Create Presigned-Upload URL: \"{}\".", command);

        S3ObjectFileKey sourceS3Object = command.getSource();
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(
                sourceS3Object.getS3BucketName().getBucketName(), AbsoluteS3KeyCalculator.getAbsolutS3Key(sourceS3Object))
                .withMethod(HttpMethod.GET)
                .withExpiration(Date.from(Instant.now(clock).plus(1, ChronoUnit.HOURS)));
        sourceS3Object.getS3ObjectVersionId()
                .ifPresent(objectVersionId -> generatePresignedUrlRequest.withVersionId(objectVersionId.getObjectVersionId()));

        return amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);
    }
}
