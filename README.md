# nexus-release-staging-repositories
is extending possibilities of Sonatype Nexus Staging Plugin. It allows to automatically release promoted/staged staging repositories after a period of time. Because it extends the Nexus Staging Suite, it is usable only in conjunction with Nexus 2.x Professional. 

It adds a new scheduled task type with name **Automatic Release of Promoted Repositories**. It contains following settings: 

- **Target Staging Profile** - only repositories, that were promoted by this staging profile will be released. You need to put the staging profile ID here.
- **Staging Repository Age** - how long after the latest update of the staging repository will the automatic release be done (in days).
- **Automatic Drop after Release** - if checked, the released staging repositories will be dropped. It is identical to the checkbox of the same name you can check when you do the release manually in Nexus Web UI.
- **Description** - the optional text identical to the same field in the release form when doing the release manually in Nexus web UI.   

## Limitations

- so far it is necessary to copy-paste the _Target Staging Profile_ manually. This is not convenient and I hope I will be able to replace with a combobox, where you could select the profile. I failed to make it, but I asked Sonatype for help in this matter.
- the plugin runs with full permissions. If someone can guess the staging profile ID and you give him access to schedule tasks, he can release anything with this plugin. 

## Installation

- shut down Nexus
- expand the bundle zip file to sonatype-work/nexus/plugin-repository folder 
- on Mac/Linux set the file access permissions so Nexus can access the files. Example:
  `chmod -R 0764 ~/xxx/sonatype-work/nexus/plugin-repository/nexus-release-staging-repositories-plugin-2.14.15-01`
- start Nexus and wait for the web UI to become active
- go to Administration -> Scheduled Tasks tab
- create the new scheduled tasks as necessary

## Build
- NX2 uses old Google framework for dependency injection and all its dependencies requires specific range of Maven. Use version 3.0.5
- If you decide updating this code, you may work with Java 8 or higher, however never use more modern language patterns (like lambda). Dependency injections stops working.
- In order to build this plugin you must have access to NX2 Professional and add com.sonatype.nexus.plugins:nexus-staging-plugin:{actual-version-number} to your Maven cache.


