package com.dhitoshi.xfrs.huixiaobao.common;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.widget.RemoteViews;
import android.widget.Toast;
import com.dhitoshi.xfrs.huixiaobao.R;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class NewVersion {
	private NotificationManager manager;
	private Notification notify;
	private Context context;
	private File fileInstall;
	private final String filename = Environment.getExternalStorageDirectory() + "/huixiaobao/";
	public NewVersion(Context context) {
		this.context=context;
	}
	@SuppressWarnings("static-access")
	public  void downLoadNewApk(String apkUri,String versionName) {
		File file=new File(filename);
		if(!file.exists()) {
			file.mkdirs();
		}
		//建立下载的apk文件
		fileInstall = new File(filename+"慧销宝_"+versionName+".apk");
		if(!fileInstall.exists()) {
			manager = (NotificationManager) context.getSystemService((context.NOTIFICATION_SERVICE));
			notify = new Notification();

			notify.icon = R.mipmap.download;
			// 通知栏显示所用到的布局文件
			notify.contentView = new RemoteViews(context.getPackageName(), R.layout.view_notify_item);
			manager.notify(100, notify);
			Toast.makeText(context, "后台君正在为您急速下载最新版本。。。", Toast.LENGTH_SHORT).show();
			downLoadSchedule(apkUri, completeHandler,fileInstall);
		}
		else {
			installApk(fileInstall);
		}
	}
	
	private void downLoadSchedule(final String apkUri, final Handler handler, final File file) {
		new Thread()
		{
			@Override
			
			public void run() {
			try {
				URL url = new URL(apkUri);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setDoInput(true);
				con.connect();
				if (con.getResponseCode() == 200) {
					InputStream inputStream = con.getInputStream();
					long length = con.getContentLength();
					FileOutputStream outputStream = new FileOutputStream(file);
					int temp = 0;
					byte[] buffer = new byte[1024];
					int len = -1;
					while ((len = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, len);
						// 当前进度
						int schedule = (int) ((file.length() * 100) / length);
						// 通知更新进度（10,7,4整除才通知，没必要每次都更新进度）
						if (temp != schedule&& (schedule % 10 == 0 || schedule % 4 == 0 || schedule % 7 == 0)) {
							// 保证同一个数据只发了一次
							temp = schedule;
							handler.sendEmptyMessage(schedule);
						}
					}
					outputStream.close();
					inputStream.close();
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
			
		}.start();	
	}
	/*** 更新通知栏*/ 
	private Handler completeHandler = new Handler() {
		public void handleMessage(Message msg) {
			if(msg.what<100) {
				notify.contentView.setTextViewText(R.id.notify_updata_values_tv, msg.what + "%");
				notify.contentView.setProgressBar(R.id.notify_updata_progress,100, msg.what, false);
				manager.notify(100, notify);
			}
			else {
				notify.contentView.setTextViewText(R.id.notify_updata_values_tv, "下载完成");
				notify.contentView.setProgressBar(R.id.notify_updata_progress,100, msg.what, false);
				// 清除通知栏
				manager.cancel(100);
				installApk(fileInstall);
			}
		};
	};
	private void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
			Uri contentUri = FileProvider.getUriForFile(context,"com.dhitoshi.hxb.fileprovider", file);
			intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
		} else {
			intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		}
        context.startActivity(intent);
    }
}
