package com.vansuita.checknewappversionavailable;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by jrvansuita on 17/09/15.
 * Modified by HaenaraShin on 19/10/14.
 */
public class CheckNewAppVersion extends AsyncTask<Void, Void, CheckNewAppVersion.Result> {
    private static final String REFERRER = "https://www.google.com";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
    private static final String PLAY_STORE_LINK = "https://play.google.com/store/apps/details?id=%s&hl=en";
    private static final String CSS_QUERY = ".hAyfc";
    private static final String CURRENT_VERSION_SELECT = ".BgcNfc";
    private static final String VESION_SELECT = ".htlgb";
    private static final String VARIES_WITH_DEVICE = "Varies with device";

    private final String PACKAGE_NAME;
    private final int TIMEOUT;

    private Context context;
    private ITaskComplete listener;

    public CheckNewAppVersion(Context context) {
        this(context, 30000, context.getPackageName());
    }
    public CheckNewAppVersion(Context context, int timeout, String packageName) {
        this.context = context;
        this.TIMEOUT = timeout;
        this.PACKAGE_NAME = packageName;
    }

    public String getExternalAppLink() {
        return String.format(PLAY_STORE_LINK, PACKAGE_NAME);
    }

    @Override
    protected Result doInBackground(Void... params) {
        String oldVersion = "";
        String newVersion = "";
        try {
            //Getting the current versions of the app and setting as old version is Result object.
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(PACKAGE_NAME, 0);
            oldVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            //Handle this exception by your own way...
            e.printStackTrace();
        }

        try {
            //Using the Jsoup library to request the store app page and getting the app version code.
            newVersion = getNewVersionCode();
        } catch (IOException | NullPointerException e) {
            //Handle this exception by your own way...
            e.printStackTrace();
        }

        return new Result(newVersion, oldVersion);
    }

    /**
     * Using the Jsoup library to request the store app page and getting the app version code.
     * @return
     * @throws IOException
     * @throws NullPointerException
     */
    private String getNewVersionCode() throws IOException, NullPointerException {
        // You may get a version name easier and faster with
        // "Jsoup.connect(getExternalAppLink()).get().select(VESION_SELECT).get(7).ownText()".
        // but, the following code might be safer.
        Elements elements = Jsoup.connect(getExternalAppLink())
            .timeout(TIMEOUT)
            .userAgent(USER_AGENT)
            .referrer(REFERRER)
            .get()
            .select(CSS_QUERY);
        for (Element element : elements) {
            if (element.select(CURRENT_VERSION_SELECT).first().ownText().contains("Current Version")){
                Elements elements1 = element.select(VESION_SELECT);
                for (Element e: elements1){
                    if (!e.ownText().trim().isEmpty()) {
                        return e.ownText();
                    }
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Result result) {
        if (listener != null) {
            listener.onTaskComplete(result);
        }
    }

    public CheckNewAppVersion setOnTaskCompleteListener(ITaskComplete listener) {
        this.listener = listener;
        return this;
    }

    public interface ITaskComplete {
        void onTaskComplete(Result result);
    }

    public class Result {
        final private String newVersionCode;
        final private String oldVersionCode;

        public boolean hasNewVersion() {
            return compareVersion(oldVersionCode, newVersionCode);
        }

        /**
         * TRUE : oldVersionCode < newVersionCode
         * FALSE : oldVersionCode >= newVersionCode OR an error occurred.
         * @param oldVersionCode
         * @param newVersionCode
         * @return
         */
        private boolean compareVersion(String oldVersionCode, String newVersionCode) {
            if (newVersionCode.contains(VARIES_WITH_DEVICE)) {
                // ignore VARIES_WITH_DEVICE
                return false;
            }
            StringTokenizer serverTokenizer = new StringTokenizer(newVersionCode, ".");
            StringTokenizer appTokenizer = new StringTokenizer(oldVersionCode, ".");
            if (serverTokenizer.countTokens() != appTokenizer.countTokens()){
                // version name format is not same.
                return true;
            }
            while (serverTokenizer.hasMoreTokens()) { // Major, Minor, Patch, ...
                try {
                    long googlePlayVer = Long.valueOf(serverTokenizer.nextToken().replaceAll("\\D+", ""));
                    long packageVer = Long.valueOf(appTokenizer.nextToken().replaceAll("\\D+", ""));
                    if (googlePlayVer > packageVer){ // higher version has found.
                        return true;
                    }
                } catch (NumberFormatException e){
                    return false;
                }
            }
            return false;
        }

        public String getNewVersionCode() {
            return newVersionCode;
        }

        public String getOldVersionCode() {
            return oldVersionCode;
        }

        @Deprecated
        public void openUpdateLink() {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getExternalAppLink())));
        }

        public void openUpdateLink(Context context) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getExternalAppLink())));
        }

        public Result(String newVersionCode, String oldVersionCode) {
            this.newVersionCode = newVersionCode;
            this.oldVersionCode = oldVersionCode;
        }
    }
}
