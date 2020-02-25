package com.redhat.nexus.releasestagingrepositories.task;

import com.sonatype.nexus.staging.internal.persist.DefaultStagingConfiguration;
import org.junit.Test;

import java.lang.reflect.Field;

public class CleanUpTaskTest {

    @Test
    public void verifyObjects() throws NoSuchFieldException {
        // fail if the structure of DefaultStagingConfiguration changed again
        DefaultStagingConfiguration.class.getDeclaredField("configurationSnapshot");
    }
}