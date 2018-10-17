package com.aminocom.sdk;

import com.aminocom.sdk.model.client.Channel;

import java.util.List;

import io.reactivex.Observable;

class CacheRepository {
    private ObservableList<Channel> channels = new ObservableList<>();

    Observable<List<Channel>> getChannels() {
        return channels.getObservable();
    }

    void cacheChannels(List<Channel> channels) {
        this.channels.setItems(channels);
    }
}