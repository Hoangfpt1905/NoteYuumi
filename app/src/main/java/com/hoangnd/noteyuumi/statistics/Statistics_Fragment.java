package com.hoangnd.noteyuumi.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hoangnd.noteyuumi.R;
import com.hoangnd.noteyuumi.data.dao.DAONote;
import com.hoangnd.noteyuumi.data.database.DatabaseHelper;

public class Statistics_Fragment extends Fragment implements StatisticContract.View {
    View viewFragmentStatistic;
    private TextView statistics;
    private StatisticsPresenter statisticsPresenter = new StatisticsPresenter(this);
    private DatabaseHelper databaseHelper;
    private DAONote daoNote;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewFragmentStatistic = inflater.inflate(R.layout.fragment_statistics, container, false);
        databaseHelper = new DatabaseHelper(viewFragmentStatistic.getContext());
        daoNote = new DAONote(databaseHelper);
        initViews();
        statisticsPresenter.loadStatistic(viewFragmentStatistic.getContext(), databaseHelper, daoNote);
        return viewFragmentStatistic;
    }

    @Override
    public void showStatistics(int activeNote, int completedNote) {
        String displayStatistic = "Active Note: "+activeNote+"\nCompleted Note: "+completedNote;
        statistics.setText(displayStatistic);
    }

    @Override
    public void showStatisticsNoneNote(String message) {
        statistics.setText(message);
    }

    private void initViews(){
        statistics = (TextView) viewFragmentStatistic.findViewById(R.id.statistics);
    }
}
