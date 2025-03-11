package com.sdis.bilan.lsf.police;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.sdis.bilan.lsf.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePoliceActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    public void setContentView(View view) {
        drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.drawer_police, null);
        FrameLayout container = drawerLayout.findViewById(R.id.content);
        container.addView(view);
        super.setContentView(drawerLayout);

        Toolbar toolbar = drawerLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = drawerLayout.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.menu_icon);
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 50, 50, false);
        Drawable resizedDrawable = new BitmapDrawable(getResources(), resizedBitmap);
        toolbar.setNavigationIcon(resizedDrawable);

        List<RechercheItem> items = new ArrayList<>();

        AssetManager assetManager = getAssets();
        try {
            String[] videoFiles = assetManager.list("videos/police");

            for (String video : videoFiles) {
                RechercheItem item = new RechercheItem();
                item.setNomOrigine(video);
                item.setNomAffiche(video.replace("_", " ").replace("39", "'").replace("63", "?").replace(".mp4", "").toUpperCase());
                items.add(item);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        View headerView = navigationView.getHeaderView(0);
        CustomArrayAdapter adapter = new CustomArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        AutoCompleteTextView actv = headerView.findViewById(R.id.recherche);
        actv.setOnItemClickListener((parent, linearLayout, position, id) -> {
            Intent intent = new Intent(BasePoliceActivity.this, VideoPoliceActivity.class);
            intent.putExtra("VIDEO_NAME", ((RechercheItem) parent.getItemAtPosition(position)).getNomOrigine());
            startActivity(intent);
            actv.setText(null);
        });
        actv.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    actionId == EditorInfo.IME_ACTION_SEARCH ||
                    event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                // Masquer le clavier
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(actv.getWindowToken(), 0);
                }

                return true;
            }
            return false;
        });
        actv.setThreshold(1);
        actv.setAdapter(adapter);
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                drawerView.setAlpha(slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(actv.getWindowToken(), 0);
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = null;
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.nav_home:
                if (!(this instanceof AccueilPoliceActivity)) {
                    intent = new Intent(BasePoliceActivity.this, AccueilPoliceActivity.class);
                }
                break;
            case R.id.nav_clavier:
                if (!(this instanceof ClavierPoliceActivity)) {
                    intent = new Intent(BasePoliceActivity.this, ClavierPoliceActivity.class);
                }
                break;
            case R.id.nav_dessin:
                if (!(this instanceof DessinPoliceActivity)) {
                    intent = new Intent(BasePoliceActivity.this, DessinPoliceActivity.class);
                }
                break;
            case R.id.nav_pointsPermis:
                if (!(this instanceof PointsPermisPoliceActivity)) {
                    intent = new Intent(BasePoliceActivity.this, PointsPermisPoliceActivity.class);
                }
                break;
            case R.id.nav_objetsTrouves:
                if (!(this instanceof ObjetsTrouvesPoliceActivity)) {
                    intent = new Intent(BasePoliceActivity.this, ObjetsTrouvesPoliceActivity.class);
                }
                break;
            case R.id.nav_vitesse:
                if (!(this instanceof VitessePoliceActivity)) {
                    intent = new Intent(BasePoliceActivity.this, VitessePoliceActivity.class);
                }
                break;
            case R.id.nav_voiture:
                if (!(this instanceof VoiturePoliceActivity)) {
                    intent = new Intent(BasePoliceActivity.this, VoiturePoliceActivity.class);
                }
                break;
            case R.id.nav_aPropos:
                if (!(this instanceof AProposPoliceActivity)) {
                    intent = new Intent(BasePoliceActivity.this, AProposPoliceActivity.class);
                }
                break;
            case R.id.nav_parametres:
                if (!(this instanceof ParametresPoliceActivity)) {
                    intent = new Intent(BasePoliceActivity.this, ParametresPoliceActivity.class);
                }
                break;
            default:
                return false;
        }
        if (intent != null) {
            startActivity(intent);
        }
        return true;
    }

    public static class RechercheItem {

        private String nomOrigine;

        private String nomAffiche;

        public String getNomOrigine() {
            return nomOrigine;
        }

        public void setNomOrigine(String nomOrigine) {
            this.nomOrigine = nomOrigine;
        }

        public String getNomAffiche() {
            return nomAffiche;
        }

        public void setNomAffiche(String nomAffiche) {
            this.nomAffiche = nomAffiche;
        }

        @NonNull
        @Override
        public String toString() {
            return nomAffiche;
        }
    }

    public class CustomArrayAdapter extends ArrayAdapter<RechercheItem> {

        private List<RechercheItem> originalItems;
        private List<RechercheItem> filteredItems;

        public CustomArrayAdapter(Context context, int resource, List<RechercheItem> objects) {
            super(context, resource, objects);
            this.originalItems = new ArrayList<>(objects);
            this.filteredItems = objects;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View view = super.getView(position, convertView, parent);

            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundColor(getColorByNomVideo(textView.getText().toString()));
            }

            return view;
        }

        private int getColorByNomVideo(String video) {
            if (video.startsWith("ABECEDAIRE")) {
                return Color.parseColor("#1D90DC");
            } else if (video.startsWith("ABORDAGE")) {
                return Color.parseColor("#00B52A");
            } else if (video.startsWith("DOCUMENTS")) {
                return Color.parseColor("#8153FF");
            } else if (video.startsWith("SECURITE ROUTIERE")) {
                return Color.parseColor("#da1a29");
            } else {
                return Color.BLACK;
            }
        }

        @Override
        public int getCount() {
            return filteredItems.size();
        }

        @Override
        public RechercheItem getItem(int position) {
            return filteredItems.get(position);
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();
                    List<RechercheItem> suggestions = new ArrayList<>();

                    if (constraint != null) {
                        String query = constraint.toString().toLowerCase().replace("'", "").replace(" ", "");

                        for (RechercheItem item : originalItems) {
                            String normalizedNomAffiche = item.getNomAffiche().toLowerCase().replace("'", "").replace(" ", "");

                            if (normalizedNomAffiche.contains(query)) {
                                suggestions.add(item);
                            }
                        }

                        results.values = suggestions;
                        results.count = suggestions.size();
                    }

                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    List<RechercheItem> values = (List<RechercheItem>) results.values;
                    filteredItems = (values != null) ? values : new ArrayList<>();
                    notifyDataSetChanged();
                }

                @Override
                public CharSequence convertResultToString(Object resultValue) {
                    return ((RechercheItem) resultValue).getNomAffiche();
                }
            };
        }
    }
}
