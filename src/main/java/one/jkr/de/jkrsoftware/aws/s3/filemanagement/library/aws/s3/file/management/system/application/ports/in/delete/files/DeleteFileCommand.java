package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.delete.files;

import lombok.NonNull;
import lombok.Value;
import one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.s3.file.key.S3ObjectFileKey;

@Value
public class DeleteFileCommand {

    @NonNull
    S3ObjectFileKey objectToDelete;

}
