package com.aminocom.sdk;

import com.aminocom.sdk.model.client.Channel;
import com.aminocom.sdk.model.client.Epg;
import com.aminocom.sdk.model.client.Group;
import com.aminocom.sdk.model.client.Program;

import java.util.List;

import io.reactivex.Observable;

public interface LocalRepository {
    Observable<List<Channel>> getChannels();

    void cacheChannels(List<Channel> channels);

    Observable<List<Program>> getPrograms();

    void cachePrograms(List<Program> programs);

    Observable<List<Epg>> getEpg();

    void cacheEpg(List<Epg> epgList);

    Observable<List<Group>> getGroup();

    void cacheGroups(List<Group> groups);
}