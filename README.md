 <a href='https://ko-fi.com/A406JCM' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi4.png?v=f' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>

[![Release](https://jitpack.io/v/jrvansuita/CheckNewAppVersionAvailable.svg)](https://jitpack.io/#jrvansuita/CheckNewAppVersionAvailable)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CheckNewAppVersionAvailable-green.svg?style=true)](https://android-arsenal.com/details/1/4573)


# CheckNewAppVersionAvailable
This project makes a request to Play Store to check if there is a new version of your published app. Then, you can notify your users to do the update.

# Porpouse

This class makes a request to the Play Store to check if there is a new version of your published app. Depends on that, you can notify your users to then,  do the update.


# Usage

#### Step 1. Add the JitPack repository to your build file:

    allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}

#### Step 2. Add the dependency

    dependencies {
	        compile 'com.github.jrvansuita:CheckNewAppVersionAvailable:v1.0.0'
	}

# Samples
 You can take a look at the sample app [located on this project](/app/).

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

   
# License
See the [LICENSE](/LICENSE.txt). file for license rights and limitations (MIT).

## Support on Beerpay
Hey dude! Help me out for a couple of :beers:!

[![Beerpay](https://beerpay.io/jrvansuita/CheckNewAppVersionAvailable/badge.svg?style=beer-square)](https://beerpay.io/jrvansuita/CheckNewAppVersionAvailable)  [![Beerpay](https://beerpay.io/jrvansuita/CheckNewAppVersionAvailable/make-wish.svg?style=flat-square)](https://beerpay.io/jrvansuita/CheckNewAppVersionAvailable?focus=wish)