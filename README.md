# CheckNewAppVersionAvailable
This class was created to use in Android projects.

# Purpose
This class makes a request to the Play Store to check if there is a new version of your published app. Depending on the result, you can then notify your users to do any neccersairy update.

# Usage

Copy the CheckNewAppVersion.java class file to your project.

You will also have to add the Jsoup library to your project. You can chose to add using the .jar file or through gradle.

[Official documentation here](https://jsoup.org/download)

##### First Alternative -  Importing the .jar file.

##### Step 1. Downloading the library file.
  Download the last version of Jsoup library(.jar) [here](https://jsoup.org/download). Or you  download the .jar file [located in this project](https://github.com/jrvansuita/CheckNewAppVersionAvailable/blob/master/jsoup-1.9.2.jar).

##### Step 2. Adding the library file.
  Copy and paste the .jar file to libs folder of your project. After that, don't forget to include the file or the whole directory as dependancy in the `build.gradle` file.
 
    dependencies {
       compile fileTree(include: ['*.jar'], dir: 'libs')
       //or
       compile files('libs/jsoup-1.9.2.jar')
    }
 
##### Second Alternative -  Importing the library using gradle.

Simple add the following compile line in the dependencies definitions on your `build.gradle` file.

    dependencies {
      compile 'org.jsoup:jsoup:1.9.2'
    }


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
