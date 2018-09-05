package com.campus.appointment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.campus.appointment.adapter.TagGroupAdapter;
import com.moxun.tagcloudlib.view.TagCloudView;

import java.util.ArrayList;
import java.util.List;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        TagCloudView tagCloud = (TagCloudView) findViewById(R.id.tag_cloud);
        List<TagGroupGson> groupGsons = new ArrayList<>();
        groupGsons.add(new TagGroupGson("11", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3198678185,878755003&fm=26&gp=0.jpg"));
        groupGsons.add(new TagGroupGson("11", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3198678185,878755003&fm=26&gp=0.jpg"));
        groupGsons.add(new TagGroupGson("11", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3198678185,878755003&fm=26&gp=0.jpg"));
        groupGsons.add(new TagGroupGson("11", "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3198678185,878755003&fm=26&gp=0.jpg"));
        TagGroupAdapter adapter = new TagGroupAdapter(groupGsons, Test.this);
        tagCloud.setAdapter(adapter);
        tagCloud.run();
    }
}
