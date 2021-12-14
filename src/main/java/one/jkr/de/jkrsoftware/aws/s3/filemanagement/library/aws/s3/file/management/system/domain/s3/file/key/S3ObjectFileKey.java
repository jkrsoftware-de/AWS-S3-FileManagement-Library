package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.s3.file.key;

import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.s3.file.key.elements.S3BucketName;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.s3.file.key.elements.S3ObjectVersionId;

import java.util.Optional;

@Value
@NonFinal
public class S3ObjectFileKey {

    @NonNull
    S3BucketName s3BucketName;

    @NonNull
    String s3ObjectBucketPath;

    @NonNull
    String s3ObjectName;

    @NonNull
    Optional<S3ObjectVersionId> s3ObjectVersionId;

    public static S3ObjectFileKey createWithVersionId(@NonNull S3BucketName s3BucketName, @NonNull String s3ObjectBucketPath,
                                                      @NonNull String s3ObjectName,
                                                      @NonNull Optional<S3ObjectVersionId> s3ObjectVersionId) {
        return new S3ObjectFileKey(s3BucketName, s3ObjectBucketPath, s3ObjectName, s3ObjectVersionId);
    }

    public static S3ObjectFileKey createWithoutVersionId(@NonNull S3BucketName s3BucketName, @NonNull String s3ObjectBucketPath,
                                                         @NonNull String s3ObjectName) {
        return new S3ObjectFileKey(s3BucketName, s3ObjectBucketPath, s3ObjectName, Optional.empty());
    }

    public S3ObjectFileKey withChangedS3ObjectVersion(@NonNull Optional<S3ObjectVersionId> s3ObjectVersionId) {
        return new S3ObjectFileKey(s3BucketName, s3ObjectBucketPath, s3ObjectName, s3ObjectVersionId);
    }

}
