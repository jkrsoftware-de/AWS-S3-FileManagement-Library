package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.s3.file.key.elements;

import lombok.NonNull;
import lombok.Value;

import java.util.Optional;

@Value(staticConstructor = "of")
public class S3ObjectVersionId {

    @NonNull
    String objectVersionId;

    private static Optional<S3ObjectVersionId> getOptionalOfS3VersionId(String versionId) {
        if (versionId.equals("")) {
            return Optional.empty();
        }
        return Optional.of(S3ObjectVersionId.of(versionId));
    }

}
