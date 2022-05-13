 <a href='https://ko-fi.com/A406JCM' target='_blank'><img height='36' style='border:0px;height:36px;' src='https://az743702.vo.msecnd.net/cdn/kofi4.png?v=f' border='0' alt='Buy Me a Coffee at ko-fi.com' /></a>

[![Release](https://jitpack.io/v/jrvansuita/CheckNewAppVersionAvailable.svg)](https://jitpack.io/#jrvansuita/CheckNewAppVersionAvailable)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CheckNewAppVersionAvailable-green.svg?style=true)](https://android-arsenal.com/details/1/4573)


# CheckNewAppVersionAvailable
This project makes a request to the Google Play Store to check if there is a new version of your published app. Should one be available, you can notify your users to apply the update.

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
Hey dude! Help me out for a glass of :beers:!


<a href="https://www.instagram.com/jnrvans/" target="_blank">
  <img src="https://camo.githubusercontent.com/c9dacf0f25a1489fdbc6c0d2b41cda58b77fa210a13a886d6f99e027adfbd358/68747470733a2f2f6564656e742e6769746875622e696f2f537570657254696e7949636f6e732f696d616765732f7376672f696e7374616772616d2e737667" alt="Instagram" witdh="44" height="44" hspace="10">
</a>
<a href="https://github.com/jrvansuita" target="_blank">
  <img src="https://camo.githubusercontent.com/b079fe922f00c4b86f1b724fbc2e8141c468794ce8adbc9b7456e5e1ad09c622/68747470733a2f2f6564656e742e6769746875622e696f2f537570657254696e7949636f6e732f696d616765732f7376672f6769746875622e737667" alt="Github" witdh="44" height="44" hspace="10">
</a>
<a href="https://play.google.com/store/apps/dev?id=8002078663318221363" target="_blank">
  <img src="https://camo.githubusercontent.com/8ce12185c778e13eed2073e7a6aba042ce5092d4d41744e7052e0fc16363c386/68747470733a2f2f6564656e742e6769746875622e696f2f537570657254696e7949636f6e732f696d616765732f7376672f676f6f676c655f706c61792e737667" alt="Google Play Store" witdh="44" height="44" hspace="10">
</a>
<a href="mailto:vansuita.jr@gmail.com" target="_blank" >
  <img src="https://camo.githubusercontent.com/4a3dd8d10a27c272fd04b2ce8ed1a130606f95ea6a76b5e19ce8b642faa18c27/68747470733a2f2f6564656e742e6769746875622e696f2f537570657254696e7949636f6e732f696d616765732f7376672f676d61696c2e737667" alt="E-mail" witdh="44" height="44" hspace="10">
</a>
