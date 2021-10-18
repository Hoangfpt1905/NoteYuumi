package com.hoangnd.noteyuumi.statistics;

import android.content.Context;
import com.hoangnd.noteyuumi.data.dao.DAONote;
import com.hoangnd.noteyuumi.data.database.DatabaseHelper;


public class StatisticsPresenter implements StatisticContract.Presenter {

    StatisticContract.View statisticsView;

    public StatisticsPresenter(StatisticContract.View statisticsView) {
        this.statisticsView = statisticsView;
    }

    public void loadStatistic(Context context, DatabaseHelper databaseHelper, DAONote daoNote){
        databaseHelper = new DatabaseHelper(context);
        daoNote = new DAONote(databaseHelper);
        int activeNote = 0;
        int completedNote = 0;
        for (int i = 0; i<daoNote.getAllNote().size(); i++){
            if (daoNote.getAllNote().get(i).getCompleted()==1){
                completedNote++;
            }else{
                activeNote++;
            }
        }
        if (activeNote>0 || completedNote>0){
            statisticsView.showStatistics(activeNote, completedNote);
        }else{
            statisticsView.showStatisticsNoneNote("You don't have Note!");
        }

    }
}
