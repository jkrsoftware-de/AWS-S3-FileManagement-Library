package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.utils;

import lombok.NonNull;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.s3.file.key.S3ObjectFileKey;

public class AbsoluteS3KeyCalculator {

    public static String getAbsolutS3Key(@NonNull S3ObjectFileKey s3ObjectFileKey) {
        return s3ObjectFileKey.getS3ObjectBucketPath() + "/" + s3ObjectFileKey.getS3ObjectName();
    }

}
