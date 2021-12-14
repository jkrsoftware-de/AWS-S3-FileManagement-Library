package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.upload.files;

import lombok.NonNull;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.upload.and.download.meta.informations.FileUploadMetaInformations;

import java.net.URL;

public interface AwsS3UploadUseCase {

    FileUploadMetaInformations uploadFile(@NonNull UploadFileCommand command);

    FileUploadMetaInformations uploadFile(@NonNull UploadFileWithAdditionalMetaInformationsCommand command);

    URL createPresignedUploadUrl(@NonNull CreatePresignedUploadUrlCommand command);

}
