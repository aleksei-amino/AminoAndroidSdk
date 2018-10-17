package com.aminocom.aminoandroidsdk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.aminocom.sdk.Provider;
import com.aminocom.sdk.model.client.Channel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView testText = findViewById(R.id.test_text);
        Button testButton = findViewById(R.id.test_button);

        Provider provider = new Provider();

        provider.setChannelCache(getTestChannelList(3, "Channel"));

        disposable = provider.getChannelCache()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(items -> {
                            StringBuilder text = new StringBuilder();

                            for (Channel channel : items) {
                                text.append(channel.title).append("\n");
                            }

                            testText.setText(text.toString());
                        },
                        t -> Log.e(TAG, "Failed to get test data", t)
                );

        testButton.setOnClickListener(view -> provider.setChannelCache(getTestChannelList(5, "Updated channel")));
    }

    private List<Channel> getTestChannelList(int limit, String prefix) {
        List<Channel> channels = new ArrayList<>();

        for (int i = 0; i < limit; i++) {
            channels.add(new Channel(prefix + " " + i));
        }

        return channels;
    }

    @Override
    protected void onDestroy() {

        if (disposable != null) {
            disposable.dispose();
        }

        super.onDestroy();
    }
}
