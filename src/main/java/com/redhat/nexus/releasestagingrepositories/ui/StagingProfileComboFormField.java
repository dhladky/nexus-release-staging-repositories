package com.redhat.nexus.releasestagingrepositories.ui;

import org.sonatype.nexus.formfields.Combobox;
//fixme does not work

/** Combobox for picking of staging profile. */
public class StagingProfileComboFormField extends Combobox<String> {
    public static final String DEFAULT_HELP_TEXT = "Select the staging profile.";

    public static final String DEFAULT_LABEL = "Staging Profile";

    public StagingProfileComboFormField(String id, String label, String helpText, boolean required, String regexValidation) {
        super(id, label, helpText, required, regexValidation);
    }

    public StagingProfileComboFormField(String id, String label, String helpText, boolean required) {
        super(id, label, helpText, required);
    }

    public StagingProfileComboFormField(String id, boolean required) {
        super(id, DEFAULT_LABEL, DEFAULT_HELP_TEXT, required);
    }

    public StagingProfileComboFormField(String id) {
        super(id, DEFAULT_LABEL, DEFAULT_HELP_TEXT, false);
    }

    @Override
    public String getStorePath() {
        return restlet1xStore("/staging/profiles");
    }

    @Override
    public String getStoreRoot() {
        return "data";
    }

    @Override
    public String getType() {
        //noinspection SpellCheckingInspection
        return "combo"; // FIXME: 06/01/2020
    }
}
