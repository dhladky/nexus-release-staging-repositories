package com.redhat.nexus.releasestagingrepositories.task;

import com.sonatype.nexus.staging.internal.DefaultStagingManager;
import com.sonatype.nexus.staging.internal.DefaultStagingRepositoryManager;
import com.sonatype.nexus.staging.internal.persist.DefaultStagingConfiguration;
import com.sonatype.nexus.staging.persist.model.CStageRepository;
import com.sonatype.nexus.staging.persist.model.Configuration;
import org.sonatype.nexus.configuration.application.NexusConfiguration;
import org.sonatype.nexus.proxy.repository.Repository;
import org.sonatype.nexus.scheduling.AbstractNexusRepositoriesTask;
import org.sonatype.nexus.scheduling.AbstractNexusTask;

import javax.inject.Inject;
import javax.inject.Named;

import java.lang.reflect.Field;

import static com.google.common.base.Preconditions.checkNotNull;

@Named(CleanUpTask.ID)
public class CleanUpTask extends AbstractNexusTask<String> { //todo define Object

    final private NexusConfiguration nexusConfiguration;
    final private DefaultStagingManager stagingManager;
    final private DefaultStagingRepositoryManager stagingRepositoryManager;
    final private DefaultStagingConfiguration stagingConfiguration;

    static final String ID = "CleanUpTask";

    @Inject
    public CleanUpTask(final NexusConfiguration nexusConfiguration, DefaultStagingManager stagingManager, DefaultStagingRepositoryManager stagingRepositoryManager, DefaultStagingConfiguration stagingConfiguration) {
        this.nexusConfiguration = checkNotNull(nexusConfiguration);
        this.stagingManager = checkNotNull(stagingManager);
        this.stagingRepositoryManager = checkNotNull(stagingRepositoryManager);
        this.stagingConfiguration = checkNotNull(stagingConfiguration);
    }

    private static String ACTION = "cleanup_repository";

    @Override
    protected String doRun() throws Exception {
        logger.info("I am doing run!"); // todo not implemented

//        HashSet<String> stagingProfilesToProcess = new HashSet<>();
//        for(CProfile stagingProfile : stagingManager.listProfiles()) {
//            if(stagingProfile.getRepositoryTargetId().equals(getRepositoryId() )) {
//                // We need to process this
//                stagingProfilesToProcess.add(stagingProfile.getId());
//            }
//        }

        Field configurationField = DefaultStagingConfiguration.class.getDeclaredField("configuration");
        configurationField.setAccessible(true);
        Configuration configuration = (Configuration) configurationField.get(stagingConfiguration);


        for(CStageRepository stagingRepository : configuration.getRepositories()) {


        }

        return null;
    }

    @Override
    protected String getAction() {
        return ACTION;
    }

    @Override
    protected String getMessage() {
        //todo add profile repo id
        return "Executing repository cleanup: " + getParameter(CleanUpTaskDescriptor.FLD_STAGING_PROFILE_ID);
    }

}
