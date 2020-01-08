package com.redhat.nexus.releasestagingrepositories.task;

import com.google.common.collect.ImmutableList;
import org.sonatype.nexus.formfields.CheckboxFormField;
import org.sonatype.nexus.formfields.FormField;
import org.sonatype.nexus.formfields.NumberTextFormField;
import org.sonatype.nexus.formfields.StringTextFormField;
import org.sonatype.nexus.tasks.descriptors.AbstractScheduledTaskDescriptor;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.List;

@Named(CleanUpTaskDescriptor.ID)
@Singleton
public class CleanUpTaskDescriptor extends AbstractScheduledTaskDescriptor {
    static final String ID = "CleanUpTask";

    static final String FLD_STAGING_PROFILE_ID = "stgProfileId";

    static final String FLD_AGE = "ageID";

    static final String FLD_AUTOMATIC_DROP = "autoDrop";

    static final String FLD_DESCRIPTION = "description";

    @SuppressWarnings("rawtypes")
    private final List<FormField> fields = ImmutableList.<FormField>of(
//            new StagingProfileComboFormField(FLD_STAGING_PROFILE_ID,"Target Staging Profile", "Staging repositories promoted by this profile will be released",
//                    FormField.MANDATORY),
            new StringTextFormField(FLD_STAGING_PROFILE_ID,"Target Staging Profile", "Staging repositories promoted by this profile will be released",
                    FormField.MANDATORY),

            (new NumberTextFormField(FLD_AGE, "Staging Repository Age","The time period after the repositories will be released in days", FormField.MANDATORY)).withInitialValue(30),
            (new CheckboxFormField(FLD_AUTOMATIC_DROP, "Automatic Drop after Release", "If checked, the released staging profile will be dropped.", false)).withInitialValue(true),
            new StringTextFormField(FLD_DESCRIPTION, "Description", "Description message of the automatic release. Usable when Automatic Drop is not used.", false)
    );

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return "Automatic Release of Promoted Repositories";
    }

    @SuppressWarnings("rawtypes")
    @Override
    public List<FormField> formFields() {
        return fields;
    }
}
