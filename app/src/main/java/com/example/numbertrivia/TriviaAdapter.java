package com.example.numbertrivia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.numbertrivia.data.Trivia;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TriviaAdapter extends RecyclerView.Adapter<TriviaAdapter.ViewHolder> {
    private List<Trivia> mTrivias;

    public TriviaAdapter(List<Trivia> trivias) {
        this.mTrivias = trivias;
    }

    @Override
    public TriviaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.item_trivia_1, parent, false);
        View view2 = inflater.inflate(R.layout.item_trivia_2, parent, false);
        // Return a new holder instance

        TriviaAdapter.ViewHolder viewHolder1 = new TriviaAdapter.ViewHolder(view1);
        TriviaAdapter.ViewHolder viewHolder2 = new TriviaAdapter.ViewHolder(view2);

        switch (viewType) {
            // if position is even return the first layout
            case 0: return viewHolder1;
            //if position is odd return second layout
            case 1: return viewHolder2;
        }
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(TriviaAdapter.ViewHolder holder, final int position) {
        Trivia trivia = mTrivias.get(position);
        holder.tvText.setText(trivia.getText());
        holder.tvNumber.setText(String.valueOf(trivia.getNumber()));
    }

    @Override
    public int getItemViewType(int position) {
        // return 0 if position is even, return 1 if position is odd
        // Note that unlike in ListView adapters, types don't have to be contiguous
        return position % 2;
    }

    @Override
    public int getItemCount() {
        return mTrivias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvNumber)
        TextView tvNumber;

        @BindView(R.id.tvText)
        TextView tvText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
