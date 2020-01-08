package com.redhat.nexus.releasestagingrepositories.task;

import com.sonatype.nexus.staging.StagingManager;
import com.sonatype.nexus.staging.internal.RepositoryState;
import com.sonatype.nexus.staging.internal.persist.DefaultStagingConfiguration;
import com.sonatype.nexus.staging.internal.persist.StagingConfiguration;
import com.sonatype.nexus.staging.persist.model.CStageRepository;
import com.sonatype.nexus.staging.persist.model.Configuration;
import org.sonatype.nexus.scheduling.AbstractNexusTask;

import javax.inject.Inject;
import javax.inject.Named;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

import static com.google.common.base.Preconditions.checkNotNull;

@Named(CleanUpTask.ID)
public class CleanUpTask extends AbstractNexusTask<String> {

    final private StagingManager stagingManager;
    final private StagingConfiguration stagingConfiguration;

    static final String ID = "CleanUpTask";

    @Inject
    public CleanUpTask(final StagingManager stagingManager, final StagingConfiguration stagingConfiguration) {
        this.stagingManager = checkNotNull(stagingManager);
        this.stagingConfiguration = checkNotNull(stagingConfiguration);
    }

    @Override
    protected String doRun() throws Exception {
        logger.info("I am doing run!"); // todo not implemented
        Field configurationField = DefaultStagingConfiguration.class.getDeclaredField("configuration");
        configurationField.setAccessible(true);
        Configuration configuration = (Configuration) configurationField.get(stagingConfiguration);

        String profileId = getParameter(CleanUpTaskDescriptor.FLD_STAGING_PROFILE_ID);
        int age = Integer.parseInt(getParameter(CleanUpTaskDescriptor.FLD_AGE));

        Date processingDate = new Date(System.currentTimeMillis() - age*86400000L);

        ArrayList<String> reposToPromote = new ArrayList<>();
        for(CStageRepository stagingRepository : configuration.getRepositories()) {
            if(stagingRepository.getProfileId().equals(profileId)) {
                if(stagingRepository.getUpdated().before(processingDate)
                        && (RepositoryState.matches(stagingRepository.getState(), RepositoryState.closed)
                        || RepositoryState.matches(stagingRepository.getState(), RepositoryState.group))
                ) {
                    final String id = stagingRepository.getId();
                    logger.info("Automatically releasing repository "+ id);
                    reposToPromote.add(id);
                }
            }
        }

        if(reposToPromote.size() > 0) {
           try {
               stagingManager.promoteStagingRepositories(reposToPromote, getParameter(CleanUpTaskDescriptor.FLD_DESCRIPTION), Boolean.parseBoolean(getParameter(CleanUpTaskDescriptor.FLD_AUTOMATIC_DROP)));
           } catch (Exception e) {
               logger.error("Error promoting repositories: ", e.getMessage());
           }
        }

        return null;
    }

    @Override
    protected String getAction() {
        return "cleanup_repository";
    }

    @Override
    protected String getMessage() {

        return "Executing repository cleanup: " + getParameter(CleanUpTaskDescriptor.FLD_STAGING_PROFILE_ID);
    }

}
