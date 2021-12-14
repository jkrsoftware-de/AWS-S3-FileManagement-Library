package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.upload.and.download.meta.informations;

import lombok.NonNull;
import lombok.Value;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.s3.file.key.S3ObjectFileKey;

@Value
public class FileUploadMetaInformations {

    @NonNull
    S3ObjectFileKey s3ObjectFileKey;

    long fileSizeOfUploadedFileInByte;

    long fileUploadDurationInMilliseconds;

}
