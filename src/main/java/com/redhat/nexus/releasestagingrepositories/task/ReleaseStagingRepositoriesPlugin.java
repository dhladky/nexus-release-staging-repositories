package com.redhat.nexus.releasestagingrepositories.task;

import javax.inject.Inject;
import javax.inject.Named;
import org.sonatype.nexus.plugin.PluginIdentity;
import org.eclipse.sisu.EagerSingleton;
import org.jetbrains.annotations.NonNls;

@Named
@EagerSingleton
public class ReleaseStagingRepositoriesPlugin extends PluginIdentity
{
    /**
    * Prefix for ID-like things.
    */
    @NonNls
    public static final String ID_PREFIX = "rsr";

    /**
    * Expected groupId for plugin artifact.
    */
    @NonNls
    public static final String GROUP_ID = "com.redhat.nexus";
    /**
    * Expected artifactId for plugin artifact.
    */
    @NonNls
    public static final String ARTIFACT_ID = "nexus-release-staging-repositories-plugin";
    @Inject
    public ReleaseStagingRepositoriesPlugin() throws Exception {
        super(GROUP_ID, ARTIFACT_ID);
    }
}