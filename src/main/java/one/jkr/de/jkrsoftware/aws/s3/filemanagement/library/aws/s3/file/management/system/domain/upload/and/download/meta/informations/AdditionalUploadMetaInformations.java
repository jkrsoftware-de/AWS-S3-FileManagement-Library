package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.domain.upload.and.download.meta.informations;

import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import lombok.NonNull;
import lombok.Value;

import java.util.Optional;

@Value
public class AdditionalUploadMetaInformations {

    @NonNull
    Optional<AccessControlList> accessControlList;

    @NonNull
    Optional<CannedAccessControlList> cannedAccessControlList;

}
