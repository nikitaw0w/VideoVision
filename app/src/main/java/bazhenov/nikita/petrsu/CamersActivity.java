package bazhenov.nikita.petrsu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import bazhenov.nikita.petrsu.videocam.VideoCamLab;
import bazhenov.nikita.petrsu.videocam.VideoCamera;

public class CamersActivity extends AppCompatActivity {
    private RecyclerView camersRecyclerView;
    private MyAdapter adapter;

    private VideoCamLab videoCamLab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camers);

        videoCamLab = VideoCamLab.getInstance(getApplicationContext());

        adapter = new MyAdapter(videoCamLab.getCameras());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        camersRecyclerView = (RecyclerView) findViewById(R.id.camersRecyclerView);
        camersRecyclerView.setLayoutManager(layoutManager);
        camersRecyclerView.setAdapter(adapter);
    }

    public void updateUI() {
        List<VideoCamera> camers  = videoCamLab.getCameras();
        if (adapter != null) {
            adapter.setCamers(camers);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.camers_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_camera:
                Intent intent = new Intent(this, AddCameraActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<VideoCamera> camers;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView name;
            public TextView id;
            public ImageView edit;

            public ViewHolder(View v) {
                super(v);
                name = (TextView) v.findViewById(R.id.camera_name);
                id = (TextView) v.findViewById(R.id.camera_id);
                edit = (ImageView) v.findViewById(R.id.edit_camera_button);
                edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(), EditCameraActivity.class);
                        intent.putExtra(EditCameraActivity.EXTRA_ID, id.getText().toString());
                        startActivity(intent);
                    }
                });
            }
        }

        public MyAdapter(List<VideoCamera> camers) {
            this.camers = camers;
        }

        public void setCamers(List<VideoCamera> camers) {
            this.camers = camers;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.camera, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.name.setText(camers.get(position).getName());
            holder.id.setText(camers.get(position).getIdCam());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: Добавить переход на активити с трансляцией.
                    //
                    // Context context = v.getContext();
                    // Intent intent = NameActivity.newIntent(context, holder.getAdapterPosition());
                    // context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return camers.size();
        }
    }
}
