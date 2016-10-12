package neat.starker.async;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;

import org.jsoup.Jsoup;


/**
 * Created by jrvansuita on 17/09/15.
 */
public class CheckNewAppVersion extends AsyncTask<Void, Void, CheckNewAppVersion.Result> {

    private static final String REFERRER = "http://www.google.com";
    private static final String DIV = "div[itemprop=softwareVersion]";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";
    private static final String PLAY_STORE_LINK = "http://play.google.com/store/apps/details?id=%s&hl=en";

    private Result result;
    private Context context;
    private ITaskComplete listener;

    public CheckNewAppVersion(Context context) {
        this.context = context;
        this.result = new Result(context);
    }

    public String getExternalAppLink() {
        return String.format(PLAY_STORE_LINK, context.getPackageName());
    }

    @Override
    protected CheckNewAppVersion.Result doInBackground(Void... params) {
        try {
            //Getting the current versions of the app and setting as old version is Result object.
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            result.setOldVersionCode(info.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            //Handle this exception by your own way...
        }

        try {
            //Using the Jsoup library to request the store app page and getting the app version code.
            String newVersion = Jsoup.connect(getExternalAppLink())
                    .timeout(30000)
                    .userAgent(USER_AGENT)
                    .referrer(REFERRER)
                    .get()
                    .select(DIV)
                    .first()
                    .ownText();

            result.setNewVersionCode(newVersion);
        } catch (Exception e) {
            //Handle this exception by your own way...
        }

        return result;
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
        private Context context;
        private String newVersionCode;
        private String oldVersionCode;

        public boolean hasNewVersion() {
            return onlyNumbers(getOldVersionCode()) < onlyNumbers(getNewVersionCode());
        }

        public String getNewVersionCode() {
            return newVersionCode;
        }

        public String getOldVersionCode() {
            return oldVersionCode;
        }

        public void setNewVersionCode(String newVersionCode) {
            this.newVersionCode = newVersionCode;
        }

        public void setOldVersionCode(String oldVersionCode) {
            this.oldVersionCode = oldVersionCode;
        }

        public Context getContext() {
            return context;
        }

        public void openUpdateLink() {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getExternalAppLink())));
        }

        public Result(Context context, String newVersionCode, String oldVersionCode) {
            this.context = context;
            this.newVersionCode = newVersionCode;
            this.oldVersionCode = oldVersionCode;
        }

        public Result(Context context) {
            this(context, "", "");
        }

        public long onlyNumbers(String s) {
            String val = s.replaceAll("\\D+", "");
            try {
                return Long.valueOf(val);
            }catch (Exception e){
                return 0;
            }
        }
    }
}