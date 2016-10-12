# CheckNewAppVersionAvailable
This class was created to use in Android projects.

# Porpouse
This class makes a request to Play Store to check if there is a new version of your published app. Then, you can notify your users to do the update.

# Usage

You have to add the Jsoup library to your project. You can chose to add using the .jar file ou using gradle.

[Official documentation here.](https://jsoup.org/download)

#### #First Alternative -  Importing the .jar file.

##### Step 1. Downloading the library file.
  Download the last version of Jsoup library(.jar) [here](https://jsoup.org/download). Or you can download the .jar file [located in this project](https://github.com/jrvansuita/CheckNewAppVersionAvailable/blob/master/jsoup-1.9.2.jar).

##### Step 2. Adding the library file.
  Copy and paste the .jar file to libs folder of your project. After that, don't forget to include the file or the whole directory as dependncy in the gradle file.
 
    dependencies {
       compile fileTree(include: ['*.jar'], dir: 'libs')
       //or
       compile files('libs/jsoup-1.9.2.jar')
    }
 
#### #Seconde Alternative -  Importing the library using gradle.

Simple add the compile line in the dependencies definitions on your gradle file.

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
        

