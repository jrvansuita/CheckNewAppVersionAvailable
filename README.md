# CheckNewAppVersionAvailable
This class was created to use in Android projects.

# Porpouse
This class makes a request to Play Store to check if there is a new version of your published app. Then, you can notify your users to do the update.



# Usage

    new CheckNewAppVersion(yourContext).setOnTaskCompleteListener(new CheckNewAppVersion.ITaskComplete() {
            @Override
            public void onTaskComplete(CheckNewAppVersion.Result result) {
                
                //Check if there is a new version available on Google Play Store.
                result.hasNewVersion();

                //Retrive the new published version code.
                result.getNewVersionCode();
                
                //Retrive app current version code.
                result.getOldVersionCode();
                
                //Open Google Play Store on your app page to do the update.
                result.openUpdateLink();
            }
        }).execute();
