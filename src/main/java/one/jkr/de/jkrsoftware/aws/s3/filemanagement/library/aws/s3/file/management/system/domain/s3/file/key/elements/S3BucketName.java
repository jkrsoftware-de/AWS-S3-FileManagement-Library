package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.s3.file.key.elements;

import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
public class S3BucketName {

    @NonNull
    String bucketName;

}
