[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CheckNewAppVersionAvailable-green.svg?style=true)](https://android-arsenal.com/details/1/4573)


# CheckNewAppVersionAvailable
This class makes a request to Play Store to check if there is a new version of your published app. Then, you can notify your users to do the update.

# Porpouse

This class makes a request to the Play Store to check if there is a new version of your published app. Depends on that, you can notify your users to then,  do the update.

# Usage


# Implementation

    new CheckNewAppVersion(yourContext).setOnTaskCompleteListener(new CheckNewAppVersion.ITaskComplete() {
        @Override
        public void onTaskComplete(CheckNewAppVersion.Result result) {

            //Checks if there is a new version available on Google Play Store.
            result.hasNewVersion();

            //Get the new published version code of the app.
            result.getNewVersionCode();

            //Get the app current version code.
            result.getOldVersionCode();

            //Opens the Google Play Store on your app page to do the update.
            result.openUpdateLink();
        }
    }).execute();
