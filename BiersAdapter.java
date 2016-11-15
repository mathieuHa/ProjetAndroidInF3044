package org.esiea.hanotaux.projetandroidinf3044;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kafim on 15/11/2016.
 */

class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder> {

    private JSONArray biers = null;

    public BiersAdapter(JSONArray biers) {
        this.biers = biers;
        Log.d("BierAdapteur","constructeur");
    }

    @Override
    public BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_bier_element, parent, false);
        Log.d("BierAdapteur","creatingview");
        return  new BierHolder(view);
    }

    @Override
    public void onBindViewHolder(BierHolder holder, int position) {
        String name = "";
        try {
            JSONObject jo = biers.getJSONObject(position);
             name= jo.getString("name");
            holder.name.setText(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        Log.d("Bier","ok"+biers.length());
        return biers.length();
    }

    public class BierHolder extends RecyclerView.ViewHolder {
        public TextView name = null;

        public BierHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.rv_bier_element_name);
        }
    }

    public void setNewBiere (JSONArray biers) {
        this.biers = biers;
        notifyDataSetChanged();
        Log.d("setnewbiere","OK"+biers.length());
    }
}
