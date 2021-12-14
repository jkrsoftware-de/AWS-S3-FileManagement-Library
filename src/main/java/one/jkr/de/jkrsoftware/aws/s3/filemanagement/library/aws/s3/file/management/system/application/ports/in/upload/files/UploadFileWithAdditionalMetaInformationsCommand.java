package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.upload.files;

import lombok.NonNull;
import lombok.Value;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.s3.file.key.S3ObjectFileKey;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.upload.and.download.meta.informations.AdditionalUploadMetaInformations;

import java.io.File;

@Value
public class UploadFileWithAdditionalMetaInformationsCommand {

    @NonNull
    S3ObjectFileKey destination;

    @NonNull
    File fileToUpload;

    @NonNull
    AdditionalUploadMetaInformations additionalUploadMetaInformations;

}
